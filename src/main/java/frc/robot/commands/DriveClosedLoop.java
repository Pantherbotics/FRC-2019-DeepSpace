package frc.robot.commands;

import frc.robot.Robot;
import frc.robot.util.*;

import edu.wpi.first.wpilibj.command.Command;

public class DriveClosedLoop extends Command {
  CheesyDriveHelper cheese;

  public DriveClosedLoop() {
    requires(Robot.kDrivetrain);
  }

  protected void initialize() {
  }

  protected void execute() { //generally choose this one
    double zoom = Robot.oi.getLeftYAxis(); //zoom = forward backwards
    double nyoom = Robot.oi.getRightXAxis(); //nyoom = side to side... twist I guess
    DriveSignal drive = cheese.cheesyDrive(-zoom, nyoom, true);
    Robot.kDrivetrain.setFPS(Units.FPSToTalonNative(16 * drive.getLeft()), Units.FPSToTalonNative(16 * drive.getRight()));
    //System.out.println("Left: " + 500*(nyoom - zoom) + "    Right: " + 500*(-nyoom - zoom));
  }

  protected boolean isFinished() {
    return false;
  }

  protected void end() {
  }

  protected void interrupted() {
  }
}