package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.DemandType;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Constants;

public class Arm extends Subsystem{
    double FF = 0;
    int timeout_ms = 0;
    TalonSRX mShoulder = new TalonSRX(Constants.kArmA); //On carriage
    TalonSRX mWrist = new TalonSRX(Constants.kArmB); //On intake

    public Arm(){
        initPID();
    }

    public void initPID(){
        //Near elevator joint
        mShoulder.configSelectedFeedbackSensor(FeedbackDevice.Analog, Constants.armA_ID, timeout_ms);
        mShoulder.configAllowableClosedloopError(Constants.armA_ID, 1, timeout_ms);
        mShoulder.config_kP(Constants.armA_ID, Constants.armAKP, timeout_ms);
        mShoulder.config_kI(Constants.armA_ID, Constants.armAKI, timeout_ms);
        mShoulder.config_kD(Constants.armA_ID, Constants.armAKD, timeout_ms);
        //No kF allowed
        //Far elevator joint
        mWrist.configSelectedFeedbackSensor(FeedbackDevice.Analog, Constants.armB_ID, timeout_ms);
        mWrist.configAllowableClosedloopError(Constants.armB_ID, 1, timeout_ms);
        mWrist.config_kP(Constants.armB_ID, Constants.armBKP, timeout_ms);
        mWrist.config_kI(Constants.armB_ID, Constants.armBKI, timeout_ms);
        mWrist.config_kD(Constants.armB_ID, Constants.armBKD, timeout_ms);
        mWrist.config_kF(Constants.armB_ID, Constants.armBKF, timeout_ms);
    }
    
    public void powerArm(double input){
        
    }

    public int getPosA(){
        return (mShoulder.getSelectedSensorPosition(0) + Constants.offsetA); //Flat should be 0
    }

    public int getPosB(){
        return (mWrist.getSelectedSensorPosition(0) + Constants.offsetB);
    }

    public void setPosA(int position){
        FF = Constants.armAKF * Math.cos(Constants.encoder2Rad * (position + Constants.offsetA));
        mShoulder.set(ControlMode.MotionMagic, position + Constants.offsetA, DemandType.ArbitraryFeedForward, FF);
    }

    public void setPosB(int position){
        mWrist.set(ControlMode.MotionMagic, position + Constants.offsetB);
    }

    public void initDefaultCommand(){
        //xd
    }
}