package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class PowerClimber extends Command {
    double power;

    public PowerClimber(double input) {
        requires(Robot.kClimb);
        power = input;
    }

    @Override
    protected void initialize() {}

    @Override
    protected void execute() {
        Robot.kClimb.setPower(power);
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
        Robot.kClimb.setPower(0);
    }

    @Override
    protected void interrupted() {
        Robot.kClimb.setPower(0);
    }
}
