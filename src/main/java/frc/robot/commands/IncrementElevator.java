package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.util.Units;

import static frc.robot.Robot.oi;

public class IncrementElevator extends Command{

    private double power;

    public IncrementElevator() {
    }

    protected void initialize() {
    }

    protected void execute() { //The increment commands are basically open loop commands
        power = Robot.oi.getPartnerRightYAxis();
        Robot.kElevator.setPower(power);
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
