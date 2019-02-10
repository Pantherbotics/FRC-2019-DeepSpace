package frc.robot.commands;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.Constants;

public class ToSetpoint extends Command {
    int elevSet;
    int armSet;
    int borkDistance;
    double toEdge;

    public ToSetpoint(int elevPreset, int armPreset) {
        requires(Robot.kElevator);
        requires(Robot.kArm);
        elevSet = elevPreset;
        armSet = armPreset;
    }

    protected void initialize() {
    }

    protected void execute() {
        Robot.kArm.setPos(armSet);
        borkDistance = elevSet + (int)(Constants.sin2Encoder * Math.sin(armSet * Constants.encoder2Rad)) + Constants.intakeDeathZone;
        toEdge = 22 * Math.cos(armSet * Constants.encoder2Rad);
        if((borkDistance <= 0) && (toEdge <= 18.5)){
            Robot.kElevator.setPos(-(borkDistance - elevSet));
        } /*else if((borkDistance <= -) && (toEdge > 18.5)){
            Robot.kElevator.setPos(pos);
        }*/
        else{
        Robot.kElevator.setPos(elevSet);
        }
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