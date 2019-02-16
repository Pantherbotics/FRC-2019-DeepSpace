package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.ParamEnum;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.DemandType;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;
import frc.robot.commands.*;

public class Arm extends Subsystem{
    double sFF;
    double wFF;
    int pos;
    int timeout_ms = 0;
    TalonSRX mShoulder = new TalonSRX(Constants.kArmA); //On carriage
    TalonSRX mWrist = new TalonSRX(Constants.kArmB); //On intake
    private final int kPIDIdx = 0;
    public Arm(){
        initPID();
        //initPos();
    }

    public void initPID(){
        //Near elevator joint
        mShoulder.configSelectedFeedbackSensor(FeedbackDevice.Analog, kPIDIdx, timeout_ms);

        //mShoulder.setNeutralMode(NeutralMode.Brake);
        mShoulder.setInverted(true);
        mShoulder.setSensorPhase(false);
        mShoulder.configAllowableClosedloopError(kPIDIdx, 1, timeout_ms);
        mShoulder.config_kP(kPIDIdx, Constants.armAKP, timeout_ms);
        mShoulder.config_kI(kPIDIdx, Constants.armAKI, timeout_ms);
        mShoulder.config_kD(kPIDIdx, Constants.armAKD, timeout_ms);
        mShoulder.config_kF(kPIDIdx, Constants.armAKF, timeout_ms);
        mShoulder.configMotionCruiseVelocity(Constants.shoulderCruiseSpeed, timeout_ms);
        mShoulder.configMotionAcceleration(Constants.shoulderAccelerationSpeed, timeout_ms);
        mShoulder.configSetParameter(ParamEnum.eFeedbackNotContinuous, 1, 0x00, 0x00, 0x00);
        //Far elevator joint
        mWrist.configFactoryDefault(timeout_ms);
        mWrist.configSelectedFeedbackSensor(FeedbackDevice.Analog, kPIDIdx, timeout_ms);
        mWrist.configSetParameter(ParamEnum.eFeedbackNotContinuous, 1, 0x00, 0x00, 0x00);
        //mWrist.setNeutralMode(NeutralMode.Brake);
        mWrist.setInverted(true);
        mWrist.setSensorPhase(false);
        mWrist.configAllowableClosedloopError(kPIDIdx, 1, timeout_ms);
        mWrist.config_kP(kPIDIdx, Constants.armBKP, timeout_ms);
        mWrist.config_kI(kPIDIdx, Constants.armBKI, timeout_ms);
        mWrist.config_kD(kPIDIdx, Constants.armBKD, timeout_ms);
        mWrist.config_kF(kPIDIdx, Constants.armBKF, timeout_ms);
        mWrist.configMotionCruiseVelocity(Constants.shoulderCruiseSpeed, timeout_ms);
        mWrist.configMotionAcceleration(Constants.shoulderAccelerationSpeed, timeout_ms);
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

    public int getPosA(){
        return mShoulder.getSensorCollection().getAnalogIn(); //Flat should be 0
    }
    public double getVoltA(){
        return mShoulder.getMotorOutputVoltage();
    }

    public int getPosB(){
        return mWrist.getSensorCollection().getAnalogIn();
    }
    public double getVoltB(){
        return mWrist.getMotorOutputVoltage();
    }

    public void setPosA(int position){
        sFF = Constants.armAAFF * Math.cos(Constants.encoder2Rad * (position));
        mShoulder.set(ControlMode.MotionMagic, (position + Constants.shoulderOffset), DemandType.ArbitraryFeedForward, sFF);
        System.out.println("SHOULDER IS BEING CALLED");
        //mShoulder.set(ControlMode.MotionMagic, positon);
    }
    public void setPosB(int wristPos, int shoulderPos){ //[-180, 220]
        wFF = Constants.armBAFF * Math.cos(Constants.encoder2Rad * wristPos);
        pos = -shoulderPos + Constants.shoulder2WristOffset;
        if(pos > 215){
            pos = 215;
        } else if(pos < -180){
            pos = -180;
        }
        mWrist.set(ControlMode.MotionMagic, (pos + Constants.wristOffset), DemandType.ArbitraryFeedForward, wFF);
        SmartDashboard.putNumber("Wrist Setpoint", mWrist.getClosedLoopTarget(0));
        System.out.println("WRIST IS BEING CALLED");
        //mWrist.set(ControlMode.MotionMagic, position);    
    }

    public void initDefaultCommand(){
        //setDefaultCommand(new ShoulderPower());
    }
}