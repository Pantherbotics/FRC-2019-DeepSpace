package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Notifier;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Constants;

public class Limelight extends Subsystem {
    double x, y, area;
    NetworkTable table;
    NetworkTableEntry tx, ty, ta;

    public Limelight() {
        x = 0;
        y = 0;
        area = 0;

        try {
            table = NetworkTableInstance.getDefault().getTable("limelight");
            tx = table.getEntry("tx");
            ty = table.getEntry("ty");
            ta = table.getEntry("ta");

            Notifier limeLoop = new Notifier(() -> {
                x = tx.getDouble(0.0);
                y = ty.getDouble(0.0);
                area = ta.getDouble(0.0);
            });

            limeLoop.startPeriodic(0.02);
        }
        catch(Exception e) {
            System.out.println("No LemonLamp plugged in. A waste of $400 smh my head.");
        }
    }

    public double getX() { return x; } //horizontal angle

    public double getY() { return y; }

    public double getArea() { return area; }

    public void toggleLight(boolean on) {

    }

    @Override
    protected void initDefaultCommand() {
    }
}
