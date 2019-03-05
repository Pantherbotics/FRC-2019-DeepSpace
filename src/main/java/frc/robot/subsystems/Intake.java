package frc.robot.subsystems; //Name the cargo intake ball fondler

import edu.wpi.first.wpilibj.Solenoid;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.command.Subsystem;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.DemandType;

public class Intake extends Subsystem{
    TalonSRX mCargo = new TalonSRX(Constants.ballIntakeID);
    Solenoid cargoSolenoid = new Solenoid(Constants.kCargoSolenoidId);  //we are using single solenoids, which are only controlled by a single boolean flag
    Solenoid hatchSolenoid = new Solenoid(Constants.kHatchSolenoidId);

    public Intake(){

    }

    public void setCargoIntakePower(double power){
        mCargo.set(ControlMode.PercentOutput, power, DemandType.ArbitraryFeedForward, Constants.intakeAFF);
    }

    public void extendCargoArms(){
        cargoSolenoid.set(true);
    }

    public void closeCargoArms(){
        cargoSolenoid.set(false);
    }

    public void grabHatchPanel(){
        hatchSolenoid.set(false);
    }

    public void releaseHatchPanel(){
        hatchSolenoid.set(true);
    }

    public void initDefaultCommand(){
    }
}