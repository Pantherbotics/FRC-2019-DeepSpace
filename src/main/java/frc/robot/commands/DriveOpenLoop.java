package frc.robot.commands;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.Constants;

public class DriveOpenLoop extends Command{
    public DriveOpenLoop(){
        requires(Robot.kDrivetrain);
    }
    protected void initialize(){
        
    }
    protected void execute(){ //You are already obsolete
        double antiTipScalar = 1.0;
        /*if(Robot.kElevator.getPos() > Constants.kElevMidway){
            antiTipScalar = Robot.kElevator.getPos() / Constants.kElevatorMaxPos;
        }*/
        double throttle = Robot.oi.getLeftYAxis() * antiTipScalar; //NANI
        double steering = Robot.oi.getRightXAxis() * antiTipScalar; //*DriveClosedLoop screeching*
        double left = (throttle + steering); //*Dies in open loop*
        double right = (throttle -steering); 
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