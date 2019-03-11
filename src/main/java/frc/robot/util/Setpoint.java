package frc.robot.util;

import frc.robot.Constants;

public class Setpoint {

    double elevatorInches, shoulderDegrees;
    public Setpoint (double elevatorInchesFromGround, double shoulderDegreesFromHorizontal){
        elevatorInches = elevatorInchesFromGround;
        shoulderDegrees = shoulderDegreesFromHorizontal;
    }

    public int checkViable(){
        return 0;
    }

    public int getElevatorTicks(){
        return Units.inchesToElevatorTicks(elevatorInches);
    }

    public int getShoulderTicks(){
        return Units.degreesToTalon(shoulderDegrees);
    }
}
