package frc.robot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.Constants;
import frc.robot.commands.*;

public class OI{
    double deadband = 0.1;
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
    public double getRightYAxis(){;
        return stick.getRawAxis(Constants.JoystickRightYAxis);
    }
    public double getRightXAxis(){;
        return stick.getRawAxis(Constants.JoystickRightXAxis);
    }
/*
    public double getPartnerLeftXAxis(){
        
    }
    public double getPartnerLeftYAxis(){
        
    }
    public double getPartnerRightXAxis(){
        
    }
    public double getPartnerRightYAxis(){
        
    }*/
}
