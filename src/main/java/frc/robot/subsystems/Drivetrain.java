package frc.robot.subsystems;

//import javax.xml.bind.JAXBElement.GlobalScope;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
//import the heccin gyro

import edu.wpi.first.wpilibj.Notifier;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.hal.sim.DriverStationSim;
import frc.robot.Constants;
import frc.robot.commands.*;

public class Drivetrain extends Subsystem {
    public int timeoutMS = 0;
    int drive_ID = 0;
    TalonSRX mLeftA = new TalonSRX(Constants.kLeftA);
    TalonSRX mLeftB = new TalonSRX(Constants.kLeftB);
    TalonSRX mLeftC = new TalonSRX(Constants.kLeftC);
    TalonSRX mRightA = new TalonSRX(Constants.kRightA);
    TalonSRX mRightB = new TalonSRX(Constants.kRightB);
    TalonSRX mRightC = new TalonSRX(Constants.kRightC);
    
    public DriverStationSim test = new DriverStationSim();

    public Drivetrain(){
        mLeftA.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, timeoutMS);
        mLeftA.setSensorPhase(false);
        mRightA.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, timeoutMS);
        mRightA.setSensorPhase(false);
        //mLeftB.follow(mLeftA);
        //mLeftC.follow(mLeftA);
        //mRightB.follow(mRightA);
        //mRightC.follow(mRightA);
        mLeftA.setInverted(false);
        mLeftB.setInverted(false);
        mLeftC.setInverted(false);
        mRightA.setInverted(true);
        mRightB.setInverted(true);
        mRightC.setInverted(true);

        //Test Simulator
        test.setDsAttached(true);
        test.setEnabled(false);
        
        Notifier myNotifier = new Notifier(()->{
            try{
                Thread.sleep(2000);
            } catch(InterruptedException e){
                System.out.println("fuk");
            }
            test.setEnabled(true);
            System.out.println("Enabling auto");
            test.setAutonomous(true);
            try{
                Thread.sleep(15000);
            } catch(InterruptedException e){
                System.out.println("E");
            }
            System.out.println("Enabling teleop");
            test.setAutonomous(false);
            try{
                Thread.sleep(135000);
            } catch(InterruptedException e){
                System.out.println("lmoa");
            }
            System.out.println("Ending simulation");
            test.setEnabled(false);
        });

        myNotifier.startSingle(0.01);
    }

    public void initPID(){
        //left
        mLeftA.configAllowableClosedloopError(drive_ID, 0, timeoutMS);
        mLeftA.config_kP(drive_ID, Constants.DRIVE_P, timeoutMS);
        mLeftA.config_kI(drive_ID, Constants.DRIVE_I, timeoutMS);
        mLeftA.config_kD(drive_ID, Constants.DRIVE_D, timeoutMS);
        mLeftA.config_kF(drive_ID, Constants.DRIVE_F, timeoutMS);
        //right
        mRightA.configAllowableClosedloopError(drive_ID, 0, timeoutMS);
        mRightA.config_kP(drive_ID, Constants.DRIVE_P, timeoutMS);
        mRightA.config_kI(drive_ID, Constants.DRIVE_I, timeoutMS);
        mRightA.config_kD(drive_ID, Constants.DRIVE_D, timeoutMS);
        mRightA.config_kF(drive_ID, Constants.DRIVE_F, timeoutMS);
    }

    @Override
    public void initDefaultCommand(){
        setDefaultCommand(new DriveClosedLoop()); //Not sure if delete or nah
    }

    public void setMotorPower(double left, double right){
        mLeftA.set(ControlMode.PercentOutput, left);
        mRightA.set(ControlMode.PercentOutput, right);
    }

    public void setFPS(double left, double right){
        mLeftA.set(ControlMode.Velocity, left);
        mRightA.set(ControlMode.Velocity, right);
    }

    public void zeroGyro(){
        //nyo ho    
    }
}