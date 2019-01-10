package frc.robot.subsystems;

import frc.robot.Constants;
import frc.robot.util.Units;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import frc.robot.commands.Drive;
import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Drivetrain extends Subsystem{
    public int timeoutMS = 0;
    int hdrive_ID = 0;
    int ldrive_ID = 1;
    TalonSRX mLeftA = new TalonSRX(Constants.kLeftA);
    TalonSRX mLeftB = new TalonSRX(Constants.kLeftB);
    TalonSRX mLeftC = new TalonSRX(Constants.kLeftC);
    TalonSRX mRightA = new TalonSRX(Constants.kRightA);
    TalonSRX mRightB = new TalonSRX(Constants.kRightB);
    TalonSRX mRightC = new TalonSRX(Constants.kRightC);
    
    public Drivetrain(){
        //mLeftB.follow(mLeftA);
        //mLeftC.follow(mLeftA);
        //mRightB.follow(mRightA);
        //mRightC.follow(mRightA);
        mLeftA.setInverted(false);
        mLeftB.setInverted(false);
        mLeftC.setInverted(false);
        mRightA.setInverted(true);
        mRightB.setInverted(true);
        mRightC.setInverted(true);


    }

    public void initPID(){
        //high left
        mLeftA.configAllowableClosedloopError(hdrive_ID, 0, timeoutMS);
        mLeftA.config_kP(hdrive_ID, Constants.HDRIVE_P, timeoutMS);
        mLeftA.config_kI(hdrive_ID, Constants.HDRIVE_I, timeoutMS);
        mLeftA.config_kD(hdrive_ID, Constants.HDRIVE_D, timeoutMS);
        mLeftA.config_kF(hdrive_ID, Constants.HDRIVE_F, timeoutMS);
        //high right
        mRightA.configAllowableClosedloopError(hdrive_ID, 0, timeoutMS);
        mRightA.config_kP(hdrive_ID, Constants.HDRIVE_P, timeoutMS);
        mRightA.config_kI(hdrive_ID, Constants.HDRIVE_I, timeoutMS);
        mRightA.config_kD(hdrive_ID, Constants.HDRIVE_D, timeoutMS);
        mRightA.config_kF(hdrive_ID, Constants.HDRIVE_F, timeoutMS);
        //low left
        mLeftA.configAllowableClosedloopError(ldrive_ID, 0, timeoutMS);
        mLeftA.config_kP(ldrive_ID, Constants.LDRIVE_P, timeoutMS);
        mLeftA.config_kI(ldrive_ID, Constants.LDRIVE_I, timeoutMS);
        mLeftA.config_kD(ldrive_ID, Constants.LDRIVE_D, timeoutMS);
        mLeftA.config_kF(ldrive_ID, Constants.LDRIVE_F, timeoutMS);
        //low right
        mRightA.configAllowableClosedloopError(ldrive_ID, 0, timeoutMS);
        mRightA.config_kP(ldrive_ID, Constants.LDRIVE_P, timeoutMS);
        mRightA.config_kI(ldrive_ID, Constants.LDRIVE_I, timeoutMS);
        mRightA.config_kD(ldrive_ID, Constants.LDRIVE_D, timeoutMS);
        mRightA.config_kF(ldrive_ID, Constants.LDRIVE_F, timeoutMS);
    }
    public void initDefaultCommand(){
        setDefaultCommand(new Drive());
    }

    public void setMotorPower(double left, double right){
        mLeftA.set(ControlMode.PercentOutput, left);
        mRightA.set(ControlMode.PercentOutput, right);
    }

    public void setFPS(double left, double right){
        mLeftA.set(ControlMode.Velocity, left);
        mRightA.set(ControlMode.Velocity, right);
    }
}