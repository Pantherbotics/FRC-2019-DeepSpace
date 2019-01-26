package frc.robot.commands;

import frc.robot.Robot;
import frc.robot.util.Units;

import edu.wpi.first.wpilibj.command.Command;

public class DriveClosedLoop extends Command {

  public DriveClosedLoop() {
    requires(Robot.kDrivetrain);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() { //generally choose this one
    double zoom = Robot.oi.getLeftYAxis(); //zoom = throttle
    double nyoom = Robot.oi.getRightXAxis(); //nyoom = steering
    Robot.kDrivetrain.setFPS(Units.FPSToTalonNative(zoom - nyoom), Units.FPSToTalonNative(zoom + nyoom));
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
  }
  
  @Override
  protected void interrupted() {
  }
}