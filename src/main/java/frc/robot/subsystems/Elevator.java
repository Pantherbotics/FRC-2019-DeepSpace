//this code was made by team 3863 FIRST Robotics, Newbury Park, CA 91320
package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.*;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Notifier;
import frc.robot.Constants;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.DemandType;
import com.ctre.phoenix.motorcontrol.LimitSwitchSource;
import frc.robot.commands.IncrementElevator;
import frc.robot.util.Units;

public class Elevator extends Subsystem{
    private TalonSRX mElevA = new TalonSRX(Constants.elevatorAID);
    private TalonSRX mElevB = new TalonSRX(Constants.elevatorBID);
    private double lastVel;
    private double acceleration;

    public Elevator(){
        mElevB.follow(mElevA);
        mElevB.setInverted(false);
        mElevA.setInverted(false);
        mElevA.setSensorPhase(true);
        mElevA.setNeutralMode(NeutralMode.Brake);
        mElevA.config_kP(Constants.lowElev_ID, Constants.elevatorKP, Constants.timeoutMS);
        mElevA.config_kI(Constants.lowElev_ID, Constants.elevatorKI, Constants.timeoutMS);
        mElevA.config_kD(Constants.lowElev_ID, Constants.elevatorKD, Constants.timeoutMS);
        mElevA.config_kF(Constants.lowElev_ID, Constants.elevatorKF1, Constants.timeoutMS);

        mElevA.config_kP(Constants.highElev_ID, Constants.elevatorKP, Constants.timeoutMS);
        mElevA.config_kI(Constants.highElev_ID, Constants.elevatorKI, Constants.timeoutMS);
        mElevA.config_kD(Constants.highElev_ID, Constants.elevatorKD, Constants.timeoutMS);
        mElevA.config_kF(Constants.highElev_ID, Constants.elevatorKF2, Constants.timeoutMS);

        mElevA.configMotionCruiseVelocity(Constants.kElevatorCruiseSpeed, Constants.timeoutMS);
        mElevA.configMotionAcceleration(Constants.kElevatorAccelerationSpeed, Constants.timeoutMS);
        mElevA.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, Constants.lowElev_ID, Constants.timeoutMS);
        mElevA.configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen, 0);
        mElevA.setSelectedSensorPosition(0);
        mElevA.configForwardSoftLimitThreshold(Constants.kElevatorMaxPos);
        mElevA.configForwardSoftLimitEnable(true);

        Notifier elevThread = new Notifier(() ->{
            acceleration = (mElevA.getSelectedSensorVelocity(0) - lastVel) / Constants.elevPeriod;
            lastVel = mElevA.getSelectedSensorVelocity(0);
        });
        elevThread.startPeriodic(Constants.elevPeriod);
    }
    public void initDefaultCommand(){
       setDefaultCommand(new IncrementElevator());
    }
    public int getPos(){
        return mElevA.getSelectedSensorPosition(0);
    }

    public double getPosInches(){
        return Units.elevatorTicksToInches(mElevA.getSelectedSensorPosition(0));
    }

    public int getVelocity(){
        return mElevA.getSelectedSensorVelocity(0);
    }

    public double getAcceleration(){
        return acceleration;
    }

    public double getVoltage(){
        return mElevA.getMotorOutputVoltage();
    }

    public void setPower(double power){
        mElevA.set(ControlMode.PercentOutput, -power);
    }

    public void setPos(int pos){
            mElevA.set(ControlMode.MotionMagic, pos, DemandType.ArbitraryFeedForward, Constants.elevatorAFF);
    }

    public boolean getLimitSwitch(){
        return mElevA.getSensorCollection().isRevLimitSwitchClosed();
    }

    public void setElevatorEncoder(int pos){
        mElevA.setSelectedSensorPosition(pos, Constants.lowElev_ID, Constants.timeoutMS);
    }

    public int getError(){
        return mElevA.getClosedLoopError();
    }
}