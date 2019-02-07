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

    public Arm(){
        initPID();
        zeroArm(true, true);
    }

    public void initPID(){
        //Near elevator joint
        talonA.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, Constants.armA_ID, timeout_ms);
        talonA.configAllowableClosedloopError(Constants.armA_ID, 1, timeout_ms);
        talonA.config_kP(Constants.armA_ID, Constants.ArmA_P, timeout_ms);
        talonA.config_kI(Constants.armA_ID, Constants.ArmA_I, timeout_ms);
        talonA.config_kD(Constants.armA_ID, Constants.ArmA_D, timeout_ms);
        talonA.config_kF(Constants.armA_ID, Constants.ArmA_F, timeout_ms);
        //Far elevator joint
        talonA.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, Constants.armA_ID, timeout_ms);
        talonA.configAllowableClosedloopError(Constants.armB_ID, 1, timeout_ms);
        talonA.config_kP(Constants.armB_ID, Constants.ArmB_P, timeout_ms);
        talonA.config_kI(Constants.armB_ID, Constants.ArmB_I, timeout_ms);
        talonA.config_kD(Constants.armB_ID, Constants.ArmB_D, timeout_ms);
        talonA.config_kF(Constants.armB_ID, Constants.ArmB_F, timeout_ms);
    }
    
    public void powerArm(double input){
        
    }

    public void setArmA(int position){

    }

    public void setArmB(int position){
        mTalonB.set(ControlMode.MotionMagic, position);
    }

    public void levelIntake(){
        double pos = mTalonA.getSelectedSensorPosition(0);
        
    }

    public void initArmPos(){ //Start position is vertical
        if(armA){
            setSelectedSensorPosition(0, Constants.armA_ID, timeout_ms);
        }
        if(armB){
            setSelectedSensorPosition(0, Constants.armB_ID, timeout_ms);
        }
    }

    public void initDefaultCommand(){
        //xd
    }
}