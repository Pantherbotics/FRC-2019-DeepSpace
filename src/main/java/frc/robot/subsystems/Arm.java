package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.*;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.ParamEnum;

import edu.wpi.first.wpilibj.Notifier;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;
import frc.robot.util.Units;

public class Arm extends Subsystem{

    private double shoulderkF;
    private double wristkF;
    private int shoulderPosition;
    private int shoulderSetpoint, wristSetpoint;

    int timeout_ms = 0;
    TalonSRX mShoulder = new TalonSRX(Constants.shoulderID); //On carriage
    TalonSRX mWrist = new TalonSRX(Constants.wristID); //On intake
    private final int kPIDIdx = 0;
    public Arm(){
        initPID();
        //initPos();

        Notifier feedForwardThread = new Notifier(() ->{
            shoulderkF = Constants.shoulderAFF * Math.cos(Math.toRadians(getShoulderDegrees()));
            wristkF = Constants.wristAFF * Math.cos(Math.toRadians(getWristDegrees() + getShoulderDegrees()));

            mShoulder.set(ControlMode.MotionMagic,shoulderSetpoint + Constants.kShoulderOffset, DemandType.ArbitraryFeedForward, shoulderkF);

            //since our wrist TalonSRX is being driven off the SUM of both its position and the shoulder position, we get 4-bar like motion when we drive the wrist to a setpoint.
            //(wristAngle + kWristOffset) + (shoulderAngle + kShoulderOffset) = setpoint + kShoulderOffset + kWristOffset
            mWrist.set(ControlMode.MotionMagic, wristSetpoint + Constants.kWristOffset + Constants.kShoulderOffset, DemandType.ArbitraryFeedForward, wristkF);

            SmartDashboard.putNumber("Wrist Setpoint", mWrist.getClosedLoopTarget(0));
            SmartDashboard.putNumber("Shoulder Setpoint", mShoulder.getClosedLoopTarget(0));

        });
        feedForwardThread.startPeriodic(0.02);
    }

    public void initPID(){
        //Near elevator joint
        mShoulder.configSelectedFeedbackSensor(FeedbackDevice.Analog, kPIDIdx, timeout_ms);

        mShoulder.setNeutralMode(NeutralMode.Brake);
        mShoulder.setInverted(true);
        mShoulder.setSensorPhase(false);
        mShoulder.configAllowableClosedloopError(kPIDIdx, 1, timeout_ms);
        mShoulder.config_kP(kPIDIdx, Constants.shoulderKP, timeout_ms);
        mShoulder.config_kI(kPIDIdx, Constants.shoulderKI, timeout_ms);
        mShoulder.config_kD(kPIDIdx, Constants.shoulderKD, timeout_ms);
        mShoulder.config_kF(kPIDIdx, Constants.shoulderKF, timeout_ms);
        mShoulder.configMotionCruiseVelocity(Constants.kShoulderCruiseSpeed, timeout_ms);
        mShoulder.configMotionAcceleration(Constants.kShoulderAccelerationSpeed, timeout_ms);
        mShoulder.configSetParameter(ParamEnum.eFeedbackNotContinuous, 1, 0x00, 0x00, 0x00);
        //Far elevator joint

        mWrist.configFactoryDefault(timeout_ms);
        mWrist.configRemoteFeedbackFilter(mShoulder.getDeviceID(), RemoteSensorSource.TalonSRX_SelectedSensor, 0, timeout_ms);
        mWrist.configRemoteFeedbackFilter(0x00, RemoteSensorSource.Off, 1, timeout_ms);
        mWrist.configSensorTerm(SensorTerm.Sum0, FeedbackDevice.RemoteSensor0, timeout_ms);
        mWrist.configSensorTerm(SensorTerm.Sum1, FeedbackDevice.Analog, timeout_ms);
        mWrist.configSelectedFeedbackSensor(FeedbackDevice.SensorSum);
        mWrist.configSetParameter(ParamEnum.eFeedbackNotContinuous, 1, 0x00, 0x00, 0x00);
        mWrist.setNeutralMode(NeutralMode.Brake);
        mWrist.setInverted(true);
        mWrist.setSensorPhase(false);
        mWrist.configAllowableClosedloopError(kPIDIdx, 0, timeout_ms);
        mWrist.config_kP(kPIDIdx, Constants.wristKP, timeout_ms);
        mWrist.config_kI(kPIDIdx, Constants.wristKI, timeout_ms);
        mWrist.config_kD(kPIDIdx, Constants.wristKD, timeout_ms);
        mWrist.config_kF(kPIDIdx, Constants.wristKF, timeout_ms);
        mWrist.configMotionCruiseVelocity(Constants.kWristCruiseSpeed, timeout_ms);
        mWrist.configMotionAcceleration(Constants.kWristAccelerationSpeed, timeout_ms);
    }


    private void initPos(){
        mShoulder.setSelectedSensorPosition(0, 0, timeout_ms);
        mWrist.setSelectedSensorPosition(0, 0, timeout_ms);
    }

    public void powerShoulder(double input){
        mShoulder.set(ControlMode.PercentOutput, 0.25 * input);
    }
    public void powerWrist(double input){
        mWrist.set(ControlMode.PercentOutput, 0.5 * input);
    }

    public int getShoulderPosition(){
        return mShoulder.getSensorCollection().getAnalogInRaw(); //Flat should be 0
    }

    public double getShoulderDegrees(){
        return Units.talonToDegrees(getShoulderPosition());
    }

    public double getShoulderVoltage(){
        return mShoulder.getMotorOutputVoltage();
    }

    public int getWristPosition(){
        return mWrist.getSensorCollection().getAnalogInRaw();
    }

    public double getWristDegrees(){
        return Units.talonToDegrees(getWristPosition());
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
    }
}
