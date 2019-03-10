package frc.robot.commands;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.Constants;
import frc.robot.util.CheesyDriveHelper;

public class DriveOpenLoop extends Command{
    CheesyDriveHelper chez = new CheesyDriveHelper();
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
        double throttle = Constants.kThrottle * Robot.oi.getLeftYAxis() * antiTipScalar; //NANI
        double steering = Constants.kSteering * Robot.oi.getRightXAxis() * antiTipScalar; //*DriveClosedLoop screeching*
        double left = chez.cheesyDrive(throttle, steering, true).getLeft();
        double right = chez.cheesyDrive(throttle, steering, true).getRight();
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