package frc.robot.subsystems; //Name the cargo intake ball fondler

import edu.wpi.first.wpilibj.DoubleSolenoid;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.command.Subsystem;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.DemandType;

public class Intake extends Subsystem{
    TalonSRX mCargoL = new TalonSRX(Constants.ballIntakeLID);
    TalonSRX mCargoR = new TalonSRX(Constants.ballIntakeRID);
    DoubleSolenoid cargoSolenoid = new DoubleSolenoid(Constants.kCargoSolenoidIdF, Constants.kCargoSolenoidIdR);  //Sike double solenoids
    DoubleSolenoid hatchSolenoid = new DoubleSolenoid(Constants.kHatchSolenoidIdF, Constants.kHatchSolenoidIdR);

    public Intake(){
        //mCargoL.setInverted(true);
        closeCargoArms();
        grabHatchPanel();
    }

    public void setCargoIntakePower(double power){
        mCargoL.set(ControlMode.PercentOutput, power, DemandType.ArbitraryFeedForward, Constants.intakeAFF);
        mCargoR.set(ControlMode.PercentOutput, power, DemandType.ArbitraryFeedForward, Constants.intakeAFF);
    }

    public void extendCargoArms(){
        cargoSolenoid.set(DoubleSolenoid.Value.kForward);
    }

    public void closeCargoArms(){
        cargoSolenoid.set(DoubleSolenoid.Value.kReverse);
    }

    public void grabHatchPanel(){
        hatchSolenoid.set(DoubleSolenoid.Value.kReverse);
    }

    public void releaseHatchPanel(){
        hatchSolenoid.set(DoubleSolenoid.Value.kForward);
    }

    public void initDefaultCommand(){
    }
}