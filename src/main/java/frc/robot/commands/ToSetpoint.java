package frc.robot.commands;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.util.Setpoint;

public class ToSetpoint extends Command {
    int elevSet;
    int shoulderSet;

    public ToSetpoint(int elevPreset, int shoulderPreset) {
        requires(Robot.kElevator);
        requires(Robot.kArm);
        elevSet = elevPreset;
        shoulderSet = shoulderPreset;
    }

    public ToSetpoint(Setpoint setpoint){
        elevSet = setpoint.getElevatorTicks();
        shoulderSet = setpoint.getShoulderTicks();
    }

    protected void initialize() {
    }

    protected void execute() { //Code the prevents the arm from slamming the intake into the electronics board //sike
        Robot.kArm.setShoulderPosition(shoulderSet, Robot.kElevator.getAcceleration());
        Robot.kElevator.setPos(elevSet);
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