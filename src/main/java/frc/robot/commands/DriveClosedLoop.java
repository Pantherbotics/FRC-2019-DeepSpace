package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.util.*;

import edu.wpi.first.wpilibj.command.Command;

public class DriveClosedLoop extends Command {
  CheesyDriveHelper cheese;

  public DriveClosedLoop() {
    requires(Robot.kDrivetrain);
    cheese = new CheesyDriveHelper();
  }

  protected void initialize() {
  }

  protected void execute() { //generally choose this one
    double antiTipScalar = 1.0;
    if(Robot.kElevator.getPos() > Constants.kElevMidway){
      antiTipScalar = Robot.kElevator.getPos() / Constants.kElevatorMaxPos;
    }
    double zoom = Math.pow(Robot.oi.getLeftYAxis(), 2) * antiTipScalar; //zoom = forward backwards
    double nyoom = Math.pow(Robot.oi.getRightXAxis(), 2) * antiTipScalar; //nyoom = side to side... twist I guess
    DriveSignal drive = cheese.cheesyDrive(zoom, nyoom, true);
    //Robot.kDrivetrain.setFPS(16*(nyoom - zoom), 16*(-nyoom - zoom));
    Robot.kDrivetrain.setFPS(16*drive.getLeft(), 16*drive.getRight());
    //System.out.println("Left: " + 500*(nyoom - zoom) + "    Right: " + 500*(-nyoom - zoom));
  }

  protected boolean isFinished() {
    return false;
  }

  protected void end() {
  }

  protected void interrupted() {
  }
}