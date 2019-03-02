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

  public IncrementShoulder() {
    incrementS = Robot.oi.getPartnerLeftYAxis();

  }

  protected void initialize() {
  }

  protected void execute() { //generally choose this one
    //kArm.setShoulderPosition(Units.degreesToTalon(increment) + kArm.getShoulderPosition());
  }

  protected boolean isFinished() {
    return true;
  }

  protected void end() {
  }

  protected void interrupted() {
  }
}