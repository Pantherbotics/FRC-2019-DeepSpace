package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.util.Units;

public class IncrementElevator extends Command{

    private int increment;
    public IncrementElevator(double increment) {
        requires(Robot.kElevator);
        this.increment = Units.inchesToElevatorTicks(increment);
    }

    protected void initialize() {
    }

    protected void execute() { //?
        Robot.kElevator.setPos(increment);
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
