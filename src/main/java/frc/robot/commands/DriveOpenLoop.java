package frc.robot.commands;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class DriveOpenLoop extends Command{
    public DriveOpenLoop(){
        requires(Robot.kDrivetrain);
    }
    protected void initialize(){
        
    }
    protected void execute(){ //You are already obsolete
        double throttle = Robot.oi.getLeftYAxis(); //NANI
        double steering = Robot.oi.getRightXAxis(); //*DriveClosedLoop screeching*
        double left = (steering - throttle); //*Dies in open loop*
        double right = (-steering - throttle); 
        Robot.kDrivetrain.setMotorPower(left, right); //High-tier cringe
    }
    protected boolean isFinished(){
        return false;
    }
    protected void end(){

    }
    protected void interrupted(){

    }
}