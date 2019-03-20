package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.util.Units;

import static frc.robot.Robot.oi;

public class IncrementElevator extends Command{

    public IncrementElevator() {
    }

    protected void initialize() {
    }

    protected void execute() { //?
        double joy = oi.getPartnerLeftYAxis() < 0.05 ? 0 : oi.getPartnerLeftYAxis();
        int increment = (int)(500 * joy);
        if(increment !=0) {
            Robot.kElevator.setPos(Robot.kElevator.getPos() + increment);
        }
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
