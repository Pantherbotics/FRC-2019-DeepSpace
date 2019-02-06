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
    //Other Joystick Stuff
    public static final double deadband = 0.05;
    public static final int[] elevSetpoint = 
                                       {0, //Lowered
                                        2500, //Middle
                                        30, //idk anymore there's going to be like 900 setpoints
                                        40, 
                                        50};
    //Robot Talons
    public static final int kLeftA = 13; //Change in phoenix tuner
    public static final int kLeftB = 14;
    public static final int kLeftC = 15;
    public static final int kRightA = 2;
    public static final int kRightB = 1;
    public static final int kRightC = 0;
    public static final int kElevatorA = 12;
    public static final int kElevatorB = 3;
    public static final int kArmA = 7;
    public static final int kArmB = 8;
    //Drivetrain
    public static final double driveRamp = 0.25;
    public static final int drivePeakCurrentLimit = 80;
    public static final int drivePeakCurrentDuration = 10;
    public static final int driveContinuousCurrentLimit = 36;
    //Drivetrain PID
    public static final double DRIVE_P = 4.5;
    public static final double DRIVE_I = 0;
    public static final double DRIVE_D = 0.05;
    public static final double DRIVE_F = 0.50979366385;
    //Robot Elevator
    public static final double kElevator_P = 1;
    public static final double kElevator_I = 0;
    public static final double kElevator_D = 0;
    public static final double kElevator_F1 = 1;
    public static final double kElevator_F2 = 1;
    public static final int kElevatorTimeoutMS = 0;
    public static final int ELEVATOR_CURRENT_LIMIT = 10;
    public static final int ELEVATOR_PID_CRUISE_VEL = 5;
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
