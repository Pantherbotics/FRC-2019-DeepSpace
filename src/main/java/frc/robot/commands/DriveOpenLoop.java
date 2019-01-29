package frc.robot.commands;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class DriveOpenLoop extends Command{
    public DriveOpenLoop(){
        requires(Robot.kDrivetrain);
    }
    protected void initialize(){
        
    }
    protected void execute(){
        double throttle = Robot.oi.getLeftYAxis();
        double steering = Robot.oi.getRightXAxis();
        double left = (steering - throttle);
        double right = (-steering - throttle);
        System.out.println(left);
        System.out.println(right);
        Robot.kDrivetrain.setMotorPower(left, right);
    }
    protected boolean isFinished(){
        return false;
    }
    protected void end(){

    }
    protected void interrupted(){

    }
}