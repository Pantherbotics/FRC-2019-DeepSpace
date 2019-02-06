package frc.robot.commands;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ToSetpoint extends Command {
    int elevSet;
    int armSet;

    public ToSetpoint(int elevPreset, int armPreset) {
        requires(Robot.kElevator);
        requires(Robot.kArm);
        elevSet = elevPreset;
        armSet = armPreset;
    }

    protected void initialize() {
    }

    protected void execute() {
        Robot.kElevator.setTargetPosition(elevSet);
        System.out.println("you suck");
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}