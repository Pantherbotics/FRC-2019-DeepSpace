package frc.robot.commands;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ElevatorSetpoint extends Command {
    int set;

    public ElevatorSetpoint(int preset) {
        set = preset;
        requires(Robot.kElevator);
    }

    protected void initialize() {
    }

    protected void execute() {
        Robot.kElevator.goToPreset(set);
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
