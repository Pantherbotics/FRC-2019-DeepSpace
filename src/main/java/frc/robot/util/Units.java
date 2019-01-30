package frc.robot.util;

/** This is a helper class to convert from Talon Native Units to human-readable units, including Feet per Second.
 */
public class Units {

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
    /*
    public static void main(String[] args){
        System.out.println(TalonNativeToFPS(770));
        System.out.println(FPSToTalonNative(7));
    }*/

}