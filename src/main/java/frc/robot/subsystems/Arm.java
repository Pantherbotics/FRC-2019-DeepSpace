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
    TalonSRX mTalonA = new TalonSRX(Constants.kArmA); //On carriage
    TalonSRX mTalonB = new TalonSRX(Constants.kArmB); //On intake

    public Arm(){
        initPID();
    }

    public void initPID(){
        //Near elevator joint
        mTalonA.configSelectedFeedbackSensor(FeedbackDevice.Analog, Constants.armA_ID, timeout_ms);
        mTalonA.configAllowableClosedloopError(Constants.armA_ID, 1, timeout_ms);
        mTalonA.config_kP(Constants.armA_ID, Constants.armAKP, timeout_ms);
        mTalonA.config_kI(Constants.armA_ID, Constants.armAKI, timeout_ms);
        mTalonA.config_kD(Constants.armA_ID, Constants.armAKD, timeout_ms);
        //No kF allowed
        //Far elevator joint
        mTalonB.configSelectedFeedbackSensor(FeedbackDevice.Analog, Constants.armB_ID, timeout_ms);
        mTalonB.configAllowableClosedloopError(Constants.armB_ID, 1, timeout_ms);
        mTalonB.config_kP(Constants.armB_ID, Constants.armBKP, timeout_ms);
        mTalonB.config_kI(Constants.armB_ID, Constants.armBKI, timeout_ms);
        mTalonB.config_kD(Constants.armB_ID, Constants.armBKD, timeout_ms);
        mTalonB.config_kF(Constants.armB_ID, Constants.armBKF, timeout_ms);
    }
    
    public void powerArm(double input){
        
    }

    public int getPosA(){
        return (mTalonA.getSelectedSensorPosition(0) + Constants.offsetA); //Flat should be 0
    }

    public int getPosB(){
        return (mTalonB.getSelectedSensorPosition(0) + Constants.offsetB);
    }

    public void setPosA(int position){
        FF = Math.cos(Constants.armAKF);
        mTalonA.set(ControlMode.MotionMagic, position + Constants.offsetA, DemandType.ArbitraryFeedForward, FF);
    }

    public void setPosB(int position){
        mTalonB.set(ControlMode.MotionMagic, position + Constants.offsetB);
    }

    public void initDefaultCommand(){
        //xd
    }
}