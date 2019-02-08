//this code was made by team 3863 FIRST Robotics, Newbury Park, CA 91320
package frc.robot.subsystems;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Notifier;
import frc.robot.Constants;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.LimitSwitchSource;
import edu.wpi.first.wpilibj.Notifier;

public class Elevator extends Subsystem{
    private TalonSRX mElevA = new TalonSRX(Constants.kElevatorA);
    private TalonSRX mElevB = new TalonSRX(Constants.kElevatorB);
    public Elevator(){
        mElevB.follow(mElevA);
        mElevB.setInverted(false);
        mElevA.setInverted(false);
        mElevA.setSensorPhase(true);
        mElevA.config_kP(Constants.lowElev_ID, Constants.elevatorKP, Constants.timeoutMS);
        mElevA.config_kI(Constants.lowElev_ID, Constants.elevatorKI, Constants.timeoutMS);
        mElevA.config_kD(Constants.lowElev_ID, Constants.elevatorKD, Constants.timeoutMS);
        mElevA.config_kF(Constants.lowElev_ID, Constants.elevatorKF1, Constants.timeoutMS);
        mElevA.config_kP(Constants.highElev_ID, Constants.elevatorKP, Constants.timeoutMS);
        mElevA.config_kI(Constants.highElev_ID, Constants.elevatorKI, Constants.timeoutMS);
        mElevA.config_kD(Constants.highElev_ID, Constants.elevatorKD, Constants.timeoutMS);
        mElevA.config_kF(Constants.highElev_ID, Constants.elevatorKF2, Constants.timeoutMS);
        mElevA.configMotionCruiseVelocity(Constants.elevatorCruiseSpeed, Constants.timeoutMS);          
        mElevA.configMotionAcceleration(Constants.elevatorAccelerationSpeed, Constants.timeoutMS);      
        mElevA.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, Constants.lowElev_ID, Constants.timeoutMS);
        mElevA.configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen, 0);
        mElevA.setSelectedSensorPosition(0);
        
        Notifier elevThread = new Notifier(() ->{
            if(getPos() > Constants.elevMidway){
                mElevA.selectProfileSlot(Constants.highElev_ID, 0);
            } else{
                mElevA.selectProfileSlot(Constants.lowElev_ID, 0);
            }
        });
        elevThread.startPeriodic(0.01);
    }
    public void initDefaultCommand(){

    }
    public int getPos(){
        return mElevA.getSelectedSensorPosition(Constants.lowElev_ID);
    }
    public int getVelocity(){
        return mElevA.getSelectedSensorVelocity(Constants.lowElev_ID);
    }

    public void setPower(double power){
        mElevA.set(ControlMode.PercentOutput, -power);
    }
    public void setPos(int pos){
        mElevA.set(ControlMode.MotionMagic, pos);
    }
    public boolean getLimitSwitch(){
        return mElevA.getSensorCollection().isRevLimitSwitchClosed();
    } 
    public void setElevatorEncoder(int pos){
        mElevA.setSelectedSensorPosition(pos, Constants.lowElev_ID, Constants.timeoutMS);
    }
}