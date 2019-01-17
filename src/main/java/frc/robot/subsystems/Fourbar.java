package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Constants;

public class Fourbar extends Subsystem{
    double FF = 0;
    TalonSRX talonA = new TalonSRX(Constants.kFourbarA);
    TalonSRX talonB = new TalonSRX(Constants.kFourbarB);

    public void initPID(){
        talonA.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);
    }
    
    public void translate(){

    }

    public void initDefaultCommand(){
        //xd
    }
}