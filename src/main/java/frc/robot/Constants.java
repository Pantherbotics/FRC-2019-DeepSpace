package frc.robot;

public class Constants{
    //Robot Joystick
    public static int JoystickPort = 0; //PS4
    public static int JoystickLeftXAxis = 0;
    public static int JoystickLeftYAxis = 1; 
    public static int JoystickRightXAxis = 2; 
    public static int JoystickRightYAxis = 5;
    public static int PartnerJoyPort = 1; //X-Box
    public static int PartnerJoyLeftXAxis = 0;
    public static int PartnerJoyLeftYAxis = 1;
    public static int PartnerJoyRightXAxis = 2;
    public static int PartnerJoyRightYAxis = 3;
    //Robot Talons
    public static int kLeftA = 15;
    public static int kLeftB = 14;
    public static int kLeftC = 13;
    public static int kRightA = 0;
    public static int kRightB = 1;
    public static int kRightC = 2;
    public static int kElevatorA = 0;
    public static int kElevatorB = 0;
    public static int kArmA = 0;
    public static int kArmB = 0;
    //Drivetrain PID
    public static double DRIVE_P = 0.5;
    public static double DRIVE_I = 0;
    public static double DRIVE_D = 0;
    public static double DRIVE_F = 0;
    //Robot Elevator
    public static double kElevator_P = 0;
    public static double kElevator_I = 0;
    public static double kElevator_D = 0;
    public static double kElevator_F1 = 0;
    public static double kElevator_F2 = 0;
    public static int kElevatorTimeoutMS = 0;
    public static int ELEVATOR_CURRENT_LIMIT = 10;
    public static int ELEVATOR_PID_CRUISE_VEL = 0;
    public static int ELEVATOR_PID_ACCELERATION = 10;
    public static int ELEVATOR_SOFT_LIMIT = 10;
    //Arm
    public static double kArmA_P = 0;
    public static double kArmA_I = 0;
    public static double kArmA_D = 0;
    public static double kArmA_F = 0;
    public static double kArmB_P = 0;
    public static double kArmB_I = 0;
    public static double kArmB_D = 0;
    public static double kArmB_F = 0;

    public static final int[] ELEVATOR_PRESETS = {5,    /*Bottom*/};//Max Travel
}
