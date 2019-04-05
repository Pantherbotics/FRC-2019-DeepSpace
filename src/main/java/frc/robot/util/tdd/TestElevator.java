//3863 Pantherbotics's attempt at replicating Test Driven Development

package frc.robot.util.tdd;

public class TestElevator {
    //775 Info
    //Stall Torque (Nm) = Amount of force needed to stall the motor
    static final double kStallTorque = 0.71;
    //Stall Current (A) = Current drawn when motor is stalled
    static final double kStallCurrent = 134;
    //Free Speed (RPM) = Maximum speed under no load
    static final double kFreeSpeed = 18730;
    //Free Current (A) = Maximum current under no load
    static final double kFreeCurrent = 0.7;

    //Elevator Info
    //Mirage has 2 775s on the elevator gearbox
    static final double kMotors = 2.0;
    //Motor Resistance
    static final double kResistance = 12.0 / kStallCurrent;
    //Mass (8+5)lbs = 5.8967kg
    static final double kMass = 5.8967;
    //Gear Ratio (775) 13.33:1 (Spool)
    static final double kGR = 1/13.33;
    //Spool Diameter = 1.25in ==> 0.03175m
    static final double kR = 0.03175 / 2; //(m)
    //Torque Constant
    static final double kT = kMotors * kStallTorque / kStallCurrent;
    //Motor Velocity Constant (W No Load / V Peak)
    static final double kV = kFreeSpeed / (kFreeCurrent * kResistance);
    //Friction = A constant that is subtracted from velocity
    static final double kFriction = 0.0;

    //Velocity (m/s)
    public static double velocity = 0;
    //Position (m)
    public static double position = 0;

    //acceleration of the elevator
    private static double getAccel(double voltage){ //
        return kGR * kT * (voltage - kGR * velocity /(kR * kV)) / (kResistance * kMass * kR);
    }

    public void runSim(double voltage, double time){
        double simTime = 0.001; //Time step
        double currentTime = 0.0;

        while(currentTime < time){
            currentTime += simTime;

            double acceleration = getAccel(voltage);
            position += simTime * velocity; //Austin Schuh says this order big good
            velocity += simTime * acceleration - kFriction;
        }
    }
}
