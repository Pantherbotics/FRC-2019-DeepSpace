package frc.robot.commands;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.util.Setpoint;

public class
ToSetpoint extends Command {
    int elevSet;
    int shoulderSet;
    int wristSet;

    public ToSetpoint(int elevPreset, int shoulderPreset, int wristPreset) {
        requires(Robot.kElevator);
        requires(Robot.kArm);
        elevSet = elevPreset;
        shoulderSet = shoulderPreset;
        wristSet = wristPreset;
    }

    public ToSetpoint(Setpoint setpoint){
        elevSet = setpoint.getElevatorTicks();
        shoulderSet = setpoint.getShoulderTicks();
        wristSet = setpoint.getWristTicks();
    }

    protected void initialize() {
    }

    protected void execute() { //Code the prevents the arm from slamming the intake into the electronics board
        Robot.kArm.setShoulderPosition(shoulderSet);
        Robot.kArm.setWristPosition(wristSet);
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