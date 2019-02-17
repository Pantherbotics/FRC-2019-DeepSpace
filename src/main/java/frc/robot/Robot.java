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
import frc.robot.subsystems.*;

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
  public static final OI oi = new OI(); //Instantiate OI after instantiating all the subsystems
  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  private Command kAuto;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();
  public DriverStation ds = DriverStation.getInstance();
  public DriverStationSim DriveSim = new DriverStationSim();

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    //if(kArm.getShoulderPosition() > 200 || kArm.getShoulderPosition() < )
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
    SmartDashboard.putNumber("Elev", kElevator.getPos());
    SmartDashboard.putNumber("Shoulder", kArm.getShoulderPosition());
    SmartDashboard.putNumber("Wrist", kArm.getWristPosition());
    //SmartDashboard.putNumber("Shoulder Volt", kArm.getShoulderVoltage());
    //SmartDashboard.putNumber("Wrist", kArm.getWristVoltage());
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
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    //Stops the auto
    if(kAuto != null){
      kAuto.cancel();
    }
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
}
