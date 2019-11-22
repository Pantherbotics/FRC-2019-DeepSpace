package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class LimelightChase extends Command {
    double drive, steer;
    double targetArea = 10.0;
    double driveKP = 0.5;
    double steerKP = 0.5;

    public LimelightChase() {
        requires(Robot.kDrivetrain);
        requires(Robot.kLime);
        drive = 0;
        steer = 0;
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        drive = driveKP * (targetArea - Robot.kLime.getArea());
        steer = steerKP * Robot.kLime.getX();

        Robot.kDrivetrain.setMotorPower(drive - steer, drive + steer);
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {

    }
}
