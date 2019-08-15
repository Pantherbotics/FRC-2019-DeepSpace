package frc.robot;

import frc.robot.util.Setpoint;
import edu.wpi.first.wpilibj.SerialPort;

/*
----------------------------------  TICK PER FT: 323.8  ----------------------------------
-Calculated by averaging 4 trials where the start and end positions where measured along a 10 ft straight path
*/

public class Constants{
    public static final boolean IS_COMPETITION_ROBOT = false;
    //Velocity and Drivetrain PID Stuff
    public static final double kTicksPerFeet = 323.8;
    public static final double driveKP = 3.75;
    public static final double driveKI = 0;
    public static final double driveKD = 0.05;
    public static final double driveKF = 391.0/1023.0;
    //Robot Joystick
    public static final int JoystickPort = 0; //Switch
    public static final int JoystickLeftXAxis = 0;
    public static final int JoystickLeftYAxis = 1;
    public static final int JoystickRightXAxis = 2;
    public static final int JoystickRightYAxis = 5;
    public static final int PartnerJoyPort = 1; //
    public static final int PartnerJoyLeftXAxis = 0;
    public static final int PartnerJoyLeftYAxis = 1;
    public static final int PartnerJoyRightXAxis = 2;
    public static final int PartnerJoyRightYAxis = 3;
    public static final double deadband = 0.05;
    //Intaking
    public static final Setpoint ballIntake = new Setpoint(0.00, -9.875);
    public static final Setpoint diskIntake = new Setpoint(5.75, 0.0); //5.00
    public static final Setpoint diskIntakeLow = new Setpoint(5.25, 0);
    public static final Setpoint ballLoadingStation = new Setpoint(23.25, 10.0);
    //Cargo Ship
    public static final Setpoint ballCargoShip = new Setpoint(37.5, -15.0);
    public static final Setpoint hatchCargoShip = diskIntake;
    //Rocket
    public static final Setpoint lowRocketBall = new Setpoint(7.5, 10.0);
    public static final Setpoint mediumRocketBall = new Setpoint(34.25, 10.0);
    public static final Setpoint highRocketBall = new Setpoint(60.0, 10.0);

    public static final Setpoint lowRocketDisk = diskIntake;
    public static final Setpoint mediumRocketDisk = new Setpoint(33.25, 0.0); //33.25
    public static final Setpoint highRocketDisk = new Setpoint(59.25, 0.0); //60.5
    //YEET
    public static final Setpoint stowed = new Setpoint(0.0, 62.5);
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
    public static final int shoulderID = 5; //Maybe correct
    public static final int ballIntakeLID = 10; // 10 //Inter-
    public static final int ballIntakeRID = IS_COMPETITION_ROBOT ? 6 : 8; //8
    public static final int jeVoisID = 7;
    public static final int climbAID = 4;
    public static final int climbBID = 8;
    public static final int suctionID = 9;

    //Drivetrain
    public static final double wheelbase = 21.548; //inches (1.796ft)
    public static final double kDriveRamp = 0.25;
    public static final int kDrivePeakCurrentLimit = 80;
    public static final int kDrivePeakCurrentDuration = 10;
    public static final int kDriveContinuousCurrentLimit = 36;
    //Robot Elevator
    public static final double elevatorKP = 5.0;
    public static final double elevatorKI = 0.0;
    public static final double elevatorKD = 0.4;
    public static final double elevatorKF1 = 1023.0/1198.0; //1198 is the calculated maximum speed
    public static final double elevatorKF2 = 1023.0/900.0;
    public static final double elevatorAFF = 0.125;
    public static final int kElevatorCruiseSpeed = 1500;
    public static final int kElevatorAccelerationSpeed = 2000; //my sat score
    public static final int kElevMidway = 3700; //An elevator that plays a video of Japanese aircraft carriers getting bombed
    public static final int kElevatorMaxPos = 8500;
    public static final double elevPeriod = 0.01;

    public static int timeoutMS = 10;
    public static final int lowElev_ID = 0;
    public static final int highElev_ID = 1;
    //Shoulder [-17deg,
    public static final double shoulderKP = 20.0; //[-11, 72]
    public static final double shoulderKI = 0;
    public static final double shoulderKD = 3.0;
    public static final double shoulderKF = 1023.0/37.51;
    public static final int kShoulderCruiseSpeed = 37;   //maximum = 37
    public static final int kShoulderAccelerationSpeed = 30;
    public static final double shoulderAFF = 0.1; //At horizontal, 1.2/12
    public static final int kShoulderOffset = IS_COMPETITION_ROBOT ? 256 : 710;
    public static final double shoulderPeriod = 0.02;
    //Intake
    public static final double intakeAFF = -0.125;
    public static final int kCargoSolenoidIdF = 0;
    public static final int kCargoSolenoidIdR = 1;
    public static final int kHatchSolenoidIdF = 3;
    public static final int kHatchSolenoidIdR = 2;
    public static final int kIntakeSensorPort = 0;
    public static final int kHatchRange = IS_COMPETITION_ROBOT ? 1350 : 800;
    public static final int kCargoRange = IS_COMPETITION_ROBOT ? 2000 : 950;
    //Climber
    public static final int kDeploySolenoidID = 4;
    public static final double climbAFF = 0
            ;
    //Vision
    public static final int kVisionBaud = 115200;
    public static final SerialPort.Port kVisionPort = SerialPort.Port.kUSB1;
    public static final double kVisionOffset = 0.0523;
    //Ramsete
    public static final String PATH_LOCATION = "/home/lvuser/deploy/";
}