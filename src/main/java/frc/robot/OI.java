package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.POVButton;
import frc.robot.commands.*;

public class OI{
    public Joystick stick = new Joystick(Constants.JoystickPort); //Switch controller
    public Joystick partnerStick = new Joystick(Constants.PartnerJoyPort);    
    //Joystick
    public JoystickButton buttonX = new JoystickButton(stick, 4); //X
    public JoystickButton buttonY = new JoystickButton(stick, 1); //Y
    public JoystickButton buttonA = new JoystickButton(stick, 3); //A
    public JoystickButton buttonB = new JoystickButton(stick, 2); //B
    public JoystickButton bumperL = new JoystickButton(stick, 5); //Left Bumper
    public JoystickButton bumperR = new JoystickButton(stick, 6); //Right Bumper
    public JoystickButton triggerL = new JoystickButton(stick, 7); //Left Trigger
    public JoystickButton triggerR = new JoystickButton(stick, 8); //Right Trigger
    public JoystickButton buttonMinus = new JoystickButton(stick, 9); //-
    public JoystickButton buttonPlus = new JoystickButton(stick, 10); //+
    public JoystickButton buttonLJoy = new JoystickButton(stick, 11); //Press the left Joystick
    public JoystickButton buttonRJoy = new JoystickButton(stick, 12); //Press right Joystick
    public JoystickButton buttonHome = new JoystickButton(stick, 13); //Home button
    public JoystickButton buttonCapture = new JoystickButton(stick, 14); //Capture button
    public POVButton POVU = new POVButton(stick, 0);
    public POVButton POVR = new POVButton(stick, 90);
    public POVButton POVD = new POVButton(stick, 180);
    public POVButton POVL = new POVButton(stick, 270);

    //Partner Joystick
    public JoystickButton partnerButtonX = new JoystickButton(partnerStick, 4); //X
    public JoystickButton partnerButtonY = new JoystickButton(partnerStick, 1); //Y
    public JoystickButton partnerButtonA = new JoystickButton(partnerStick, 3); //A
    public JoystickButton partnerButtonB = new JoystickButton(partnerStick, 2); //B
    public JoystickButton partnerBumperL = new JoystickButton(partnerStick, 5); //Left Bumper
    public JoystickButton partnerBumperR = new JoystickButton(partnerStick, 6); //Right Bumper
    public JoystickButton partnerTriggerL = new JoystickButton(partnerStick, 7); //Left Trigger
    public JoystickButton partnerTriggerR = new JoystickButton(partnerStick, 8); //Right Trigger
    public JoystickButton partnerButtonMinus = new JoystickButton(partnerStick, 9); //-
    public JoystickButton partnerButtonPlus = new JoystickButton(partnerStick, 10); //+
    public JoystickButton partnerButtonLJoy = new JoystickButton(partnerStick, 11); //Press the left Joystick
    public JoystickButton partnerButtonRJoy = new JoystickButton(partnerStick, 12); //Press right Joystick
    public JoystickButton partnerButtonHome = new JoystickButton(partnerStick, 13); //Home button
    public JoystickButton partnerButtonCapture = new JoystickButton(partnerStick, 14); //Capture button
    public POVButton partnerPOVU = new POVButton(partnerStick, 0);  //Up
    public POVButton partnerPOVR = new POVButton(partnerStick, 90); //Right
    public POVButton partnerPOVD = new POVButton(partnerStick, 180);//Down
    public POVButton partnerPOVL = new POVButton(partnerStick, 270);//Left
    int POV;
    int partnerPOV;
    boolean isDisk;


    public OI(){ //Drive and Intake on stick, elevator and arm on partnerStick
        //Rocket Cargo
        partnerButtonX.whenPressed(new ToSetpoint(Constants.highRocketBall)); //work in progress
        partnerButtonY.whenPressed(new ToSetpoint(Constants.lowRocketBall));
        partnerButtonA.whenPressed(new ToSetpoint(Constants.mediumRocketBall));
        partnerButtonB.whenPressed(new ToSetpoint(Constants.ballIntake)); //Hatch Panel
        //Cargo Ship
        partnerBumperR.whenPressed(new ToSetpoint(Constants.ballCargoShip));
        partnerTriggerR.whenPressed(new ToSetpoint(Constants.ballLoadingStation));
        //Hatch Panel
        partnerPOVU.whenPressed(new ToSetpoint(Constants.highRocketDisk));
        partnerPOVR.whenPressed(new ToSetpoint(Constants.lowRocketDisk));
        partnerPOVL.whenPressed(new ToSetpoint(Constants.mediumRocketDisk));
        partnerPOVD.whenPressed(new ToSetpoint(Constants.diskIntakeLow));
        partnerPOVD.whenPressed(new AutoHatchGrab());
        //Intake (main)
        bumperL.whenPressed(new PrepareHatchGrab()); //Left Side Succ
        triggerL.whenPressed(new HatchGrab()); //true = in
        bumperR.whileHeld(new IntakeCargo(1.0)); //Right Side Fondle 0.75
        triggerR.whileHeld(new IntakeCargo(-0.8)); //in = negative -0.6
        buttonX.whenPressed(new HatchPush());
        //Prepare for yeet
        partnerBumperL.whenPressed(new ToSetpoint(Constants.stowed));
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
        /*if(Math.abs(partnerStick.getRawAxis(Constants.PartnerJoyRightYAxis)) < Constants.deadband){
            return 0;
        }*/
        return -partnerStick.getRawAxis(Constants.PartnerJoyRightYAxis);
    }
}
