package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Constants;

public class Arm extends Subsystem{
    double FF = 0;
    int timeout_ms = 0;
    TalonSRX mTalonA = new TalonSRX(Constants.kArmA); //On carriage
    TalonSRX mTalonB = new TalonSRX(Constants.kArmB); //On intake

    public Arm(){ //Arm is 22in long. Bigboi torque
        initPID();
        initArmPos();
    }

    public void initPID(){
        //Near elevator joint
        mTalonA.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, Constants.armA_ID, timeout_ms);
        mTalonA.configAllowableClosedloopError(Constants.armA_ID, 1, timeout_ms);
        mTalonA.config_kP(Constants.armA_ID, Constants.armAKP, timeout_ms);
        mTalonA.config_kI(Constants.armA_ID, Constants.armAKI, timeout_ms);
        mTalonA.config_kD(Constants.armA_ID, Constants.armAKD, timeout_ms);
        mTalonA.config_kF(Constants.armA_ID, Constants.armAKF, timeout_ms);
        //Far elevator joint
        mTalonA.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, Constants.armA_ID, timeout_ms);
        mTalonA.configAllowableClosedloopError(Constants.armB_ID, 1, timeout_ms);
        mTalonA.config_kP(Constants.armB_ID, Constants.armBKP, timeout_ms);
        mTalonA.config_kI(Constants.armB_ID, Constants.armBKI, timeout_ms);
        mTalonA.config_kD(Constants.armB_ID, Constants.armBKD, timeout_ms);
        mTalonA.config_kF(Constants.armB_ID, Constants.armBKF, timeout_ms);
    }
    
    public void powerArm(double input){
        mTalonA.set(ControlMode.PercentOutput, input);
    }

    public void setArmA(int position){

    }

    public void setArmB(int position){
        mTalonB.set(ControlMode.MotionMagic, position);
    }

    public void stableIntake(){
        double pos = mTalonA.getSelectedSensorPosition(0);
        
    }

    public void initArmPos(){ //Start position is vertical
        mTalonA.setSelectedSensorPosition(0, Constants.armA_ID, timeout_ms);
        mTalonB.setSelectedSensorPosition(0, Constants.armB_ID, timeout_ms);
    }

    public void initDefaultCommand(){
        //xd
    }
}