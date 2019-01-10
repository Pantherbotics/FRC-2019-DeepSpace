package frc.robot.commands;

import frc.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class DriveClosedLoop extends Command {
  public DriveClosedLoop() {
    requires(Robot.kDrivetrain);
  }

  protected void initialize() {
  }

  protected void execute() {
    double zoom = Robot.oi.getLeftYAxis(); //zoom = throttle
    double nyoom = Robot.oi.getRightXAxis(); //nyoom = steering
    Robot.kDrivetrain.setFPS(zoom - nyoom, zoom + nyoom);
  }

  protected boolean isFinished() {
    return false;
  }

  protected void end() {
  }

  protected void interrupted() {
  }
}
