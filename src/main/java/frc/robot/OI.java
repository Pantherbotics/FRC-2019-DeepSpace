package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.POVButton;
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
    public JoystickButton leftJoy = new JoystickButton(stick, 15); //Hit the Joystick
    public JoystickButton rightJoy = new JoystickButton(stick, 16);
    public POVButton POVU = new POVButton(stick, 0);
    public POVButton POVR = new POVButton(stick, 90);
    public POVButton POVD = new POVButton(stick, 180);
    public POVButton POVL = new POVButton(stick, 270);

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
    public JoystickButton partnerLeftJoy = new JoystickButton(partnerStick, 11);
    public JoystickButton partnerRightJoy = new JoystickButton(partnerStick, 12);
    public POVButton partnerPOVU = new POVButton(partnerStick, 0);  //Up
    public POVButton partnerPOVR = new POVButton(partnerStick, 90); //Right
    public POVButton partnerPOVD = new POVButton(partnerStick, 180);//Down
    public POVButton partnerPOVL = new POVButton(partnerStick, 270);//Left
    int POV;
    int partnerPOV;
    boolean isDisk;


    public OI(){ //Drive and Intake on stick, elevator and arm on partnerStick
        //Rocket Cargo
        partnerButtonY.whenPressed(new ToSetpoint(Constants.highRocketBall)); //work in progress
        partnerButtonX.whenPressed(new ToSetpoint(Constants.lowRocketBall));
        partnerButtonB.whenPressed(new ToSetpoint(Constants.mediumRocketBall));
        partnerButtonA.whenPressed(new ToSetpoint(Constants.ballIntake)); //Hatch Panel
        //Cargo Ship
        partnerBumperR.whenPressed(new ToSetpoint(Constants.ballCargoShip));
        partnerTriggerR.whenPressed(new ToSetpoint(Constants.ballLoadingStation));
        //partnerBumperR.whenPressed(new ToSetpoint(Constants.ballCargoShipFlip));
        //partnerTriggerR.whenPressed(new ToSetpoint(Constants.backflip));
        //Hatch Panel
        partnerPOVU.whenPressed(new ToSetpoint(Constants.highRocketDisk));
        partnerPOVR.whenPressed(new ToSetpoint(Constants.lowRocketDisk));
        partnerPOVL.whenPressed(new ToSetpoint(Constants.mediumRocketDisk));
        partnerPOVD.whenPressed(new ToSetpoint(Constants.diskIntake));
        //Yeetus that Fetus
        partnerRightJoy.whenPressed(new ToSetpoint(Constants.YEEEET));
        //Drivetrain Test
        POVU.whileHeld(new OpenLoopConstant(1.0));
        POVL.whileHeld(new OpenLoopConstant(0.8));
        POVR.whileHeld(new OpenLoopConstant(0.6));
        POVD.whileHeld(new OpenLoopConstant(0.4));
        buttonShare.whileHeld(new OpenLoopConstant(0.2));
        touchpad.whileHeld(new OpenLoopConstant(0.0));
        /*
        buttonT.whileHeld(new PrepareHatchGrab(-1));
        buttonT.whenPressed(new ToSetpoint(Constants.outtakeHigh));
        buttonS.whileHeld(new PrepareHatchGrab(-1));
        buttonS.whenPressed(new ToSetpoint(Constants.outtakeMedium));
        buttonC.whileHeld(new PrepareHatchGrab(-1));
        buttonC.whenPressed(new ToSetpoint(Constants.outtakeLow));
        */
        //Intake (main)
        bumperL.whenPressed(new PrepareHatchGrab()); //Left Side Succ
        triggerL.whenPressed(new HatchGrab()); //true = in
        bumperR.whileHeld(new IntakeCargo(0.75)); //Right Side Fondle
        triggerR.whileHeld(new IntakeCargo(-0.6)); //in = negative
        buttonT.whenPressed(new HatchPush());
        //Prepare for yeet
        partnerBack.whenPressed(new ToSetpoint(Constants.stowed));
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
