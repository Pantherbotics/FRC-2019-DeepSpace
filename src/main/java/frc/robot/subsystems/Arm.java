package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.*;
import com.ctre.phoenix.ParamEnum;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.Notifier;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;
import frc.robot.commands.IncrementShoulder;
import frc.robot.util.LazyTalonSRX;
import frc.robot.commands.PowerArmOpenLoop;
import frc.robot.util.Units;

public class Arm extends Subsystem{

    private double shoulderkF;
    private int shoulderPosition;
    private double shoulderSetpoint;
    private double elevAccel;

    private int timeout_ms = 0;
    private TalonSRX mShoulder = new TalonSRX(Constants.shoulderID); //On carriage
    private final int kPIDIdx = 0;
    public Arm(){

        shoulderSetpoint = Units.degreesToTalon(62.5);
        initPID();
        //initPos();


        Notifier feedForwardThread = new Notifier(() ->{ //Inputs should be aimed at the RAW sensor units
            shoulderkF = Math.abs(Math.cos(Math.toRadians(getShoulderDegrees()))) * (Constants.elevatorAFF + Units.elevAccelToVoltage(elevAccel));

            mShoulder.set(ControlMode.MotionMagic,(-shoulderSetpoint + Constants.kShoulderOffset), DemandType.ArbitraryFeedForward, -shoulderkF);
            //mShoulder.set(ControlMode.PercentOutput, shoulderSetpoint, DemandType.ArbitraryFeedForward, shoulderkF);
            SmartDashboard.putNumber("Shoulder Setpoint", mShoulder.getClosedLoopTarget(0));
        });

        feedForwardThread.startPeriodic(Constants.shoulderPeriod);

    }

    public void initPID(){
        //Shoulder
        mShoulder.configSelectedFeedbackSensor(FeedbackDevice.Analog, kPIDIdx, timeout_ms);

        mShoulder.setNeutralMode(NeutralMode.Brake);
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
    }


    public void powerShoulder(double input){
        shoulderSetpoint = input;
    }

    public int getShoulderPosition(){
        return mShoulder.getSensorCollection().getAnalogInRaw()-Constants.kShoulderOffset; //Flat should be 0
    }

    public int getShoulderPositionRaw(){
        return mShoulder.getSelectedSensorPosition();
    }

    public double getShoulderDegrees(){
        return -Units.talonToDegrees(getShoulderPosition());
    }

    public double getShoulderVoltage(){
        return mShoulder.getMotorOutputVoltage();
    }

    public void setShoulderPosition(int position, double elevAcceleration){
        elevAccel = elevAcceleration;
        shoulderSetpoint = position;
        System.out.println("SHOULDER IS BEING CALLED");
    }

    public void initDefaultCommand(){
        //setDefaultCommand(new PowerArmOpenLoop());
        //setDefaultCommand(new IncrementShoulder());
    }
}
