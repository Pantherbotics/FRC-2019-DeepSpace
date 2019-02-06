//this code was made by team 3863 FIRST Robotics, Newbury Park, CA 91320
package frc.robot.commands;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ZeroElevator extends Command{
    public ZeroElevator(){
        //requires(Robot.kElevator);
    }
    protected void initialize(){
        
    }
    protected void execute(){
        Robot.kElevator.setPower(-0.1);
    }
    protected boolean isFinished(){
        return Robot.kElevator.getLimitSwitch();
    }
    protected void end(){
        Robot.kElevator.setElevatorEncoder(0);
    }
    protected void interrupted(){

    }
}

