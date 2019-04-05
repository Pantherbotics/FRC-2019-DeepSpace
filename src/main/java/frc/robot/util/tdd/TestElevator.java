package frc.robot.util.tdd;

public class TestElevator {
    //775 Info
    //Stall Torque (Nm) = Amount of force needed to stall the motor
    static double kStallTorque = 0.71;
    //Stall Current (A) = Current drawn when motor is stalled
    static double kStallCurrent = 134;
    //Free Speed (RPM) = Maximum speed under no load
    static double kFreeSpeed = 18730;
    //Free Current (A) = Maximum current under no load
    static double kFreeCurrent = 0.7;

    //Elevator Info
    //Mirage has 2 775s on the elevator gearbox
    static double kMotors = 2.0;
    //Motor Resistance
    static double kResistance = 12.0 / kStallCurrent;
    //Mass (8+5)lbs = 5.8967kg
    static double kMass = 5.8967;
    //Gear Ratio (775) 13.33:1 (Spool)
    static double kGR = 1/13.33;
    //Spool Diameter = 1.25in
    static double kR = 1.25 / 2;
    //Torque Constant
    static double kT = kMotors * kStallTorque / kStallCurrent;
    //Motor Velocity Constant (W No Load / V Peak)
    static double kV = kFreeSpeed / (kFreeCurrent * kResistance);
    //Friction = A constant that is subtracted from velocity
    static double kFriction = 0.0;

    private static double getAccel(double voltage){ //V = IR + W/kV, Torque = kT * I;
        return 0;
    }

    public void runSim(double voltage, double time){
        double simTime = 0.001; //Time step
        double currentTime = 0.0;

        while(currentTime < time){
            currentTime += simTime;
        }
    }
}
