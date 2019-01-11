package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.LimitSwitchSource;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Constants;

public class Elevator extends Subsystem {
    public int target = 0;
    public WPI_TalonSRX elevDriveTalon = new WPI_TalonSRX(Constants.kElevatorID);
    public int timeout_ms = Constants.kElevatorTimeoutMS;

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }

    public double getPos() {
        return elevDriveTalon.getSelectedSensorPosition(timeout_ms);
    }

    //Initalize PID settings
    public void initPID() {
        setTargetPosition(0);
        elevDriveTalon.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, timeout_ms);
        elevDriveTalon.setSensorPhase(false);
        elevDriveTalon.configNominalOutputForward(0, timeout_ms);
        elevDriveTalon.configNominalOutputReverse(0, timeout_ms);
        elevDriveTalon.configPeakOutputForward(1, timeout_ms);
        elevDriveTalon.configPeakOutputReverse(-1, timeout_ms);
        elevDriveTalon.configContinuousCurrentLimit(Constants.ELEVATOR_CURRENT_LIMIT, timeout_ms);

        elevDriveTalon.configForwardLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen, timeout_ms);

        elevDriveTalon.configAllowableClosedloopError(10, 0, timeout_ms);

        elevDriveTalon.config_kF(0, Constants.kElevator_F, timeout_ms);
        elevDriveTalon.config_kP(0, Constants.kElevator_P, timeout_ms);
        elevDriveTalon.config_kI(0, Constants.kElevator_I, timeout_ms);
        elevDriveTalon.config_kD(0, Constants.kElevator_D, timeout_ms);

        elevDriveTalon.configMotionCruiseVelocity(Constants.ELEVATOR_PID_CRUISE_VEL, timeout_ms);
        elevDriveTalon.configMotionAcceleration(Constants.ELEVATOR_PID_ACCELERATION, timeout_ms);

        elevDriveTalon.configForwardSoftLimitThreshold(Constants.ELEVATOR_SOFT_LIMIT, timeout_ms);
        elevDriveTalon.configForwardSoftLimitEnable(true, timeout_ms);
        elevDriveTalon.configReverseSoftLimitThreshold(0, timeout_ms);
        elevDriveTalon.configReverseSoftLimitEnable(true, timeout_ms);

        setTargetPosition(elevDriveTalon.getSelectedSensorPosition(timeout_ms));
    }

    public void setTargetPosition(int new_target) {
        elevDriveTalon.configReverseSoftLimitEnable(true, timeout_ms);
        if (new_target > Constants.ELEVATOR_SOFT_LIMIT) {
            new_target = Constants.ELEVATOR_SOFT_LIMIT;
        } else if (new_target < 5) {
            new_target = 5;
        }
        target = new_target;
        elevDriveTalon.set(ControlMode.MotionMagic, new_target);
    }

    public void setMotorPower(double power) {
        elevDriveTalon.configReverseSoftLimitEnable(false, timeout_ms);
        elevDriveTalon.set(ControlMode.PercentOutput, power);
    }

    //Used by driving control systems to set speed limits. 
    public boolean isLiftRaised() {
        return (elevDriveTalon.getSelectedSensorPosition(timeout_ms) > 100);
    }

    public boolean isLiftLowered() {
        return elevDriveTalon.getSensorCollection().isRevLimitSwitchClosed();
    }

    public boolean testMotorCurrentThreshold(double amps) {
        return elevDriveTalon.getOutputCurrent() > amps;
    }

    public void zeroEncoder() {
        target = 0;
        elevDriveTalon.setSelectedSensorPosition(0, 0, timeout_ms);
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
        return elevDriveTalon.getSelectedSensorVelocity(timeout_ms);
    }


}

