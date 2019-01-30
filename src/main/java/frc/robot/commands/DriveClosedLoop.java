package frc.robot.commands;

import frc.robot.Robot;
import frc.robot.util.Units;

import edu.wpi.first.wpilibj.command.Command;

public class DriveClosedLoop extends Command {

  public DriveClosedLoop() {
    requires(Robot.kDrivetrain);
  }

  protected void initialize() {
  }

  protected void execute() { //generally choose this one
    double zoom = Robot.oi.getLeftYAxis(); //zoom = throttle
    double nyoom = Robot.oi.getRightXAxis(); //nyoom = steering
    Robot.kDrivetrain.setFPS(Units.FPSToTalonNative(nyoom - zoom), Units.FPSToTalonNative(-nyoom - zoom));
    System.out.println("throttle: " + Units.FPSToTalonNative(nyoom - zoom) + "    steering: " + Units.FPSToTalonNative(-nyoom - zoom)
    );
  }

  protected boolean isFinished() {
    return false;
  }

  protected void end() {
  }

  protected void interrupted() {
  }
}