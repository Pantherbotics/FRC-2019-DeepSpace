package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Constants;

public class Arm extends Subsystem{
    double FF = 0;
    int timeout_ms = 0;
    int jointA_ID = 0;
    int jointB_ID = 1;
    TalonSRX talonA = new TalonSRX(Constants.kArmA);
    TalonSRX talonB = new TalonSRX(Constants.kArmB);

    public void initPID(){
        //Near elevator joint
        talonA.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, jointA_ID, timeout_ms);
        talonA.configAllowableClosedloopError(jointA_ID, 1, timeout_ms);
        talonA.config_kP(jointA_ID, Constants.kArmA_P, timeout_ms);
        talonA.config_kI(jointA_ID, Constants.kArmA_I, timeout_ms);
        talonA.config_kD(jointA_ID, Constants.kArmA_D, timeout_ms);
        talonA.config_kF(jointA_ID, Constants.kArmA_F, timeout_ms);
        //Far elevator joint
        talonA.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, jointA_ID, timeout_ms);
        talonA.configAllowableClosedloopError(jointA_ID, 1, timeout_ms);
        talonA.config_kP(jointA_ID, Constants.kArmA_P, timeout_ms);
        talonA.config_kI(jointA_ID, Constants.kArmA_I, timeout_ms);
        talonA.config_kD(jointA_ID, Constants.kArmA_D, timeout_ms);
        talonA.config_kF(jointA_ID, Constants.kArmA_F, timeout_ms);
    }
    
    public void moveArm(){

    }

    public void initDefaultCommand(){
        //xd
    }
}