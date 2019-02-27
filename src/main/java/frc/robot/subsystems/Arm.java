package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.*;
import com.ctre.phoenix.ParamEnum;

import edu.wpi.first.wpilibj.Notifier;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;
import frc.robot.commands.IncrementShoulder;
import frc.robot.util.LazyTalonSRX;
import frc.robot.util.Units;

public class Arm extends Subsystem{

    private double shoulderkF;
    private double wristkF;
    private int shoulderPosition;
    private int shoulderSetpoint, wristSetpoint;

    private int timeout_ms = 0;
    private LazyTalonSRX mShoulder = new LazyTalonSRX(Constants.shoulderID); //On carriage
    private LazyTalonSRX mWrist = new LazyTalonSRX(Constants.wristID); //On intake
    private final int kPIDIdx = 0;
    public Arm(){

        shoulderSetpoint = Units.degreesToTalon(14.0);
        wristSetpoint = Units.degreesToTalon(0);
        mShoulder.configSelectedFeedbackSensor(FeedbackDevice.Analog, kPIDIdx, timeout_ms);

        //INITIALIZE SHOULDER PARAMETERS
        mShoulder.setNeutralMode(NeutralMode.Coast);

        //SHOULDER SENSOR CONFIG
        mShoulder.setInverted(false);
        mShoulder.setSensorPhase(false);

        //SHOULDER PID PARAMETERS
        mShoulder.configAllowableClosedloopError(kPIDIdx, 1, timeout_ms);
        mShoulder.config_kP(kPIDIdx, Constants.shoulderKP, timeout_ms);
        mShoulder.config_kI(kPIDIdx, Constants.shoulderKI, timeout_ms);
        mShoulder.config_kD(kPIDIdx, Constants.shoulderKD, timeout_ms);
        mShoulder.config_kF(kPIDIdx, Constants.shoulderKF, timeout_ms);
        mShoulder.configMotionCruiseVelocity(Constants.kShoulderCruiseSpeed, timeout_ms);
        mShoulder.configMotionAcceleration(Constants.kShoulderAccelerationSpeed, timeout_ms);
        mShoulder.configSetParameter(ParamEnum.eFeedbackNotContinuous, 1, 0x00, 0x00, 0x00);

        //WRIST PARAMETERS
        mWrist.setNeutralMode(NeutralMode.Coast);

        //WRIST SENSOR CONFIG
        mWrist.setInverted(true);
        mWrist.setSensorPhase(false);
        mWrist.configSetParameter(ParamEnum.eFeedbackNotContinuous, 1, 0x00, 0x00, 0x00);
        mWrist.configRemoteFeedbackFilter(mShoulder.getDeviceID(), RemoteSensorSource.TalonSRX_SelectedSensor, 0, timeout_ms);
        mWrist.configRemoteFeedbackFilter(0x00, RemoteSensorSource.Off, 1, timeout_ms);
        mWrist.configSensorTerm(SensorTerm.Sum0, FeedbackDevice.RemoteSensor0, timeout_ms);
        mWrist.configSensorTerm(SensorTerm.Sum1, FeedbackDevice.Analog, timeout_ms);
        mWrist.configSelectedFeedbackSensor(FeedbackDevice.SensorSum);

        //WRIST PID PARAMETERS

        mWrist.configAllowableClosedloopError(kPIDIdx, 0, timeout_ms);
        mWrist.config_kP(kPIDIdx, Constants.wristKP, timeout_ms);
        mWrist.config_kI(kPIDIdx, Constants.wristKI, timeout_ms);
        mWrist.config_kD(kPIDIdx, Constants.wristKD, timeout_ms);
        mWrist.config_kF(kPIDIdx, Constants.wristKF, timeout_ms);
        mWrist.configMotionCruiseVelocity(Constants.kWristCruiseSpeed, timeout_ms);
        mWrist.configMotionAcceleration(Constants.kWristAccelerationSpeed, timeout_ms);


        //CREATE THREAD FOR SETTING TALONS
        Notifier feedForwardThread = new Notifier(() ->{ //Inputs should be aimed at the RAW sensor units
            shoulderkF = Constants.shoulderAFF * Math.abs(Math.cos(Math.toRadians(getShoulderDegrees())));
            wristkF = Constants.wristAFF * Math.abs(Math.cos(Math.toRadians(getWristDegrees() + getShoulderDegrees())));

            mShoulder.set(ControlMode.MotionMagic,(-shoulderSetpoint + Constants.kShoulderOffset), DemandType.ArbitraryFeedForward, shoulderkF);

            //since our wrist TalonSRX is being driven off the SUM of both its position and the shoulder position, we get 4-bar like motion when we drive the wrist to a setpoint.
            //(wristAngle + kWristOffset) + (shoulderAngle + kShoulderOffset) = setpoint + kShoulderOffset + kWristOffset

            //SET WRIST SOFT LIMITS
            wristSetpoint = getWristPositionRaw() >= Constants.kWristMaxPos ? Constants.kWristMaxPos : wristSetpoint;
            wristSetpoint = getWristPositionRaw() <= Constants.kWristMinPos ? Constants.kWristMinPos : wristSetpoint;

            //SET WRIST

            mWrist.set(ControlMode.MotionMagic, wristSetpoint - (Constants.kWristOffset + Constants.kShoulderOffset), DemandType.ArbitraryFeedForward, wristkF);

            SmartDashboard.putNumber("Wrist Setpoint", mWrist.getClosedLoopTarget(0));
            SmartDashboard.putNumber("Wrist Sensor Sum", mWrist.getSelectedSensorPosition());
            SmartDashboard.putNumber("Shoulder Setpoint", mShoulder.getClosedLoopTarget(0));

        });

        feedForwardThread.startPeriodic(0.02);

    }


    public void powerShoulder(double input){
        mShoulder.set(ControlMode.PercentOutput, 0.5 * input);
    }
    public void powerWrist(double input){
        mWrist.set(ControlMode.PercentOutput, 0.5 * input);
    }

    public int getShoulderPosition(){
        return mShoulder.getSensorCollection().getAnalogInRaw()-Constants.kShoulderOffset; //Flat should be 0
    }

    public int getShoulderPositionRaw(){
        return mShoulder.getSensorCollection().getAnalogInRaw();
    }

    public int getWristPositionRaw(){
        return mWrist.getSensorCollection().getAnalogInRaw();
    }
    public double getShoulderDegrees(){
        return -Units.talonToDegrees(getShoulderPosition());
    }

    public double getShoulderVoltage(){
        return mShoulder.getMotorOutputVoltage();
    }

    public int getWristPosition(){
        return mWrist.getSensorCollection().getAnalogInRaw()-Constants.kWristOffset;
    }

    public double getWristDegrees(){
        return -Units.talonToDegrees(getWristPosition());
    }

    public double getWristVoltage(){
        return mWrist.getMotorOutputVoltage();
    }

    public void setShoulderPosition(int position){
        shoulderSetpoint = position;
        System.out.println("SHOULDER IS BEING CALLED");
    }
    public void setWristPosition(int position){ //[-180, 220]
        wristSetpoint = position;
        System.out.println("WRIST IS BEING CALLED");
    }

    public void initDefaultCommand(){
        //setDefaultCommand(new ShoulderPower());
        setDefaultCommand(new IncrementShoulder(0));
    }
}
