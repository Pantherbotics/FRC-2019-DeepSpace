package frc.robot.autonomous;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.util.DriveSignal;
import frc.robot.util.PurePursuit;
import jaci.pathfinder.Trajectory;

public class AutoPurePursuit extends Command {

    PurePursuit pursuit;
    private double start;
    private double last;
    private DriveSignal ds;

    public AutoPurePursuit(Trajectory traj) throws NullPointerException {
        requires(Robot.kDrivetrain);
        System.out.println("Path Length: " + traj.length());
        pursuit = new PurePursuit(Constants.wheelbase, traj);
        System.out.println("The Pursuit has begun, please work or I lathe myself");
    }

    @Override
    protected void initialize() {
        Robot.kDrivetrain.setOdo(pursuit.getInitOdom());
        start = System.nanoTime();
        last = start;
    }

    @Override
    protected void execute() {
        pursuit.setOdom(Robot.kDrivetrain.getOdo());
        ds = pursuit.getNextDriveSignal();
        System.out.println(ds.getLeft() + ", " + ds.getRight());
        Robot.kDrivetrain.setFPS(ds.getLeft(), ds.getRight());
        System.out.println("Loop time: " + (System.nanoTime() - last) / 1000000); //In milliseconds
    }

    @Override
    protected void end() {
        System.out.println("Finished");
        System.out.println("Running time: " + (System.nanoTime() - start) / 1000000); //In milliseconds, probably
    }

    @Override
    protected boolean isFinished() {
        return pursuit.isFinished();
    }

    @Override
    protected void interrupted() {
        System.out.println("wtf");
    }
}
