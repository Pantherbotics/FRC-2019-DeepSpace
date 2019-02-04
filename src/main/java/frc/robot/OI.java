package frc.robot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.Constants;
import frc.robot.commands.*;

public class OI{
    public Joystick stick = new Joystick(Constants.JoystickPort);
    public Joystick partnerStick = new Joystick(Constants.JoystickPort);    
    //Joystick
    public JoystickButton buttonT = new JoystickButton(stick, 0); //Triangle
    public JoystickButton buttonS = new JoystickButton(stick, 1); //Square
    public JoystickButton buttonC = new JoystickButton(stick, 2); //Circle
    public JoystickButton buttonX = new JoystickButton(stick, 3); //X

    //Partner Joystick
    public JoystickButton partnerButtonY = new JoystickButton(partnerStick, 0);
    public JoystickButton partnerButtonX = new JoystickButton(partnerStick, 1);
    public JoystickButton partnerButtonB = new JoystickButton(partnerStick, 2);
    public JoystickButton partnerButtonA = new JoystickButton(partnerStick, 3);
    public JoystickButton partnerButtonU = new JoystickButton(partnerStick, 4); //Up
    public JoystickButton partnerButtonL = new JoystickButton(partnerStick, 5); //Left
    public JoystickButton partnerButtonR = new JoystickButton(partnerStick, 6); //Right
    public JoystickButton partnerButtonD = new JoystickButton(partnerStick, 7); //Down
    public JoystickButton partnerBumperL = new JoystickButton(partnerStick, 8); //Left Bumper
    public JoystickButton partnerBumperR = new JoystickButton(partnerStick, 9); //Right Bumper
    public JoystickButton partnerTriggerL = new JoystickButton(partnerStick, 10); //Left Trigger
    public JoystickButton partnerTriggerR = new JoystickButton(partnerStick, 11); //Right Trigger

    public OI(){
        partnerButtonY.whenPressed(new ToSetpoint(0, 0)); //work in progress
        partnerButtonX.whenPressed(new ToSetpoint(0, 0));
        partnerButtonB.whenPressed(new ToSetpoint(0, 0));
        partnerButtonA.whenPressed(new ToSetpoint(0, 0));
    }

    //Joystick
    public double getLeftXAxis(){ //Deadband is of kill
        if(Math.abs(stick.getRawAxis(Constants.JoystickLeftXAxis)) < Constants.deadband){
            return 0;
        }
        return stick.getRawAxis(Constants.JoystickLeftXAxis);
    }
    public double getLeftYAxis(){
        if(Math.abs(stick.getRawAxis(Constants.JoystickLeftYAxis)) < Constants.deadband){
            return 0;
        }
        return stick.getRawAxis(Constants.JoystickLeftYAxis);
    }
    public double getRightYAxis(){
        if(Math.abs(stick.getRawAxis(Constants.JoystickRightYAxis)) < Constants.deadband){
            return 0;
        }
        return stick.getRawAxis(Constants.JoystickRightYAxis);
    }
    public double getRightXAxis(){
        if(Math.abs(stick.getRawAxis(Constants.JoystickRightXAxis)) < Constants.deadband){
            return 0;
        }
        return stick.getRawAxis(Constants.JoystickRightXAxis);
    }


    //Partner Joystick
    public double getPartnerLeftXAxis(){
        if(Math.abs(partnerStick.getRawAxis(Constants.PartnerJoyLeftXAxis)) < Constants.deadband){
            return 0;
        }
        return partnerStick.getRawAxis(Constants.PartnerJoyLeftXAxis);
    }
    public double getPartnerLeftYAxis(){
        if(Math.abs(partnerStick.getRawAxis(Constants.PartnerJoyLeftYAxis)) < Constants.deadband){
            return 0;
        }
        return partnerStick.getRawAxis(Constants.PartnerJoyLeftYAxis);
    }
    public double getPartnerRightXAxis(){
        if(Math.abs(partnerStick.getRawAxis(Constants.PartnerJoyRightXAxis)) < Constants.deadband){
            return 0;
        }
        return partnerStick.getRawAxis(Constants.PartnerJoyRightXAxis);
    }
    public double getPartnerRightYAxis(){
        if(Math.abs(partnerStick.getRawAxis(Constants.PartnerJoyRightYAxis)) < Constants.deadband){
            return 0;
        }
        return partnerStick.getRawAxis(Constants.PartnerJoyRightYAxis);
    }
}
