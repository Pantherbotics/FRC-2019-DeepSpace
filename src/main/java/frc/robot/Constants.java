package frc.robot;

import frc.robot.util.Setpoint;
import edu.wpi.first.wpilibj.SerialPort;

public class Constants{
    public static final boolean IS_COMPETITION_ROBOT = true;

    //Robot Joystick
    public static final int JoystickPort = 0; //Need new PS4 wire
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
    //Intaking
    public static final Setpoint ballIntake = new Setpoint(0.00, -11.67);
    public static final Setpoint diskIntake = new Setpoint(7.63, 0.0);
    //Cargo Ship
    public static final Setpoint ballCargoShip = new Setpoint(25.0, 30.0);
    public static final Setpoint hatchCargoShip = diskIntake;
    //Rocket
    public static final Setpoint lowRocketBall = new Setpoint(7.63, 10.0);
    public static final Setpoint mediumRocketBall = new Setpoint(35.75, 10.0);
    public static final Setpoint highRocketBall = new Setpoint(63.0, 10.0);

    public static final Setpoint lowRocketDisk = diskIntake;
    public static final Setpoint mediumRocketDisk = new Setpoint(35.75, 0.0);
    public static final Setpoint highRocketDisk = new Setpoint(63.0, 0.0);


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
    public static final int ballIntakeLID = 10; //Inter-
    public static final int ballIntakeRID = 8;

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
    public static final double shoulderKP = 17.5; //[-11, 72]
    public static final double shoulderKI = 0;
    public static final double shoulderKD = 3.0;
    public static final double shoulderKF = 1023.0/37.51;
    public static final int kShoulderCruiseSpeed = 37;   //maximum = 37
    public static final int kShoulderAccelerationSpeed = 15;
    public static final double shoulderAFF = 0.1; //At horizontal, 1.5/12
    public static final int kShoulderOffset = IS_COMPETITION_ROBOT ? 565 : 0;
    //Intake
    public static final double fondleLimit = 0.4;
    public static final double intakeAFF = -0.1;
    public static final int kCargoSolenoidId = 0;
    public static final int kHatchSolenoidId = 1;
    //Vision
    public static final int kVisionBaud = 115200;
    public static final SerialPort.Port kVisionPort = SerialPort.Port.kUSB1;
    public static final String PATH_LOCATION = "/home/lvuser/deploy/";
}
