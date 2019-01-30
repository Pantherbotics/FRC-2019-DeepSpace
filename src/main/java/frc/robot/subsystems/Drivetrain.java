package frc.robot.subsystems;

//import javax.xml.bind.JAXBElement.GlobalScope;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.Notifier;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.hal.sim.DriverStationSim;

import frc.robot.Constants;
import frc.robot.commands.*;
import frc.robot.util.*;

public class Drivetrain extends Subsystem {
    public int timeoutMS = 0;
    int drive_ID = 0;
    TalonSRX mLeftA = new TalonSRX(Constants.kLeftA);
    TalonSRX mLeftB = new TalonSRX(Constants.kLeftB);
    TalonSRX mLeftC = new TalonSRX(Constants.kLeftC);
    TalonSRX mRightA = new TalonSRX(Constants.kRightA);
    TalonSRX mRightB = new TalonSRX(Constants.kRightB);
    TalonSRX mRightC = new TalonSRX(Constants.kRightC);
    
    AHRS gyro = new AHRS(I2C.Port.kOnboard);

    volatile double x, y, theta;
    private double lastPos, currentPos, dPos;

    public DriverStationSim test = new DriverStationSim();

    public Drivetrain(){
        mLeftA.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, timeoutMS);
        mLeftA.setSensorPhase(false);
        mRightA.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, timeoutMS);
        mRightA.setSensorPhase(false);

        mLeftA.setInverted(false);
        mLeftB.setInverted(false);
        mLeftC.setInverted(false);
        mRightA.setInverted(true);
        mRightB.setInverted(true);
        mRightC.setInverted(true);

        mLeftA.setNeutralMode(NeutralMode.Coast);
        mLeftB.setNeutralMode(NeutralMode.Coast);
        mLeftC.setNeutralMode(NeutralMode.Coast);
        mRightA.setNeutralMode(NeutralMode.Coast);
        mRightB.setNeutralMode(NeutralMode.Coast);
        mRightC.setNeutralMode(NeutralMode.Coast);

        mLeftB.follow(mLeftA);
        mLeftC.follow(mLeftA);
        mRightB.follow(mRightA);
        mRightC.follow(mRightA);

        //Test Simulator
        test.setDsAttached(true);
        test.setEnabled(false);
        
        initPID();
        x = 0;
        y = 0;
        theta = 0;

        Notifier odoThread = new Notifier(() ->{
            currentPos = (mLeftA.getSelectedSensorPosition(0) + mRightA.getSelectedSensorPosition(0))/2;
            dPos = Units.TalonNativeToFeet(currentPos - lastPos);
            theta = Math.toRadians(boundHalfDegrees(-gyro.getAngle()));
            x += Math.cos(theta)*dPos;
            y += Math.sin(theta)*dPos;
            lastPos = currentPos;
        });
        odoThread.startPeriodic(0.01);
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
        setDefaultCommand(new DriveOpenLoop()); //Not sure if delete or nah
    }

    public void setMotorPower(double left, double right){
        mLeftA.set(ControlMode.PercentOutput, left);
        mRightA.set(ControlMode.PercentOutput, right);
    }

    public void setFPS(double left, double right){
        mLeftA.set(ControlMode.Velocity, left);
        mRightA.set(ControlMode.Position, right);
    }

    public void zeroGyro(){
        gyro.reset();
        System.out.println("Rella Rella Pizza M- Gyro Reset.");
    }

    public Odometry getOdo(){
        return new Odometry(x, y, theta);
    }
    public void setOdo(Odometry odo){
        this.x = odo.getX();
        this.y = odo.getY();
        this.theta = odo.getTheta();
    }

    private double boundHalfDegrees(double angle_degrees) {
        while (angle_degrees >= 180.0) angle_degrees -= 360.0;
        while (angle_degrees < -180.0) angle_degrees += 360.0;
        return angle_degrees;
    }
}