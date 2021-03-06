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
    TalonSRX mLeftA = new TalonSRX(Constants.leftAID);
    TalonSRX mLeftB = new TalonSRX(Constants.leftBID);
    TalonSRX mLeftC = new TalonSRX(Constants.leftCID);
    TalonSRX mRightA = new TalonSRX(Constants.rightAID);
    TalonSRX mRightB = new TalonSRX(Constants.rightBID);
    TalonSRX mRightC = new TalonSRX(Constants.rightCID);
    
    AHRS gyro = new AHRS(I2C.Port.kOnboard);

    volatile double x, y, theta;
    private double lastPos, currentPos, dPos;

    public DriverStationSim test = new DriverStationSim();

    public Drivetrain(){
        mLeftA.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, timeoutMS);
        mLeftA.setSensorPhase(true);
        mRightA.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, timeoutMS);
        mRightA.setSensorPhase(true);
        //Invert Right Side
        mLeftA.setInverted(false);
        mLeftB.setInverted(false);
        mLeftC.setInverted(false);
        mRightA.setInverted(true);
        mRightB.setInverted(true);
        mRightC.setInverted(true);
        //Coast for West Coast
        mLeftA.setNeutralMode(NeutralMode.Brake);
        mLeftB.setNeutralMode(NeutralMode.Brake);
        mLeftC.setNeutralMode(NeutralMode.Brake);
        mRightA.setNeutralMode(NeutralMode.Brake);
        mRightB.setNeutralMode(NeutralMode.Brake);
        mRightC.setNeutralMode(NeutralMode.Brake);
        //Ramp
        mLeftA.configClosedloopRamp(Constants.kDriveRamp, timeoutMS);
        mLeftB.configClosedloopRamp(Constants.kDriveRamp, timeoutMS);
        mLeftC.configClosedloopRamp(Constants.kDriveRamp, timeoutMS);
        mRightA.configClosedloopRamp(Constants.kDriveRamp, timeoutMS);
        mRightB.configClosedloopRamp(Constants.kDriveRamp, timeoutMS);
        mRightC.configClosedloopRamp(Constants.kDriveRamp, timeoutMS);
        //Electricity Stuff
        mLeftA.configPeakCurrentLimit(Constants.kDrivePeakCurrentLimit, timeoutMS);
        mLeftA.configPeakCurrentDuration(Constants.kDrivePeakCurrentDuration, timeoutMS);
        mLeftA.configContinuousCurrentLimit(Constants.kDriveContinuousCurrentLimit, timeoutMS);
        mLeftB.configPeakCurrentLimit(Constants.kDrivePeakCurrentLimit, timeoutMS);
        mLeftB.configPeakCurrentDuration(Constants.kDrivePeakCurrentDuration, timeoutMS);
        mLeftB.configContinuousCurrentLimit(Constants.kDriveContinuousCurrentLimit, timeoutMS);
        //Changed name of slave method to be more PC
        mLeftB.follow(mLeftA);
        mLeftC.follow(mLeftA);
        mRightB.follow(mRightA);
        mRightC.follow(mRightA);

        //Test Simulator
        test.setDsAttached(true);
        test.setEnabled(false);
        
        initPID();
        zeroGyro();
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
        mLeftA.config_kP(drive_ID, Constants.driveKP, timeoutMS);
        mLeftA.config_kI(drive_ID, Constants.driveKI, timeoutMS);
        mLeftA.config_kD(drive_ID, Constants.driveKD, timeoutMS);
        mLeftA.config_kF(drive_ID, Constants.driveKF, timeoutMS);
        //right
        mRightA.configAllowableClosedloopError(drive_ID, 0, timeoutMS);
        mRightA.config_kP(drive_ID, Constants.driveKP, timeoutMS);
        mRightA.config_kI(drive_ID, Constants.driveKI, timeoutMS);
        mRightA.config_kD(drive_ID, Constants.driveKD, timeoutMS);
        mRightA.config_kF(drive_ID, Constants.driveKF, timeoutMS);
    }

    @Override
    public void initDefaultCommand(){
        setDefaultCommand(new DriveOpenLoop()); //It worked but only partially
    }

    public void setMotorPower(double left, double right){
        mLeftA.set(ControlMode.PercentOutput, left);
        mRightA.set(ControlMode.PercentOutput, right);
        //System.out.println("Left: " + getEncoderVelocity()[0] + "     Right: " + getEncoderVelocity()[1]);
    }

    public void setFPS(double left, double right){
        mLeftA.set(ControlMode.Velocity, Units.FPSToTalonNative(left));
        mRightA.set(ControlMode.Velocity, Units.FPSToTalonNative(right));
        //System.out.println("Left: " + getEncoderVelocity()[0] + "     Right: " + getEncoderVelocity()[1]);
    }

    public double[] getEncoderVelocity(){ //0 for left, 1 for right
        return new double[]{mLeftA.getSelectedSensorVelocity(0), mRightA.getSelectedSensorVelocity(0)};
    }

    public double[] getVoltage(){
        return new double[]{mLeftA.getMotorOutputVoltage(), mRightA.getMotorOutputVoltage()};
    }

    public void zeroGyro(){ //Pizza Mozzerella Pizza Mozzerella Rella Rella Rella Rella
        gyro.reset();
        System.out.println("Gyro Reset");
    }

    public double getGyroAngle(){
        return gyro.getAngle();
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