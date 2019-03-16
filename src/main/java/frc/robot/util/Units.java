package frc.robot.util;

/** This is a helper class to convert from Talon Native Units to human-readable units, including Feet per Second.
 */
public class Units { //GRAYTHING ENCODER HAS 128 CPR SO 512 TPR            oh it's right here

    static final double talon2fps = .03067961572265625;           //( 1 rev/ 512 ticks) * (0.5pi ft/ 1 rev) * (10 [100ms] / 1 s)
    static final double fps2talon = 1/talon2fps;
    public static double FPSToTalonNative(double fps) {
        return fps * fps2talon;
    }

    public static double TalonNativeToFPS(double nativeUnits) {
        return nativeUnits * talon2fps;
    }

    public static double TalonNativeToFeet(double nativeUnits){
        return nativeUnits *.003067961;
    }

    public static double talonToDegrees(int in){
        return ((double)in / 1023.0 * 360.0);
    }

    public static int degreesToTalon(double in){
        return (int)((double) in / 360.0 * 1023.0);
    }

    public static double elevatorTicksToInches(int elevatorTicks){
        return (((double)elevatorTicks) * 1.25 * 3.14159) / 512.0;
    }

    public static double elevAccelToVoltage(double accel){ //Literally a proportion based on the elevator AFF.
        return (accel * 1.25 * 3.14159 / 512.0 * 100 * 0.0254) * 0.125 / 9.8;
    } //Acceleration needs to be converted from ticks/100ms/100ms to m/s^2


    public static int inchesToElevatorTicks(double inches){
        return (int)Math.round(inches * 512.0 / (1.25 * 3.14159));
    }

    public static double getTrueAttackAngle(double aa, double distance){ //This is your fault Aaron
        if(distance * Math.cos(aa) == 0){
            return 0;
        }
        return Math.PI / 2 - Math.atan((distance * Math.sin(aa) + 4) / (distance * Math.cos(aa))); // pi/2 - arctan((d*sinA + 4)/(d*cosA)) //
    }
    /*
    public static void main(String[] args){
        System.out.println(TalonNativeToFPS(770));
        System.out.println(FPSToTalonNative(7));
    }*/

}