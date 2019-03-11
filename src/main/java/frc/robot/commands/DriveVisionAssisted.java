package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.util.CheesyDriveHelper;

import static frc.robot.Robot.kVision;

public class DriveVisionAssisted extends Command{

    private static double turnKp = 1.0;



    public DriveVisionAssisted(){
        requires(Robot.kDrivetrain);
        requires(kVision);
    }
    protected void initialize(){
        
    }
    protected void execute(){
        double turnPower = turnKp * kVision.getAttackAngle();
        double left = Robot.oi.getLeftYAxis()  - turnPower;
        double right = Robot.oi.getLeftYAxis() + turnPower;
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