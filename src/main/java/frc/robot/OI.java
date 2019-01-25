package frc.robot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.Constants;
import frc.robot.commands.*;

public class OI{
    public Joystick stick = new Joystick(Constants.kJoystickPort);

    public OI(){
        
    }
    
    public double getLeftXAxis(){
        return stick.getRawAxis(Constants.kJoystickLeftXAxis);
    }
    public double getLeftYAxis(){
        return stick.getRawAxis(Constants.kJoystickLeftYAxis);
    }
    public double getRightYAxis(){
        return stick.getRawAxis(Constants.kJoystickRightYAxis);
    }
    public double getRightXAxis(){
        return stick.getRawAxis(Constants.kJoystickRightXAxis);
    }
}
