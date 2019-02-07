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
                                       {0, //bottom
                                        500, //test, few inches from bottom
                                        2000, //idk
                                        8000  //higher than idk, maybe top?
                                    };
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
    public static final double DRIVE_F = 0.50979366385; //Probably wrong
    //Robot Elevator
    public static double elevatorKP = 1.75;
    public static double elevatorKI = 0.0;
    public static double elevatorKD = 0.0;
    public static double elevatorKF = 770.0/1023.0; //1023.0/770.0; //talon (1023) / max velocity of elevator (770)
    public static int elevatorCruiseSpeed = 800;
    public static int elevatorAccelerationSpeed = 1000;

    public static int timeoutMS = 10;
    public static int primaryPIDIDX = 0;
    //Arm
    public static final double ArmA_P = 0;
    public static final double ArmA_I = 0;
    public static final double ArmA_D = 0;
    public static final double ArmA_F = 0; //Won't be used
    public static final double ArmB_P = 0;
    public static final double ArmB_I = 0;
    public static final double ArmB_D = 0;
    public static final double ArmB_F = 0;
    public static final int armA_ID = 0;
    public static final int armB_ID = 1;

    public static final int[] ELEVATOR_PRESETS = {5,    /*Bottom*/};//Max Travel
}
