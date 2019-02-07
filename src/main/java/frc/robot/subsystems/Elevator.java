//this code was made by team 3863 FIRST Robotics, Newbury Park, CA 91320
package frc.robot.subsystems;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Constants;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.LimitSwitchSource;

public class Elevator extends Subsystem{
    private TalonSRX mElevA = new TalonSRX(Constants.kElevatorA);
    private TalonSRX mElevB = new TalonSRX(Constants.kElevatorB);
    public Elevator(){
        mElevB.follow(mElevA);
        mElevB.setInverted(false);
        mElevA.setInverted(false);
        mElevA.setSensorPhase(true);
        mElevA.config_kP(Constants.primaryPIDIDX, Constants.elevatorKP, Constants.timeoutMS);
        mElevA.config_kI(Constants.primaryPIDIDX, Constants.elevatorKI, Constants.timeoutMS);
        mElevA.config_kD(Constants.primaryPIDIDX, Constants.elevatorKD, Constants.timeoutMS);
        mElevA.config_kF(Constants.primaryPIDIDX, Constants.elevatorKF, Constants.timeoutMS);           
        mElevA.configMotionCruiseVelocity(Constants.elevatorCruiseSpeed, Constants.timeoutMS);          
        mElevA.configMotionAcceleration(Constants.elevatorAccelerationSpeed, Constants.timeoutMS);      
        mElevA.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, Constants.primaryPIDIDX, Constants.timeoutMS);
        mElevA.configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen, 0);
        mElevA.setSelectedSensorPosition(0);
    }
    public void initDefaultCommand(){

    }
    public int getPos(){
        return mElevA.getSelectedSensorPosition(Constants.primaryPIDIDX);
    }
    public int getVelocity(){
        return mElevA.getSelectedSensorVelocity(Constants.primaryPIDIDX);
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
        mElevA.setSelectedSensorPosition(pos, Constants.primaryPIDIDX, Constants.timeoutMS);
    }
}