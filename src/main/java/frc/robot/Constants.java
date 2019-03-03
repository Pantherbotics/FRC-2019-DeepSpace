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
    public static final double deadband = 0.05;

    public static final Setpoint groundIntakeBall = new Setpoint(0.00, 17.5, -22.5);
    public static final Setpoint groundIntakeDisk = new Setpoint(0.00, 15.5, -20.0);
    public static final Setpoint linkReaction = new Setpoint(0.00, 30.0, 0.00);
    public static final Setpoint diskIntake = new Setpoint(0, 1.5, 47.5);
    public static final Setpoint diskIntake2 = new Setpoint(0, 5.0, 73.5);
    public static final Setpoint ballCargoShip = new Setpoint(25.0, 30.0, 0.00);
    public static final Setpoint ballCargoShipFlip = new Setpoint(17.5, 80.00, 168.0);
    public static final Setpoint backflip = new Setpoint(17.5, 35.0, 0.00);

    public static final Setpoint lowRocketBall = new Setpoint(0, 47.5, 30.0);
    public static final Setpoint mediumRocketBall = new Setpoint(27.5, 45.0, 30.0);
    public static final Setpoint highRocketBall = new Setpoint(52.5, 40.0, 30.0);
    public static final Setpoint lowRocketDisk = new Setpoint(0.00, -7.00, 73.5);
    public static final Setpoint mediumRocketDisk = new Setpoint(12.0, 30.00, 73.5);
    public static final Setpoint highRocketDisk = new Setpoint(39.0, 30.00, 73.5);

    public static final Setpoint outtakeLow = new Setpoint(0.0, -14.0, 73.5);
    public static final Setpoint outtakeMedium = new Setpoint(10.0, 25.0, 73.5);
    public static final Setpoint outtakeHigh = new Setpoint(0,0,0);
    //WTF IS GOING ON??
    public static final Setpoint testOne = new Setpoint(30, 0, 0);
    public static final Setpoint testTwo = new Setpoint(0, 0, 0);
    public static final Setpoint testThree = new Setpoint(0, 60, 10);

    public static final Setpoint highElevator = new Setpoint (55.5, 0, 0);
    public static final Setpoint elevatorDown = new Setpoint (0, 0, 0);

    //Elevator Increments
    public static final double mediumRocketDiskIncrement = 28.5;
    public static final double highRocketDiskIncrement = 55.5;
    public static final double mediumRocketBallIncrement = 44.4;
    public static final double highRocketBallIncrement = 66.0;
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
    public static final double elevatorKP = 5.0;
    public static final double elevatorKI = 0.0;
    public static final double elevatorKD = 0.4;
    public static final double elevatorKF1 = 1023.0/1198.0; //1198 is the calculated maximum speed
    public static final double elevatorKF2 = 1023.0/900.0;
    public static final double elevatorAFF = 0.125;
    public static final int kElevatorCruiseSpeed = 1198;
    public static final int kElevatorAccelerationSpeed = 450; //my sat score
    public static final int kElevMidway = 3700; //An elevator that plays a video of Japanese aircraft carriers getting bombed
    public static final int kElevatorMaxPos = 7975;

    public static int timeoutMS = 10;
    public static final int lowElev_ID = 0;
    public static final int highElev_ID = 1;
    //Shoulder [-17deg,
    public static final double shoulderKP = 10.0;
    public static final double shoulderKI = 0;
    public static final double shoulderKD = 2.75;
    public static final double shoulderKF = 1023.0/37.51;
    public static final int kShoulderCruiseSpeed = 37;   //maximum = 37
    public static final int kShoulderAccelerationSpeed = 10;
    public static final double shoulderAFF = 0.166; //At horizontal, 1.5/12


j    public static final int kShoulderOffset = IS_COMPETITION_ROBOT ? 260 : 0;
    public static final double kIncrementDegrees = 20;
    //Wrist [-35deg, 87deg]
    public static final double wristKP = 20.0;
    public static final double wristKI = 0;
    public static final double wristKD = 0.35;
    public static final double wristKF = 1023.0/37.51;
    public static final int kWristCruiseSpeed = 37;  //maximum = 37
    public static final int kWristAccelerationSpeed = 60;
    public static final double wristAFF = 0.125; //1.5v/12v
    public static final int kWristOffset = IS_COMPETITION_ROBOT ? 327 : 0;
    //Intake
    public static final double succLimit = -0.4; //Percentage
    public static final double fondleLimit = 0.4;
    public static final double intakeAFF = 0.1;
}
