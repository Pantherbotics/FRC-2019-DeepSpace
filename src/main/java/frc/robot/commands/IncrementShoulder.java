package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.util.CheesyDriveHelper;
import frc.robot.util.DriveSignal;
import frc.robot.util.Units;

import static frc.robot.Robot.kArm;
import static frc.robot.Robot.oi;

public class IncrementShoulder extends Command {

  public IncrementShoulder(double increment) {
    requires(kArm);
  }

  protected void initialize() {
  }

  protected void execute() { //generally choose this one
    double increment = 5.0 * oi.getPartnerRightYAxis();
    kArm.setShoulderPosition(Units.degreesToTalon(increment) + kArm.getShoulderPosition());
  }

  protected boolean isFinished() {
    return false;
  }

  protected void end() {
  }

  protected void interrupted() {
  }
}