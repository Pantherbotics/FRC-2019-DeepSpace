package frc.robot.subsystems; //Name the cargo intake ball fondler

import frc.robot.Constants;
import edu.wpi.first.wpilibj.command.Subsystem;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class Intake extends Subsystem{
    TalonSRX mBallFondler = new TalonSRX(Constants.kBallFondler);
    TalonSRX mDiskSuccer = new TalonSRX(Constants.kDiskSuccer);
    
    public Intake(){

    }

    public void initDefaultCommand(){

    }
}