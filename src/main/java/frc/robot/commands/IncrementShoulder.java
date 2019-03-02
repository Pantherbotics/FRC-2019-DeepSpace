package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.util.CheesyDriveHelper;
import frc.robot.util.DriveSignal;
import frc.robot.util.Units;

import static frc.robot.Robot.kArm;

public class IncrementShoulder extends Command { //Should really be called incrementArm

  private double incrementS;
  private double incrementW;
  private double shoulderPos;
  private double wristPos;

  public IncrementShoulder() {

  }

  protected void initialize() {
  }

  protected void execute() { //generally choose this one
    shoulderPos = kArm.getShoulderPosition();
    if(Robot.oi.getPartnerLeftYAxis() > Constants.deadband) {
      incrementS = 50 * Robot.oi.getPartnerLeftYAxis();
      Robot.kArm.setShoulderPosition((int) (shoulderPos + incrementS));
    } if(Robot.oi.getPartnerRightYAxis() > Constants.deadband) {
      incrementW = 50 * Robot.oi.getPartnerRightYAxis();
      Robot.kArm.setWristPosition((int) (wristPos + incrementW));
    }
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