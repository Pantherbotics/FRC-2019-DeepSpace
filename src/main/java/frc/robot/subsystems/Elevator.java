package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.LimitSwitchSource;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Notifier;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Constants;
import frc.robot.commands.*;

public class Elevator extends Subsystem {
    public int target = 0;
    public int lowID = 0;
    public int highID = 1;
    public TalonSRX mElevA = new TalonSRX(Constants.kElevatorA);
    public TalonSRX mElevB = new TalonSRX(Constants.kElevatorB);
    public int timeout_ms = Constants.kElevatorTimeoutMS;

    public Elevator(){
        mElevA.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, timeout_ms);
        mElevA.setSensorPhase(true);
        mElevA.configNominalOutputForward(0, timeout_ms);
        mElevA.configNominalOutputReverse(0, timeout_ms);
        mElevA.configPeakOutputForward(1, timeout_ms);
        mElevA.configPeakOutputReverse(-1, timeout_ms);
        mElevA.configContinuousCurrentLimit(Constants.ELEVATOR_CURRENT_LIMIT, timeout_ms);

        mElevA.configForwardLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen, timeout_ms);

        mElevA.configAllowableClosedloopError(10, 0, timeout_ms);

        mElevA.selectProfileSlot(lowID, 0);

        mElevA.configMotionCruiseVelocity(Constants.ELEVATOR_PID_CRUISE_VEL, timeout_ms);
        mElevA.configMotionAcceleration(Constants.ELEVATOR_PID_ACCELERATION, timeout_ms);

        mElevA.configForwardSoftLimitThreshold(Constants.ELEVATOR_SOFT_LIMIT, timeout_ms);
        mElevA.configForwardSoftLimitEnable(true, timeout_ms);
        mElevA.configReverseSoftLimitThreshold(0, timeout_ms);
        mElevA.configReverseSoftLimitEnable(true, timeout_ms);

        setTargetPosition(mElevA.getSelectedSensorPosition(0));

        mElevB.follow(mElevA);
        initPID();

        Notifier elevNotifier = new Notifier(() ->{
            if(mElevA.getSelectedSensorPosition(0) > 0){ //Setpoint at which second stage begins lifting
                mElevA.selectProfileSlot(highID, 0);
            } else{ //Lowered elevator
                mElevA.selectProfileSlot(lowID, 0);
            }
        });
        elevNotifier.startPeriodic(0.01);
    }

    public void initDefaultCommand() {
        //setDefaultCommand(new ElevatorOpenLoop());
    }

    public double getPos() {
        return mElevA.getSelectedSensorPosition(0);
    }

    //Initalize PID settings
    public void initPID() {
        setTargetPosition(0);
        //Lowered
        mElevA.config_kF(lowID, Constants.kElevator_F1, timeout_ms);
        mElevA.config_kP(lowID, Constants.kElevator_P, timeout_ms);
        mElevA.config_kI(lowID, Constants.kElevator_I, timeout_ms);
        mElevA.config_kD(lowID, Constants.kElevator_D, timeout_ms);
        //Raised
        mElevA.config_kF(highID, Constants.kElevator_F2, timeout_ms);
        mElevA.config_kP(highID, Constants.kElevator_P, timeout_ms);
        mElevA.config_kI(highID, Constants.kElevator_I, timeout_ms);
        mElevA.config_kD(highID, Constants.kElevator_D, timeout_ms);
    }

    public void setTargetPosition(int new_target) {
        mElevA.configReverseSoftLimitEnable(true, timeout_ms);
        if (new_target > Constants.ELEVATOR_SOFT_LIMIT) {
            new_target = Constants.ELEVATOR_SOFT_LIMIT;
        } else if (new_target < 5) {
            new_target = 5;
        }
        target = new_target;
        mElevA.set(ControlMode.MotionMagic, new_target);
    }

    public void setMotorPower(double power) {
        mElevA.configReverseSoftLimitEnable(false, timeout_ms);
        mElevA.configForwardSoftLimitEnable(false, timeout_ms);
        mElevA.set(ControlMode.PercentOutput, power);
    }

    //Used by driving control systems to set speed limits. 
    public boolean isLiftRaised() {
        return (mElevA.getSelectedSensorPosition(timeout_ms) > 100);
    }

    public boolean isLiftLowered() {
        return mElevA.getSensorCollection().isRevLimitSwitchClosed();
    }

    public boolean testMotorCurrentThreshold(double amps) {
        return mElevA.getOutputCurrent() > amps;
    }

    public void zeroEncoder() {
        target = 0;
        mElevA.setSelectedSensorPosition(0, 0, timeout_ms);
        //System.out.println("Elevator Zeroed!");
    }

    public void goToPreset(int presetID) {
        if (presetID == 0) {
            //zeroEncoder();
            setTargetPosition(Constants.ELEVATOR_PRESETS[presetID]);
        } else {
            setTargetPosition(Constants.ELEVATOR_PRESETS[presetID]);
        }

    }

    public double getHeightPercent() {
        return (getPos() / Constants.ELEVATOR_SOFT_LIMIT);
    }

    public double getVel() {
        return mElevA.getSelectedSensorVelocity(timeout_ms);
    }


}

