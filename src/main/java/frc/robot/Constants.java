package frc.robot;

import frc.robot.util.Setpoint;

public class Constants{
    public static final boolean IS_COMPETITION_ROBOT = true;


    //Robot Joystick
    public static final int JoystickPort = 0; //PS4 Why do they cost $50 dollars ree
    public static final int JoystickLeftXAxis = 0;
    public static final int JoystickLeftYAxis = 1;
    public static final int JoystickRightXAxis = 2;
    public static final int JoystickRightYAxis = 5;
    public static final int PartnerJoyPort = 1; //X-Box
    public static final int PartnerJoyLeftXAxis = 0;
    public static final int PartnerJoyLeftYAxis = 1;
    public static final int PartnerJoyRightXAxis = 2;
    public static final int PartnerJoyRightYAxis = 3;
    //Other Joystick Stuff
    public static final double deadband = 0.05;
    public static final int[] elevSetpoint =
                                       {0, //bottom
                                        2200, //Flip Pos
                                        3700, //carrage at top of level 1
                                        6000, //idk
                                        7950  //top of level 2
                                       };
    public static final int[] shoulderSetpoint = //These probably shouldn't be in numerical order
                                       {-30, //Hatch Panel Intake                    0
                                        30,  //Angled so the intake is on the ground 1
                                        50,  //Design god Aaron Fang has forsaken me 2
                                        100, //And I want to kashoot myself          3
                                        150, //lmao
                                        220, //ERECTIN                               4
                                       };
    public static final int[] wristSetpoint = //why
                                       {-45, //Grounded
                                        0,   //Flat
                                        100, //delet
                                        180, //Hatch Panel Intake
                                        210, //90 deg
                                       };

    public static final Setpoint ballIntake = new Setpoint(0.5, 15.15, -9.85); //u
    public static final Setpoint diskIntake = new Setpoint(0, -17.75, 106.5); //u
    public static final Setpoint diskIntakeGround = new Setpoint(0, 11.26, 0); //u
    public static final Setpoint ballCargoShip = new Setpoint(0, 79.82, 30.27);
    public static final Setpoint diskCargoShip = new Setpoint(0, -20.33, 17.41);
    public static final Setpoint lowRocketBall = new Setpoint(0, 60.2, 39.4); //u
    public static final Setpoint mediumRocketBall = new Setpoint(44.0, 0, 39.4); //u
    public static final Setpoint highRocketBall = new Setpoint(64, 24.5, 39.4); //u

    //WTF IS GOING ON??
    public static final Setpoint testOne = new Setpoint(0, 60, 0);
    public static final Setpoint testTwo = new Setpoint(0, 0, 15);
    public static final Setpoint testThree = new Setpoint(0, 60, 10);

    public static final Setpoint highElevator = new Setpoint (55.5, 0, 0);
    public static final Setpoint elevatorDown = new Setpoint (0, 0, 0);

    //Elevator Increments
    public static final double mediumRocketDisk = 28.5;
    public static final double highRocketDisk = 55.5;
    public static final double mediumRocketBalll = 44.4;
    public static final double highRocketBalll = 66.0;
    //Robot Talons
    public static final int leftAID = 13;
    public static final int leftBID = 14;
    public static final int leftCID = 15;
    public static final int rightAID = 2;
    public static final int rightBID = 1;
    public static final int rightCID = 0;
    public static final int elevatorAID = 12;
    public static final int elevatorBID = 3;
    public static final int shoulderID = 4; //Change in Phoenix tuner
    public static final int wristID = 5;
    public static final int ballIntakeID = 8; //Inter-
    public static final int diskIntakeID = 10;  //changeable

    //Drivetrain
    public static final double kDriveRamp = 0.25;
    public static final int kDrivePeakCurrentLimit = 80;
    public static final int kDrivePeakCurrentDuration = 10;
    public static final int kDriveContinuousCurrentLimit = 36;
    //Drivetrain PID
    public static final double driveKP = 4.5;
    public static final double driveKI = 0;
    public static final double driveKD = 0.05;
    public static final double driveKF = 0.50979366385; //Probably wrong
    //Robot Elevator
    public static final double elevatorKP = 3.0;
    public static final double elevatorKI = 0.0;
    public static final double elevatorKD = 0.02;
    public static final double elevatorKF1 = 1023.0/1198.0; //1198 is the calculated maximum speed
    public static final double elevatorKF2 = 1023.0/900.0;
    public static final double elevatorAFF = 0.06;
    public static final int kElevatorCruiseSpeed = 1198;
    public static final int kElevatorAccelerationSpeed = 1000; //my sat score
    public static final int kElevMidway = 3700; //An elevator that plays a video of Japanese aircraft carriers getting bombed
    public static final int kElevatorMaxPos = 7950;

    public static int timeoutMS = 10;
    public static final int lowElev_ID = 0;
    public static final int highElev_ID = 1;
    //Shoulder
    public static final double shoulderKP = 15.0;
    public static final double shoulderKI = 0;
    public static final double shoulderKD = 0;
    public static final double shoulderKF = 1023.0/37.51;
    public static final int kShoulderCruiseSpeed = 37;   //maximum = 37
    public static final int kShoulderAccelerationSpeed = 60;
    public static final double shoulderAFF = 0.04166; //At horzontal, 0.52 = 44.33
    public static final int kShoulderOffset = IS_COMPETITION_ROBOT ? 290 : 0;
    public static final double kIncrementDegrees = 20;
    //Wrist
    public static final double wristKP = 10.0;
    public static final double wristKI = 0;
    public static final double wristKD = 0;
    public static final double wristKF = 1023.0/37.51;
    public static final int kWristCruiseSpeed = 37;  //maximum = 37
    public static final int kWristAccelerationSpeed = 60;
    public static final double wristAFF = 0.04166; //0.50v/12v
    public static final int kWristOffset = IS_COMPETITION_ROBOT ? 410 : 0;
    //Intake
    public static final double succLimit = -0.4; //Percentage
    public static final double fondleLimit = 0.4;
    public static final double intakeAFF = 0.1;
}
