package frc.robot.util;

import com.ctre.phoenix.motorcontrol.*;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class LazyTalonSRX extends TalonSRX {
    protected double mLastSet = Double.NaN;
    protected ControlMode mLastControlMode = null;
    protected DemandType mLastDemandType = null;
    protected double mLastDemand = Double.NaN;

    public LazyTalonSRX(int deviceNumber) {
        super(deviceNumber);
    }

    public double getLastSet() {
        return mLastSet;
    }


    //credit to 1323 and 254 for this set function
    //helps minimize CAN overhead by ignoring redundant commands.

    @Override
    public void set(ControlMode mode, double value) {
        if (value != mLastSet || mode != mLastControlMode) {
            mLastSet = value;
            mLastControlMode = mode;
            super.set(mode, value);
        }
    }

    @Override
    public void set(ControlMode mode, double value, DemandType demandType, double demand) {
        if (value != mLastSet || mode != mLastControlMode || demandType !=mLastDemandType || demand !=mLastDemand) {
            mLastSet = value;
            mLastControlMode = mode;
            mLastDemandType = demandType;
            mLastDemand = demand;
            super.set(mode, value);
        }
    }


}