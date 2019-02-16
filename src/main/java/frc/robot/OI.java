package frc.robot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
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
    int partnerPOV;
    int currentElev, currentShoulder, currentWrist;


    public OI(){ //Drive and Intake on stick, elevator and arm on partnerStick
        //Elevator + Arm
        partnerButtonY.whenPressed(new ToSetpoint(Constants.elevSetpoint[3], Constants.shoulderSetpoint[3], Constants.wristSetpoint[1])); //work in progress
        partnerButtonX.whenPressed(new ToSetpoint(Constants.elevSetpoint[2], Constants.shoulderSetpoint[2], Constants.wristSetpoint[1]));
        partnerButtonB.whenPressed(new ToSetpoint(Constants.elevSetpoint[1], Constants.shoulderSetpoint[1], Constants.wristSetpoint[1]));
        partnerButtonA.whenPressed(new ToSetpoint(Constants.elevSetpoint[0], Constants.shoulderSetpoint[0], Constants.wristSetpoint[3])); //Hatch Panel
        //Intake
        partnerBumperL.whileHeld(new SuccDisk(false)); //Left Side Succ
        partnerTriggerL.whileHeld(new SuccDisk(true));
        partnerBumperR.whileHeld(new FondleBall(false)); //Right Side Fondle
        partnerTriggerR.whileHeld(new FondleBall(true));

        partnerStart.whenPressed(new ZeroElevator());
    }

    //POV
    public void checkPartnerPOV(){ //wtf is this
        partnerPOV = partnerStick.getPOV();
        switch(partnerPOV){
            case 0:
                new ToSetpoint(Constants.elevSetpoint[2], 0, 0).start(); //Elev down, Cargo (Sicko) Mode
                currentShoulder = 0;
                currentWrist = 0;
            case 90:
                new ToSetpoint(Constants.elevSetpoint[4], currentShoulder, currentWrist).start();
            case 180:
                new ToSetpoint(Constants.elevSetpoint[3], 0, 0).start();
                currentShoulder = 0;
                currentWrist = 0;   
            case 270:
                new ToSetpoint(Constants.elevSetpoint[0], currentShoulder, currentWrist).start();
        }
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
