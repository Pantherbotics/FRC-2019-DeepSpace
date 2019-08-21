package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.*;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Constants;

public class Climber extends Subsystem {
    private TalonSRX mClimbA = new TalonSRX(Constants.climbAID);
    private TalonSRX mClimbB = new TalonSRX(Constants.climbBID);
    private VictorSPX mSuction = new VictorSPX(Constants.suctionID);
    private Solenoid deploySolenoid = new Solenoid(Constants.kDeploySolenoidID);
    private DigitalInput botSwitch = new DigitalInput(1);


    public Climber() {
        mClimbB.follow(mClimbA);
        mClimbA.setNeutralMode(NeutralMode.Brake);

        deploySolenoid.set(true); //Pulls arm in
        mClimbA.set(ControlMode.PercentOutput, 0);
    }

    public void setPower(double power) {
        //Negative is down
        mClimbA.set(ControlMode.PercentOutput, power);
    }

    public void setDeploy(boolean deploy) { //False for deployed
        deploySolenoid.set(deploy);
    }

    public void setSuction(double power) {
        mSuction.set(ControlMode.PercentOutput, power);
    }

    public boolean isSuccing() {
        return (Math.abs(mSuction.getMotorOutputPercent()) > 0.25);
    }

    public double getVoltage() {
        return mClimbA.getMotorOutputVoltage();
    }

    public boolean getSensor() {
        return botSwitch.get();
    }

    public void initDefaultCommand() {}
}
