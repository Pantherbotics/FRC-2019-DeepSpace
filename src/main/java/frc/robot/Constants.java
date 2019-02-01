package frc.robot;

public class Constants{
    //Robot Joystick
    public static final int JoystickPort = 0; //PS4
    public static final int JoystickLeftXAxis = 0;
    public static final int JoystickLeftYAxis = 1; 
    public static final int JoystickRightXAxis = 2; 
    public static final int JoystickRightYAxis = 5;
    public static final int PartnerJoyPort = 1; //X-Box
    public static final int PartnerJoyLeftXAxis = 0;
    public static final int PartnerJoyLeftYAxis = 1;
    public static final int PartnerJoyRightXAxis = 2;
    public static final int PartnerJoyRightYAxis = 3;
    //Robot Talons
    public static final int kLeftA = 15;
    public static final int kLeftB = 14;
    public static final int kLeftC = 13;
    public static final int kRightA = 0;
    public static final int kRightB = 1;
    public static final int kRightC = 2;
    public static final int kElevatorA = 5;
    public static final int kElevatorB = 6;
    public static final int kArmA = 7;
    public static final int kArmB = 8;
    //Drivetrain
    public static final double driveRamp = 0.5;
    public static final int drivePeakCurrentLimit = 80;
    public static final int drivePeakCurrentDuration = 10;
    public static final int driveContinuousCurrentLimit = 36;
    //Drivetrain PID
    public static final double DRIVE_P = 0;
    public static final double DRIVE_I = 0;
    public static final double DRIVE_D = 0.00;
    public static final double DRIVE_F = 0.49169110459;
    //Robot Elevator
    public static final double kElevator_P = 0;
    public static final double kElevator_I = 0;
    public static final double kElevator_D = 0;
    public static final double kElevator_F1 = 0;
    public static final double kElevator_F2 = 0;
    public static final int kElevatorTimeoutMS = 0;
    public static final int ELEVATOR_CURRENT_LIMIT = 10;
    public static final int ELEVATOR_PID_CRUISE_VEL = 0;
    public static final int ELEVATOR_PID_ACCELERATION = 10;
    public static final int ELEVATOR_SOFT_LIMIT = 10;
    //Arm
    public static final double kArmA_P = 0;
    public static final double kArmA_I = 0;
    public static final double kArmA_D = 0;
    public static final double kArmA_F = 0;
    public static final double kArmB_P = 0;
    public static final double kArmB_I = 0;
    public static final double kArmB_D = 0;
    public static final double kArmB_F = 0;

    public static final int[] ELEVATOR_PRESETS = {5,    /*Bottom*/};//Max Travel
}
