package frc.robot;

public class Constants{
    //Robot Joystick
    public static int kJoystickPort = 0;
    public static int kJoystickLeftXAxis = 0;
    public static int kJoystickLeftYAxis = 1; 
    public static int kJoystickRightYAxis = 5; 
    public static int kJoystickRightXAxis = 2; 
    //Robot Talons
    public static int kLeftA = 0;
    public static int kLeftB = 0;
    public static int kLeftC = 0;
    public static int kRightA = 0;
    public static int kRightB = 0;
    public static int kRightC = 0;
    public static int kElevatorID = 0;
    public static int kArmA = 0;
    public static int kArmB = 0;
    //Drivetrain PID
    public static double HDRIVE_P = 0.5;
    public static double HDRIVE_I = 0;
    public static double HDRIVE_D = 0;
    public static double HDRIVE_F = 0;
    public static double LDRIVE_P = 0.2;
    public static double LDRIVE_I = 0;
    public static double LDRIVE_D = 0;
    public static double LDRIVE_F = 0;
    //Robot Elevator
    public static double kElevator_P = 0;
    public static double kElevator_I = 0;
    public static double kElevator_D = 0;
    public static double kElevator_F = 0;
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
