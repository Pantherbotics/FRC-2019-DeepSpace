package frc.robot.util;

public class Setpoint {

    double elevatorInches, shoulderDegrees, wristDegrees;
    public Setpoint (double elevatorInchesFromGround, double shoulderDegreesFromHorizontal, double wristDegreesFromHorizontal){
        elevatorInches = elevatorInchesFromGround;
        shoulderDegrees = shoulderDegreesFromHorizontal;
        wristDegrees = wristDegreesFromHorizontal;
    }

    public int getElevatorTicks(){
        return Units.inchesToElevatorTicks(elevatorInches);
    }

    public int getShoulderTicks(){
        return Units.degreesToTalon(shoulderDegrees);
    }

    public int getWristTicks(){
        return Units.degreesToTalon(wristDegrees);
    }
}
