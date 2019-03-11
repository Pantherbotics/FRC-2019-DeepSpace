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

  private double incrementS;
  private double shoulderPos;

  public IncrementShoulder() {

  }

  protected void initialize() {
  }

  protected void execute() { //generally choose this one
    shoulderPos = kArm.getShoulderPosition();
    if(Robot.oi.getPartnerLeftYAxis() > Constants.deadband) {
      incrementS = 50 * Robot.oi.getPartnerLeftYAxis();
      Robot.kArm.setShoulderPosition((int) (shoulderPos + incrementS));
    }
  }

  protected boolean isFinished() {
    return false;
  }

  protected void end() {
  }

  protected void interrupted() {
    isFinished();
  }
}