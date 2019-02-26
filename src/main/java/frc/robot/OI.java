package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.POVButton;
import frc.robot.Constants;
import frc.robot.commands.*;

public class OI{
    public Joystick stick = new Joystick(Constants.JoystickPort);
    public Joystick partnerStick = new Joystick(Constants.PartnerJoyPort);    
    //Joystick
    public JoystickButton buttonT = new JoystickButton(stick, 4); //Triangle
    public JoystickButton buttonS = new JoystickButton(stick, 1); //Square
    public JoystickButton buttonC = new JoystickButton(stick, 2); //Circle
    public JoystickButton buttonX = new JoystickButton(stick, 3); //X
    public JoystickButton bumperL = new JoystickButton(stick, 5); //Left Bumper
    public JoystickButton bumperR = new JoystickButton(stick, 6); //Right Bumper
    public JoystickButton triggerL = new JoystickButton(stick, 7); //Left Trigger
    public JoystickButton triggerR = new JoystickButton(stick, 8); //Right Trigger
    public JoystickButton buttonShare = new JoystickButton(stick, 9); //Share
    public JoystickButton buttonOption = new JoystickButton(stick, 10); //Option
    public JoystickButton buttonLJoy = new JoystickButton(stick, 11); //Press the left Joystick
    public JoystickButton buttonRJoy = new JoystickButton(stick, 12); //Press right Joystick
    public JoystickButton buttonPS4 = new JoystickButton(stick, 13); //PS4 Button
    public JoystickButton touchpad = new JoystickButton(stick, 14); //Hit touchpad

    //Partner Joystick
    public JoystickButton partnerButtonY = new JoystickButton(partnerStick, 4);
    public JoystickButton partnerButtonX = new JoystickButton(partnerStick, 1);
    public JoystickButton partnerButtonB = new JoystickButton(partnerStick, 3);
    public JoystickButton partnerButtonA = new JoystickButton(partnerStick, 2);
    public JoystickButton partnerBumperL = new JoystickButton(partnerStick, 5); //Left Bumper
    public JoystickButton partnerBumperR = new JoystickButton(partnerStick, 6); //Right Bumper
    public JoystickButton partnerTriggerL = new JoystickButton(partnerStick, 7); //Left Trigger
    public JoystickButton partnerTriggerR = new JoystickButton(partnerStick, 8); //Right Trigger
    public JoystickButton partnerBack = new JoystickButton(partnerStick, 9);
    public JoystickButton partnerStart = new JoystickButton(partnerStick, 10);
    public POVButton partnerButtonUp = new POVButton(partnerStick, 0);
    public POVButton partnerButtonRight = new POVButton(partnerStick, 90);
    public POVButton partnerButtonDown = new POVButton(partnerStick, 180);
    public POVButton partnerButtonLeft = new POVButton(partnerStick, 270);



    public OI(){ //Drive and Intake on stick, elevator and arm on partnerStick
        //Elevator + Arm (partner)
        partnerButtonY.whenPressed(new ToSetpoint(Constants.highRocketBall)); //work in progress
        partnerButtonX.whenPressed(new ToSetpoint(Constants.lowRocketBall));
        partnerButtonB.whenPressed(new ToSetpoint(Constants.mediumRocketBall));
        partnerButtonA.whenPressed(new ToSetpoint(Constants.ballIntake)); //Hatch Panel

        //partnerButtonUp.whenPressed(new ToSetpoint());
        //Intake (main)
        bumperL.whileHeld(new SuccDisk(false)); //Left Side Succ
        triggerL.whileHeld(new SuccDisk(true)); //true = in
        bumperR.whileHeld(new FondleBall(false)); //Right Side Fondle
        triggerR.whileHeld(new FondleBall(true)); //true = in

        partnerStart.whenPressed(new ZeroElevator());
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
        return -stick.getRawAxis(Constants.JoystickLeftYAxis);
    }
    public double getRightYAxis(){
        if(Math.abs(stick.getRawAxis(Constants.JoystickRightYAxis)) < Constants.deadband){
            return 0;
        }
        return -stick.getRawAxis(Constants.JoystickRightYAxis);
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
        //if(Math.abs(partnerStick.getRawAxis(Constants.PartnerJoyLeftYAxis)) < Constants.deadband){
         //   return 0;
        //}
        return -partnerStick.getRawAxis(Constants.PartnerJoyLeftYAxis);
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
        return -partnerStick.getRawAxis(Constants.PartnerJoyRightYAxis);
    }
}
