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
import frc.robot.autonomous.AutoPurePursuit;
import frc.robot.subsystems.*;
import jaci.pathfinder.Trajectory;

import java.io.IOException;
import java.util.HashMap;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import jaci.pathfinder.Pathfinder;

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
    public static final Climber kClimb = new Climber();
    public static final Limelight kLime = new Limelight();
    public static final OI oi = new OI(); //Instantiate OI after instantiating all the subsystems
    public static HashMap<String, Trajectory> paths;
    private static final String kDefaultAuto = "Default";
    private static final String kCustomAuto = "My Auto";
    private Command kAuto;
    private final SendableChooser<String> m_chooser = new SendableChooser<>();
    private boolean isAuto = false;
    public DriverStation ds = DriverStation.getInstance();
    public static final Cameras kCamera = new Cameras();

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    @Override
    public void robotInit() {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\nROBOT INIT");
        m_chooser.setDefaultOption("None    ", null);
        try {
            paths = collectPathsFromDirectory(Constants.PATH_LOCATION); //IF THE ROBOT CODE FAILS COMMENT ME OUT
        }
        catch (IOException e) {
            e.printStackTrace();
            paths = null;
        }
        for (String key : paths.keySet()) {
            System.out.println("ADDING KEY:   " + key);
            m_chooser.addOption(key, key);
        }
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\nADDING CHOOSER\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        SmartDashboard.putData("Auto choices", m_chooser);
        kCamera.enableCameras();
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
        SmartDashboard.putNumber("Elevator (Inches)", kElevator.getPosInches());
        SmartDashboard.putNumber("Elevator Voltage", kElevator.getVoltage());
        SmartDashboard.putNumber("Elevator Current", kElevator.getCurrent());
        SmartDashboard.putNumber("Elevator Velocity", kElevator.getVelocity());

        SmartDashboard.putNumber("Shoulder (Ticks)", kArm.getShoulderPosition());
        SmartDashboard.putNumber("Shoulder RAW (Ticks)", kArm.getShoulderPositionRaw());
        SmartDashboard.putNumber("Shoulder (Degrees)", kArm.getShoulderDegrees());
        SmartDashboard.putNumber("Shoulder Voltage", kArm.getShoulderVoltage());

        SmartDashboard.putNumber("Left Drive Encoder", kDrivetrain.getLeftDriveEncoder());
        SmartDashboard.putNumber("Right Drive Encoder", kDrivetrain.getRightDriveEncoder());
        SmartDashboard.putNumber("VL", kDrivetrain.getVoltage()[0]);
        SmartDashboard.putNumber("VR", kDrivetrain.getVoltage()[1]);
        SmartDashboard.putNumber("VelL", kDrivetrain.getEncoderVelocity()[0]);
        SmartDashboard.putNumber("VelR", kDrivetrain.getEncoderVelocity()[1]);
        SmartDashboard.putNumber("Average Velocity PID Error: ", kDrivetrain.getVelocityError());
        SmartDashboard.putNumber("Angle", kDrivetrain.getGyroAngle());
        SmartDashboard.putNumber("Bound Angle", kDrivetrain.getBoundGyroAngle());

        SmartDashboard.putNumber("Odometry X", kDrivetrain.getOdo().getX());
        SmartDashboard.putNumber("Odometry Y", kDrivetrain.getOdo().getY());
        SmartDashboard.putNumber("Odometry Theta", kDrivetrain.getOdo().getTheta());

        SmartDashboard.putNumber("Attack Angle", kVision.getAttackAngle());
        SmartDashboard.putNumber("Distance", kVision.getDistance());

        SmartDashboard.putNumber("Intake Sensor Raw", kIntake.getIntakeSensorRaw());
        SmartDashboard.putBoolean("Scissors Closed?", kIntake.getHatchPanel());
        SmartDashboard.putBoolean("Cargo Closed?", kIntake.getCargoArms());

        SmartDashboard.putNumber("Limelight X", kLime.getX());
        SmartDashboard.putNumber("Limelight Y", kLime.getY());
        SmartDashboard.putNumber("LimeLight Area", kLime.getArea());
    }

    @Override
    public void disabledInit() {
        kIntake.closeCargoArms();
        kIntake.grabHatchPanel();
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
        isAuto = false;

        try {
            kAuto = new AutoPurePursuit(paths.get(m_chooser.getSelected()));
        } catch (NullPointerException e) {
            System.out.println("No auto selected");
        }
        kDrivetrain.zeroGyro();
        kIntake.closeCargoArms();
        kIntake.grabHatchPanel();
    }

    /**
     * This function is called periodically during autonomous.
     */
    @Override
    public void autonomousPeriodic() {
        if (kAuto != null && !isAuto) {
            isAuto = true;
            System.out.println("Execute Auto 66");
            kAuto.start();
        }
        Scheduler.getInstance().run();
    }

    @Override
    public void teleopInit() {
        kClimb.setSuction(0);
        kClimb.setDeploy(true);
    }

    /**
     * This function is called periodically during operator control.
     */
    @Override
    public void teleopPeriodic() {
        //System.out.println(kDrivetrain.getVelocity());
        //Stops the auto
        if (kAuto != null) {
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

    public void outputPathsToDashboard(HashMap<String, Trajectory> paths, SendableChooser<String> chooser) {
        System.out.println(paths.isEmpty());
        for (String key : paths.keySet()) {
            System.out.println(key);
            chooser.addObject(key, key);
        }
    }

    public HashMap<String, Trajectory> collectPathsFromDirectory(String dir) throws IOException {
        HashMap<String, Trajectory> paths = new HashMap<>();

        ArrayList<File> filesInFolder = listf(dir);

        System.out.println("\t\t" + filesInFolder.size());
        for (int i = filesInFolder.size() - 1; i >= 0; i--) {
            File traj = filesInFolder.get(i);
            if (!traj.getName().contains("_Auto_left.csv")) {

                filesInFolder.remove(i);
            }
        }
        System.out.println("\n\n\nI'M GETTING THERE\n\n\n");
        System.out.println("\t\t" + filesInFolder.size());
        for (File traj : filesInFolder) {
            System.out.println("ADDING TRAJECTORY:    " + traj.getName()); //take all the File objects we just created & convert them into Trajectories to put into HashMap
            paths.put(traj.getName().replace("_left.csv", " "), Pathfinder.readFromCSV(traj)); //choose the left one arbitrarily (they're the same bc wheelbase = 0 in vannaka's)
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
