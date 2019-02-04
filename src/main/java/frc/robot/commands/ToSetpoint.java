package frc.robot.commands;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ToSetpoint extends Command {
    int elevSet;
    int armSet;

    public ToSetpoint(int elevPreset, int armPreset) {
        elevSet = elevPreset;
        armSet = armPreset;
        requires(Robot.kElevator);
        requires(Robot.kArm);
    }

    protected void initialize() {
    }

    protected void execute() {
        Robot.kElevator.goToPreset(elevSet);
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}