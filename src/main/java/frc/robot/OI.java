package frc.robot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.Constants;
import frc.robot.commands.*;

public class OI{
    public Joystick stick = new Joystick(Constants.JoystickPort);
    public Joystick partnerStick = new Joystick(Constants.PartnerJoyPort);    

    public OI(){
        
    }
    
    public double getLeftXAxis(){
        return stick.getRawAxis(Constants.JoystickLeftXAxis);
    }
    public double getLeftYAxis(){
        return stick.getRawAxis(Constants.JoystickLeftYAxis);
    }
    public double getRightYAxis(){
        return stick.getRawAxis(Constants.JoystickRightYAxis);
    }
    public double getRightXAxis(){
        return stick.getRawAxis(Constants.JoystickRightXAxis);
    }

    public double getPartnerLeftXAxis(){
        return partnerStick.getRawAxis(Constants.PartnerJoyLeftXAxis);
    }
    public double getPartnerLeftYAxis(){
        return partnerStick.getRawAxis(Constants.PartnerJoyLeftYAxis);
    }
    public double getPartnerRightXAxis(){
        return partnerStick.getRawAxis(Constants.PartnerJoyRightXAxis);
    }
    public double getPartnerRightYAxis(){
        return partnerStick.getRawAxis(Constants.PartnerJoyRightYAxis);
    }
}
