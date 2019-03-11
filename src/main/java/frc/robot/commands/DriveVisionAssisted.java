package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.util.CheesyDriveHelper;

public class DriveVisionAssisted extends Command{

    private double visionAngleErrorDeg;
    private static double turnKp = 1.0;

    private double lastError;

    public DriveVisionAssisted(){
        requires(Robot.kDrivetrain);
        lastError = visionAngleErrorDeg;
    }
    protected void initialize(){
        
    }
    protected void execute(){ //You are already obsolete
        /* ADD STUFF TO GET ANGLE ERROR FROM JEVOIS */
        double turnPower = turnKp * visionAngleErrorDeg;
        double left = Robot.oi.getLeftYAxis()  - turnPower;
        double right = Robot.oi.getLeftYAxis() + turnPower;
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