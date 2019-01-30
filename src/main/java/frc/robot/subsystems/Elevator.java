package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.LimitSwitchSource;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Notifier;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Constants;

public class Elevator extends Subsystem {
    public int target = 0;
    public TalonSRX elevTalonA = new TalonSRX(Constants.kElevatorA);
    public TalonSRX elevTalonB = new TalonSRX(Constants.kElevatorB);
    public int timeout_ms = Constants.kElevatorTimeoutMS;

    public Elevator(){
        elevTalonB.follow(elevTalonA);
        initPID();

        Notifier elevNotifier = new Notifier(() ->{
            if(elevTalonA.getSelectedSensorPosition(0) > 0){ //Setpoint at which second stage begins lifting

            }
        });
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }

    public double getPos() {
        return elevTalonA.getSelectedSensorPosition(timeout_ms);
    }

    //Initalize PID settings
    public void initPID() {
        setTargetPosition(0);
        elevTalonA.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, timeout_ms);
        elevTalonA.setSensorPhase(false);
        elevTalonA.configNominalOutputForward(0, timeout_ms);
        elevTalonA.configNominalOutputReverse(0, timeout_ms);
        elevTalonA.configPeakOutputForward(1, timeout_ms);
        elevTalonA.configPeakOutputReverse(-1, timeout_ms);
        elevTalonA.configContinuousCurrentLimit(Constants.ELEVATOR_CURRENT_LIMIT, timeout_ms);

        elevTalonA.configForwardLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen, timeout_ms);

        elevTalonA.configAllowableClosedloopError(10, 0, timeout_ms);

        elevTalonA.config_kF(0, Constants.kElevator_F1, timeout_ms);
        elevTalonA.config_kP(0, Constants.kElevator_P, timeout_ms);
        elevTalonA.config_kI(0, Constants.kElevator_I, timeout_ms);
        elevTalonA.config_kD(0, Constants.kElevator_D, timeout_ms);

        elevTalonA.configMotionCruiseVelocity(Constants.ELEVATOR_PID_CRUISE_VEL, timeout_ms);
        elevTalonA.configMotionAcceleration(Constants.ELEVATOR_PID_ACCELERATION, timeout_ms);

        elevTalonA.configForwardSoftLimitThreshold(Constants.ELEVATOR_SOFT_LIMIT, timeout_ms);
        elevTalonA.configForwardSoftLimitEnable(true, timeout_ms);
        elevTalonA.configReverseSoftLimitThreshold(0, timeout_ms);
        elevTalonA.configReverseSoftLimitEnable(true, timeout_ms);

        setTargetPosition(elevTalonA.getSelectedSensorPosition(timeout_ms));
    }

    public void setTargetPosition(int new_target) {
        elevTalonA.configReverseSoftLimitEnable(true, timeout_ms);
        if (new_target > Constants.ELEVATOR_SOFT_LIMIT) {
            new_target = Constants.ELEVATOR_SOFT_LIMIT;
        } else if (new_target < 5) {
            new_target = 5;
        }
        target = new_target;
        elevTalonA.set(ControlMode.MotionMagic, new_target);
    }

    public void setMotorPower(double power) {
        elevTalonA.configReverseSoftLimitEnable(false, timeout_ms);
        elevTalonA.set(ControlMode.PercentOutput, power);
    }

    //Used by driving control systems to set speed limits. 
    public boolean isLiftRaised() {
        return (elevTalonA.getSelectedSensorPosition(timeout_ms) > 100);
    }

    public boolean isLiftLowered() {
        return elevTalonA.getSensorCollection().isRevLimitSwitchClosed();
    }

    public boolean testMotorCurrentThreshold(double amps) {
        return elevTalonA.getOutputCurrent() > amps;
    }

    public void zeroEncoder() {
        target = 0;
        elevTalonA.setSelectedSensorPosition(0, 0, timeout_ms);
        //System.out.println("Elevator encoder zeroed!");
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
        return elevTalonA.getSelectedSensorVelocity(timeout_ms);
    }


}

