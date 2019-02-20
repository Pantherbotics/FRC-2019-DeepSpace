package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.util.CheesyDriveHelper;
import frc.robot.util.DriveSignal;
import frc.robot.util.Units;

import static frc.robot.Robot.kArm;

public class IncrementShoulder extends Command {

  private double increment;
  public IncrementShoulder(double increment) {
    requires(kArm);
    this.increment = increment;
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