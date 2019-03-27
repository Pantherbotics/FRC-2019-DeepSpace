/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Constants;
import frc.robot.Robot;

public class IntakeCargo extends Command {
  double power;
  public IntakeCargo(double power) {
    requires(Robot.kIntake);
    this.power = power;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.kIntake.extendCargoArms();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.kIntake.grabHatchPanel();
    Robot.kIntake.setCargoIntakePower(power);
    if(Robot.kIntake.withinIntakeRange(Constants.kCargoRange)){
      Robot.kIntake.closeCargoArms();
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    Robot.kIntake.setCargoIntakePower(0);
  }
}
