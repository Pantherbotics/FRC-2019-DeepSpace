package frc.robot;

public class Constants{
    //Robot Joystick
    public static int kJoystickPort = 0;
    public static int kJoystickLeftXAxis = 0;
    public static int kJoystickLeftYAxis = 1; 
    public static int kJoystickRightYAxis = 5; 
    public static int kJoystickRightXAxis = 2; 
    //Robot Talons Motors
    public static int kLeftA = 0;
    public static int kLeftB = 0;
    public static int kLeftC = 0;
    public static int kRightA = 0;
    public static int kRightB = 0;
    public static int kRightC = 0;
    //Robot Elevator
    public static int kElevatorID = 0;
    public static int kElevator_P = 0;
    public static int kElevator_I = 0;
    public static int kElevator_D = 0;
    public static int kElevator_F = 0;
    public static int kElevatorTimeoutMS = 0;
    public static int ELEVATOR_CURRENT_LIMIT = 10;
    public static int ELEVATOR_PID_CRUISE_VEL = 0;
    public static int ELEVATOR_PID_ACCELERATION = 10;
    public static int ELEVATOR_SOFT_LIMIT = 10;

    public static final int[] ELEVATOR_PRESETS = {5,    /*Bottom*/};//Max Travel
}

