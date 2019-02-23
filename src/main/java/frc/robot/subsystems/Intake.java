package frc.robot.subsystems; //Name the cargo intake ball fondler

import frc.robot.Constants;
import edu.wpi.first.wpilibj.command.Subsystem;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.DemandType;

public class Intake extends Subsystem{
    TalonSRX mBallFondler = new TalonSRX(Constants.ballIntakeID);
    TalonSRX mDiskSuccer = new TalonSRX(Constants.diskIntakeID);
    
    public Intake(){

    }
    
    public void setSucc(double power){
        mDiskSuccer.set(ControlMode.PercentOutput, power);
    }

    public void setFondle(double power){
        mBallFondler.set(ControlMode.PercentOutput, power, DemandType.ArbitraryFeedForward, Constants.intakeAFF);
        mDiskSuccer.set(ControlMode.PercentOutput, -power);
    }

    public double getSuccVoltage(){
        return mDiskSuccer.getMotorOutputVoltage();
    }
    public double getFondleVoltage(){
        return mBallFondler.getMotorOutputVoltage();
    }
    public double getSuccPercent(){
        return mDiskSuccer.getMotorOutputPercent();
    }
    public double getFondlePercent(){
        return mBallFondler.getMotorOutputPercent();
    }
    public void initDefaultCommand(){
    }
}