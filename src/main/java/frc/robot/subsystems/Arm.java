package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.*;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.ParamEnum;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;
import frc.robot.util.Units;

public class Arm extends Subsystem{

    double shoulderkF;
    double wristkF;
    int shoulderPosition;
    int wristPosition;

    int timeout_ms = 0;
    State state;
    TalonSRX mShoulder = new TalonSRX(Constants.kshoulder); //On carriage
    TalonSRX mWrist = new TalonSRX(Constants.kwrist); //On intake
    private final int kPIDIdx = 0;
    public Arm(){
        state = State.INDEPENDENT;
        initPID();
        //initPos();
    }

    enum State{
        FOUR_BAR, INDEPENDENT
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
        mShoulder.configMotionCruiseVelocity(Constants.shoulderCruiseSpeed, timeout_ms);
        mShoulder.configMotionAcceleration(Constants.shoulderAccelerationSpeed, timeout_ms);
        mShoulder.configSetParameter(ParamEnum.eFeedbackNotContinuous, 1, 0x00, 0x00, 0x00);
        //Far elevator joint
        mWrist.configFactoryDefault(timeout_ms);
        mWrist.configSelectedFeedbackSensor(FeedbackDevice.Analog, kPIDIdx, timeout_ms);
        mWrist.configSetParameter(ParamEnum.eFeedbackNotContinuous, 1, 0x00, 0x00, 0x00);
        mWrist.setNeutralMode(NeutralMode.Brake);
        mWrist.setInverted(true);
        mWrist.setSensorPhase(false);
        mWrist.configAllowableClosedloopError(kPIDIdx, 1, timeout_ms);
        mWrist.config_kP(kPIDIdx, Constants.wristKP, timeout_ms);
        mWrist.config_kI(kPIDIdx, Constants.wristKI, timeout_ms);
        mWrist.config_kD(kPIDIdx, Constants.wristKD, timeout_ms);
        mWrist.config_kF(kPIDIdx, Constants.wristKF, timeout_ms);
        mWrist.configMotionCruiseVelocity(Constants.shoulderCruiseSpeed, timeout_ms);
        mWrist.configMotionAcceleration(Constants.shoulderAccelerationSpeed, timeout_ms);
    }

    private void configFourBar(){
        mWrist.configRemoteFeedbackFilter(mShoulder.getDeviceID(), RemoteSensorSource.TalonSRX_SelectedSensor, 0, timeout_ms);
        mWrist.configRemoteFeedbackFilter(0x00, RemoteSensorSource.Off, 1, timeout_ms);
        mWrist.configSensorTerm(SensorTerm.Sum0, FeedbackDevice.RemoteSensor0, timeout_ms);
        mWrist.configSensorTerm(SensorTerm.Sum1, FeedbackDevice.Analog, timeout_ms);
        mWrist.configSelectedFeedbackSensor(FeedbackDevice.SensorSum);
    }

    private void initPos(){
        mShoulder.setSelectedSensorPosition(0, 0, timeout_ms);
        mWrist.setSelectedSensorPosition(0, 0, timeout_ms);
    }

    public void configState(Arm.State state){
        switch(state){
            case FOUR_BAR: {
                    this.state = state;
                    initPID();
                    configFourBar();
                }
                break;
            case INDEPENDENT: {
                    this.state = state;
                    initPID();
                }
                break;
            default:
                break;
        }
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
        shoulderkF = Constants.shoulderAFF * Math.cos(Constants.encoder2Rad * (position));
        shoulderPosition = position + Constants.shoulderOffset;
        mShoulder.set(ControlMode.MotionMagic, (position + Constants.shoulderOffset), DemandType.ArbitraryFeedForward, shoulderkF);
        System.out.println("SHOULDER IS BEING CALLED");
        //mShoulder.set(ControlMode.MotionMagic, positon);
    }
    public void setWristPosition(int wristPos, int shoulderPos){ //[-180, 220]
        if(state == State.INDEPENDENT) {
            wristkF = Constants.wristAFF * Math.cos(Math.toRadians(getWristDegrees()));
            wristPosition = wristPos - shoulderPos + Constants.shoulder2WristOffset;
            if (wristPosition > 215) {
                wristPosition = 215;
            } else if (wristPosition < -180) {
                wristPosition = -180;
            }
            mWrist.set(ControlMode.MotionMagic, (wristPosition + Constants.wristOffset), DemandType.ArbitraryFeedForward, wristkF);
            SmartDashboard.putNumber("Wrist Setpoint", mWrist.getClosedLoopTarget(0));
        } else if (state == State.FOUR_BAR) {
            wristkF = Constants.wristAFF * Math.cos(Math.toRadians(getWristDegrees()));
            mWrist.set(ControlMode.MotionMagic, wristPos + Constants.wristOffset + Constants.shoulderOffset);
            SmartDashboard.putNumber("Wrist Setpoint", mWrist.getClosedLoopTarget(0));
        }
        System.out.println("WRIST IS BEING CALLED");
        //mWrist.set(ControlMode.MotionMagic, position);
    }

    public void setFourBarDegrees(double degrees){


    }
    public void initDefaultCommand(){
        //setDefaultCommand(new ShoulderPower());
    }
}
