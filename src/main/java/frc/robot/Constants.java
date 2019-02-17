package frc.robot;

public class Constants{
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
    //Robot Talons
    public static final int kLeftA = 13;
    public static final int kLeftB = 14;
    public static final int kLeftC = 15;
    public static final int kRightA = 2;
    public static final int kRightB = 1;
    public static final int kRightC = 0;
    public static final int kElevatorA = 12;
    public static final int kElevatorB = 3;
    public static final int kshoulder = 4; //Change in Phoenix tuner
    public static final int kwrist = 5;
    public static final int kBallFondler = 8; //Inter-
    public static final int kDiskSuccer = 10;  //changeable
    //Drivetrain
    public static final double driveRamp = 0.25;
    public static final int drivePeakCurrentLimit = 80;
    public static final int drivePeakCurrentDuration = 10;
    public static final int driveContinuousCurrentLimit = 36;
    //Drivetrain PID
    public static final double driveKP = 4.5;
    public static final double driveKI = 0;
    public static final double driveKD = 0.05;
    public static final double driveKF = 0.50979366385; //Probably wrong
    //Robot Elevator
    public static final double elevatorKP = 0.5;
    public static final double elevatorKI = 0.0;
    public static final double elevatorKD = 0.05;
    public static final double elevatorKF1 = 1023.0/1198.0; //1198 is the calculated maximum speed
    public static final double elevatorKF2 = 1023.0/2000.0;
    public static final double elevatorAFF1 = 30;
    public static final double elevatorAFF2 = 40;
    public static final int elevatorCruiseSpeedUp = 1198;
    public static final int elevatorAccelerationSpeedUp = 1500;
    public static final int elevatorCruiseSpeedDown = 700;
    public static final int elevatorAccelerationSpeedDown = 700;
    public static final int elevMidway = 3700; //An elevator that plays a video of Japanese aircraft carriers getting bombed
    public static final int kElevatorMaxPos = 7950;

    public static int timeoutMS = 10;
    public static final int lowElev_ID = 0;
    public static final int highElev_ID = 1;
    //Arm
    public static final double shoulderKP = 20.0;
    public static final double shoulderKI = 0;
    public static final double shoulderKD = 0;
    public static final double shoulderKF = 1023.0/37.51;
    public static final int shoulderCruiseSpeed = 37;
    public static final int shoulderAccelerationSpeed = 100;
    public static final double shoulderAFF = 0.0; //At horzontal, 0.52 = 44.33

    public static final double wristKP = 20.0;
    public static final double wristKI = 0;
    public static final double wristKD = 0;
    public static final double wristKF = 1023.0/37.51;
    public static final int wristCruiseSpeed = 19;
    public static final int wristAccelerationSpeed = 60;
    public static final double wristAFF = 0.0; //0.50 = 42.625
    public static final int shoulder_ID = 0;
    public static final int wrist_ID = 1;

    public static final double encoder2Rad = 0.00981747704;
    public static final double sin2Encoder = 2736.97905189;
    public static final int intakeDeathZone = 1680; //13.5 inches
    public static final double elev2Edge = 18.5;
    public static final int shoulderOffset = -533;
    public static final int wristOffset = -333;
    public static final int shoulder2WristOffset = 20;
}
