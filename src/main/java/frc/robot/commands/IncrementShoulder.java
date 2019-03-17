package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.util.CheesyDriveHelper;
import frc.robot.util.DriveSignal;
import frc.robot.util.Units;

import static frc.robot.Robot.kArm;
import static frc.robot.Robot.oi;

public class IncrementShoulder extends Command { //Should really be called incrementArm

  private double power;

  public IncrementShoulder() {
    requires(Robot.kArm);
  }

  protected void initialize() {
  }

  protected void execute() { //Bind to a button so that when it's held it will work
    power = Robot.oi.getPartnerRightXAxis();
    Robot.kArm.powerShoulder(power);
  }

  protected boolean isFinished() {
    return true;
  }

  protected void end() {
  }

  protected void interrupted() {
    isFinished();
  }
}