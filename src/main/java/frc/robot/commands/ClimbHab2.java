package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ClimbHab2 extends Command { //WE NEED 6 POINTS TO WIN WORLDS
    public ClimbHab2() {
        requires(Robot.kDrivetrain);
    }

    protected void initialize() {
    }

    protected void execute() { //YEEEEEEEEEEEEEEEEEET
        Robot.kDrivetrain.setFPS(0.9, 0.9);
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
    }

    protected void interrupted() {
        isFinished();
    }
}
