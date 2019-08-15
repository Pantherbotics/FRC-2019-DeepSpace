package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class DeployClimber extends Command {
    boolean actuated = true;

    public DeployClimber(boolean input) {
        requires(Robot.kClimb);
        actuated = input;
    }

    @Override
    protected void initialize() {}

    @Override
    protected void execute() {
        Robot.kClimb.setDeploy(actuated);
        Robot.kClimb.setSuction(1.0);
    }

    @Override
    protected boolean isFinished() {
        return true;
    }

    @Override
    protected void end() {}

    @Override
    protected void interrupted() {}
}
