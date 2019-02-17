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

    public static double TalonAnalogToDegrees(int in){
        return ((double)in / 1023.0 * 360.0);
    }

    public static int DegreesToTalonAnalog(double in){
        return (int)((double) in / 360.0 * 1023.0);
    }
    /*
    public static void main(String[] args){
        System.out.println(TalonNativeToFPS(770));
        System.out.println(FPSToTalonNative(7));
    }*/

}