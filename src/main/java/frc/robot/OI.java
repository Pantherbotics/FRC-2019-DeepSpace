package frc.robot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.Constants;
import frc.robot.commands.*;

public class OI{
    double deadband = 0.05;
    public Joystick stick = new Joystick(Constants.JoystickPort);
    public Joystick partnerStick = new Joystick(Constants.PartnerJoyPort);    

    public OI(){
        
    }
    
    public double getLeftXAxis(){ //Deadband is of kill
        if(Math.abs(stick.getRawAxis(Constants.PartnerJoyLeftXAxis)) < deadband){
            return 0;
        }
        return stick.getRawAxis(Constants.JoystickLeftXAxis);
    }
    public double getLeftYAxis(){
        if(Math.abs(stick.getRawAxis(Constants.PartnerJoyLeftYAxis)) < deadband){
            return 0;
        }
        return stick.getRawAxis(Constants.JoystickLeftYAxis);
    }
    public double getRightYAxis(){
        if(Math.abs(stick.getRawAxis(Constants.PartnerJoyRightYAxis)) < deadband){
            return 0;
        }
        return stick.getRawAxis(Constants.JoystickRightYAxis);
    }
    public double getRightXAxis(){
        if(Math.abs(stick.getRawAxis(Constants.PartnerJoyRightXAxis)) < deadband){
            return 0;
        }
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
