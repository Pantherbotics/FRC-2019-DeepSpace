package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.util.CheesyDriveHelper;
import frc.robot.util.Units;

import static frc.robot.Robot.kVision;

public class DriveVisionAssisted extends Command{

    private static double turnKp = 0.75;



    public DriveVisionAssisted(){
        requires(Robot.kDrivetrain);
        requires(Robot.kVision);
    }
    protected void initialize(){
        kVision.enableLEDs();
    }

    protected void execute(){
        double turnPower = 0;
        if(kVision.isTarget()) {
            turnPower = turnKp * kVision.getRobotAttackAngle();
        }
        //System.out.print(turnPower);
        double left = Robot.oi.getLeftYAxis()  + turnPower;
        double right = Robot.oi.getLeftYAxis() - turnPower;
        Robot.kDrivetrain.setMotorPower(left, right);
    }

    protected boolean isFinished(){
        return false;
    }
    protected void end(){
    }
    protected void interrupted(){
        kVision.disableLEDs();
    }
}