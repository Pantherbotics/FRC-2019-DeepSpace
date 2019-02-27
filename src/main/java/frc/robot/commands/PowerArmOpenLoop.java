package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.util.CheesyDriveHelper;
import frc.robot.util.DriveSignal;
import frc.robot.util.Units;

import static frc.robot.Robot.kArm;
import static frc.robot.Robot.oi;

public class PowerArmOpenLoop extends Command {

    public PowerArmOpenLoop() {
        requires(kArm);
    }

    protected void initialize() {
    }

    protected void execute() { //generally choose this one
        //kArm.setShoulderPosition(Units.degreesToTalon(increment) + kArm.getShoulderPosition());
        double shoulder = oi.getPartnerLeftYAxis() * 0.5;
        double wrist = oi.getPartnerRightYAxis() * 0.5;
        kArm.powerShoulder(shoulder);
        kArm.powerWrist(wrist);
        System.out.println("yes");
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}