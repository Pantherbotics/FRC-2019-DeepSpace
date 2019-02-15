package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.DemandType;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Constants;
import frc.robot.commands.*;

public class Arm extends Subsystem{
    double FF = 0;
    int timeout_ms = 0;
    TalonSRX mShoulder = new TalonSRX(Constants.kArmA); //On carriage
    TalonSRX mWrist = new TalonSRX(Constants.kArmB); //On intake
    private final int kPIDIdx = 0;
    public Arm(){
        initPID();
        initPos();
    }

    public void initPID(){
        //Near elevator joint
        mShoulder.configSelectedFeedbackSensor(FeedbackDevice.Analog, kPIDIdx, timeout_ms);
        mShoulder.setInverted(true);
        mShoulder.setSensorPhase(false);
        mShoulder.configAllowableClosedloopError(kPIDIdx, 1, timeout_ms);
        mShoulder.config_kP(kPIDIdx, Constants.armAKP, timeout_ms);
        mShoulder.config_kI(kPIDIdx, Constants.armAKI, timeout_ms);
        mShoulder.config_kD(kPIDIdx, Constants.armAKD, timeout_ms);
        mShoulder.config_kF(kPIDIdx, Constants.armAKF, timeout_ms);
        mShoulder.configMotionCruiseVelocity(Constants.shoulderCruiseSpeed, timeout_ms);
        mShoulder.configMotionAcceleration(Constants.shoulderAccelerationSpeed, timeout_ms);
        //Far elevator joint
        mWrist.configSelectedFeedbackSensor(FeedbackDevice.Analog, 0, timeout_ms);
        mWrist.configAllowableClosedloopError(kPIDIdx, 1, timeout_ms);
        mWrist.config_kP(kPIDIdx, Constants.armBKP, timeout_ms);
        mWrist.config_kI(kPIDIdx, Constants.armBKI, timeout_ms);
        mWrist.config_kD(kPIDIdx, Constants.armBKD, timeout_ms);
        mWrist.config_kF(kPIDIdx, Constants.armBKF, timeout_ms);
    }

    private void initPos(){
        mShoulder.setSelectedSensorPosition(0, 0, timeout_ms);
        mWrist.setSelectedSensorPosition(0, 0, timeout_ms);
    }
    
    public void powerArm(double input){
        mShoulder.set(ControlMode.PercentOutput, 0.25 * input);
    }
    public int getPosA(){
        return mShoulder.getSelectedSensorPosition(0); //Flat should be 0
    }
    public double getVoltA(){
        return mShoulder.getMotorOutputVoltage();
    }

    public int getPosB(){
        return mWrist.getSelectedSensorPosition(0);
    }
    public double getVoltB(){
        return mWrist.getMotorOutputVoltage();
    }

    public void setPosA(int position){
        FF = Constants.FF * Math.cos(Constants.encoder2Rad * (position));
        mShoulder.set(ControlMode.MotionMagic, position, DemandType.ArbitraryFeedForward, FF);
    }

    public void setPosB(int position){
        mWrist.set(ControlMode.MotionMagic, position - getPosA());
    }

    public void initDefaultCommand(){
        //setDefaultCommand(new ShoulderPower());
    }
}