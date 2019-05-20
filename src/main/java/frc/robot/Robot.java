/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.hal.sim.DriverStationSim;
import frc.robot.commands.IncrementShoulder;
import frc.robot.subsystems.*;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Waypoint;
import jaci.pathfinder.Trajectory.Segment;
import jaci.pathfinder.followers.EncoderFollower;

import java.util.HashMap;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.modifiers.*;



/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  public static final Drivetrain kDrivetrain = new Drivetrain();
  public static final Elevator kElevator = new Elevator();
  public static final Arm kArm = new Arm();
  public static final Intake kIntake = new Intake();
  public static final Vision kVision = new Vision(Constants.kVisionBaud, Constants.kVisionPort);
  public static final OI oi = new OI(); //Instantiate OI after instantiating all the subsystems
  public static HashMap<String, Trajectory> paths;
  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  private Command kAuto;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();
  public DriverStation ds = DriverStation.getInstance();
  public DriverStationSim DriveSim = new DriverStationSim();
  public static final Cameras kCamera = new Cameras();
  private Trajectory kTrajectory;
  EncoderFollower leftEncoderFollower;
  EncoderFollower rightEncoderFollower;
  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);
    kCamera.enableCameras();
    paths = collectPathsFromDirectory(Constants.PATH_LOCATION); //IF THE ROBOT CODE FAILS COMMENT ME OUT
    Waypoint[] points = new Waypoint[]{
      new Waypoint(0,0,0), 
      new Waypoint(5, 0, 0), 
      new Waypoint(10, 5, Math.toRadians(90))
    };
    Trajectory.Config config = new Trajectory.Config(Trajectory.FitMethod.HERMITE_CUBIC, Trajectory.Config.SAMPLES_HIGH, 0.05, 1.7, 2.0, 60.0);
    kTrajectory = Pathfinder.generate(points, config);
    TankModifier modifier = new TankModifier(kTrajectory).modify(0.5);
    leftEncoderFollower = new EncoderFollower(modifier.getLeftTrajectory());
    rightEncoderFollower = new EncoderFollower(modifier.getRightTrajectory()); 

    TalonSRX kTalon = kDrivetrain.getTalon();
    /*
    Encoder Position is the current, cumulative position of your encoder. If you're using an SRX, this will be the
        'getSelectedSensorPosition' function.
    1000 is the amount of encoder ticks per full revolution
    Wheel Diameter is the diameter of your wheels (or pulley for a track system) in meters
    */
    leftEncoderFollower.configureEncoder(kTalon.getSelectedSensorPosition(), 2048, 0.1524); 
    rightEncoderFollower.configureEncoder(kTalon.getSelectedSensorPosition(), 2048, 0.1524);
    /*
    The first argument is the proportional gain. Usually this will be quite high
    The second argument is the integral gain. This is unused for motion profiling
    The third argument is the derivative gain. Tweak this if you are unhappy with the tracking of the trajectory
    The fourth argument is the velocity ratio. This is 1 over the maximum velocity you provided in the 
        trajectory configuration (it translates m/s to a -1 to 1 scale that your motors can read)
    The fifth argument is your acceleration gain. Tweak this if you want to get to a higher or lower speed quicker
    */
    leftEncoderFollower.configurePIDVA(1.0, 0.0, 0.0, 1 / Constants.kTicksPerFeet, 0);
    rightEncoderFollower.configurePIDVA(1.0, 0.0, 0.0, 1 / Constants.kTicksPerFeet, 0);

  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    SmartDashboard.putNumber("Elevator (Ticks)", kElevator.getPos());
    SmartDashboard.putNumber("Shoulder (Ticks)", kArm.getShoulderPosition());

    SmartDashboard.putNumber("Shoulder RAW (Ticks)", kArm.getShoulderPositionRaw());

    SmartDashboard.putNumber("Shoulder (Degrees)", kArm.getShoulderDegrees());
    SmartDashboard.putNumber("Elevator (Inches)", kElevator.getPosInches());

    SmartDashboard.putNumber("Elevator Voltage", kElevator.getVoltage());
    SmartDashboard.putNumber("Shoulder Voltage", kArm.getShoulderVoltage());
    SmartDashboard.putNumber("VL", kDrivetrain.getVoltage()[0]);
    SmartDashboard.putNumber("VR", kDrivetrain.getVoltage()[1]);
    SmartDashboard.putNumber("VelL", kDrivetrain.getEncoderVelocity()[0]);
    SmartDashboard.putNumber("VelR", kDrivetrain.getEncoderVelocity()[1]);

    SmartDashboard.putNumber("Odometry X", kDrivetrain.getOdo().getX());
    SmartDashboard.putNumber("Odometry Y", kDrivetrain.getOdo().getY());
    SmartDashboard.putNumber("Odometry Theta", kDrivetrain.getOdo().getTheta());

    //System.out.println(kVision.getSerialData());
    SmartDashboard.putNumber("Attack Angle", kVision.getAttackAngle());
    SmartDashboard.putNumber("Distance", kVision.getDistance());

    SmartDashboard.putNumber("Left Drive Encoder", kDrivetrain.getLeftDriveEncoder());
    SmartDashboard.putNumber("Right Drive Encoder", kDrivetrain.getRightDriveEncoder());

    SmartDashboard.putNumber("Average Velocity PID Error: ", kDrivetrain.getVelocityError());
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to
   * the switch structure below with additional strings. If using the
   * SendableChooser make sure to add them to the chooser code above as well.
   */
  @Override
  public void autonomousInit() {
    //kAuto = m_chooser.getSelected();
    // m_autoSelected = SmartDashboard.getString("Auto Selector", kDefaultAuto);
    //System.out.println("Auto selected: " + m_autoSelected);
    kDrivetrain.zeroGyro();
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    /*
    switch (m_autoSelected) {
      case kCustomAuto:
        // Put custom auto code here
        break;
      case kDefaultAuto:
      default:
        // Put default auto code here
        break;
    }
    */
    Scheduler.getInstance().run();
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    //System.out.println(kDrivetrain.getVelocity());
    //Stops the auto
    if(kAuto != null){
      kAuto.cancel();
    }


    //CODE FOR CONTROLLING SHOULDER POSITION MANUALLY
   // double increment = oi.getPartnerLeftYAxis() * Constants.kIncrementDegrees;
   // new IncrementShoulder(increment).start();


    Scheduler.getInstance().run();

    //System.out.println(kElevator.getVelocity());
    /*kElevator.setPower(oi.getPartnerLeftYAxis()); makeshift open loop, 
    if used, also disabled soft forward and soft reverse limit for talons*/
  }
  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }

  public void outputPathsToDashboard(HashMap<String, Trajectory> paths, SendableChooser<String> chooser){
    System.out.println(paths.isEmpty());
    for(String key : paths.keySet()){
        System.out.println(key);
        chooser.addObject(key, key);
    }
  }

  public HashMap<String, Trajectory> collectPathsFromDirectory(String dir){
      HashMap<String, Trajectory> paths = new HashMap<>();

          ArrayList<File> filesInFolder = listf(dir);

          for(int i = filesInFolder.size()-1; i >=0 ; i--){
              File traj = filesInFolder.get(i);
              if (!traj.getName().contains("_source_Jaci.csv")){
                  filesInFolder.remove(i);
              }
          }
          for(File traj: filesInFolder){
              System.out.println(traj.getName());                                                                          //take all the File objects we just created & convert them into Trajectories to put into HashMap
              paths.put(traj.getName().replace("_left.csv", ""), Pathfinder.readFromCSV(traj));         //choose the left one arbitrarily (they're the same bc wheelbase = 0 in vannaka's)
          }
          return paths;
  }


  public static ArrayList<File> listf(String directoryName) {
      File directory = new File(directoryName);

      // get all the files from a directory
      File[] fList = directory.listFiles();
      ArrayList<File> resultList = new ArrayList<File>(Arrays.asList(fList));
      for (File file : fList) {
          if (file.isFile()) {
              System.out.println(file.getAbsolutePath());
          } else if (file.isDirectory()) {
              resultList.addAll(listf(file.getAbsolutePath()));
          }
      }
      //System.out.println(fList);
      return resultList;
  }
}
