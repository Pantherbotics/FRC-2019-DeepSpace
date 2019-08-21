//this code was made by team 3863 FIRST Robotics, Newbury Park, CA 91320
package frc.robot.commands;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ZeroElevator extends Command{

    public ZeroElevator(){
        requires(Robot.kElevator);
    }

    @Override
    protected void initialize(){
        
    }

    @Override
    protected void execute(){
        Robot.kElevator.setPower(-0.25);
    }

    @Override
    protected boolean isFinished(){
        return Math.abs(Robot.kElevator.getVelocity()) < 10;
    }

    @Override
    protected void end(){
        Robot.kElevator.setElevatorEncoder(10);
        Robot.kElevator.setPower(0);
    }

    @Override
    protected void interrupted(){
        Robot.kElevator.setPower(0);
    }
}

