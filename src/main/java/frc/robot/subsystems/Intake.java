package frc.robot.subsystems; //Name the cargo intake ball fondler

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.command.Subsystem;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.DemandType;
import frc.robot.util.Units;

public class Intake extends Subsystem{
    TalonSRX mCargoL = new TalonSRX(Constants.ballIntakeLID);
    TalonSRX mCargoR = new TalonSRX(Constants.ballIntakeRID);
    DoubleSolenoid cargoSolenoid = new DoubleSolenoid(Constants.kCargoSolenoidIdF, Constants.kCargoSolenoidIdR);  //Sike double solenoids
    DoubleSolenoid hatchSolenoid = new DoubleSolenoid(Constants.kHatchSolenoidIdF, Constants.kHatchSolenoidIdR);

    private AnalogInput distanceSensor = new AnalogInput(Constants.kIntakeSensorPort);

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

    public boolean getHatchPanel(){
        if(hatchSolenoid.get() == DoubleSolenoid.Value.kReverse){
            return true;
        }
        return false;
    }

    public boolean getCargoArms(){
        if(cargoSolenoid.get() == DoubleSolenoid.Value.kReverse){
            return true;
        }
        return false;
    }

    public boolean withinIntakeRange(int range){
        return getIntakeSensorRaw() >= range;
    }

    public double getIntakeSensor(){
        return Units.analogRawToInches(distanceSensor.getAverageValue());
    }

    public double getIntakeSensorRaw(){
        return distanceSensor.getAverageValue();
    }
    public void initDefaultCommand(){
    }
}