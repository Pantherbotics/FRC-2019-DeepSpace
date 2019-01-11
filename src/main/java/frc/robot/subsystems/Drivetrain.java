package frc.robot.subsystems;
import frc.robot.Constants;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import frc.robot.commands.Drive;
import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Drivetrain extends Subsystem{
    private TalonSRX mLeftA = new TalonSRX(Constants.kLeftA);
    private TalonSRX mLeftB = new TalonSRX(Constants.kLeftB);
    private TalonSRX mLeftC = new TalonSRX(Constants.kLeftC);
    private TalonSRX mRightA = new TalonSRX(Constants.kRightA);
    private TalonSRX mRightB = new TalonSRX(Constants.kRightB);
    private TalonSRX mRightC = new TalonSRX(Constants.kRightC);
    
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
    public void initDefaultCommand(){
        setDefaultCommand(new Drive());
    }
    public void setMotorPower(double left, double right){
        mLeftA.set(ControlMode.PercentOutput, left);
        mRightA.set(ControlMode.PercentOutput, right);
    }
}