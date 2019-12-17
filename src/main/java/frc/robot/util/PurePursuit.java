package frc.robot.util;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Trajectory.Segment;

/*  3863 implementation of PurePursuit
    https://www.ri.cmu.edu/pub_files/pub3/coulter_r_craig_1992_1/coulter_r_craig_1992_1.pdf
    
*/

public class PurePursuit { //This is probably the worst thing I [Matthew] have ever written - MS

    private Trajectory path;
    private double wheelBase;
    private double lookahead = 4.75; //lookahead distance in ft
    private Odometry odom;
    private int index = 0;
    private int length = 0;
    private double k = 0.75;

    public PurePursuit(double wheelBase, Trajectory path) {
        this.wheelBase = wheelBase;
        this.path = path;
        getInitOdom();
        length = path.length();
    }

    public DriveSignal getNextDriveSignal() {
        double left;
        double right;

        int lookIndex = getLookaheadIndex(index);
        //int lookIndex = getSimpleLookahead(index);

        Segment look = path.get(lookIndex);
        setOdom(Robot.kDrivetrain.getOdo());

        double R = getRadius(odom.getX(), odom.getY(), look.x, look.y);
        double W = getAngularVelocity(odom.getTheta(), look.heading, index, lookIndex);
        double sign = getSign(odom.getTheta(), look.heading);

        SmartDashboard.putNumber("R:", R);
        SmartDashboard.putNumber("W:", W);
        SmartDashboard.putNumber("Index:", index);
        SmartDashboard.putNumber("LIndex:", lookIndex);

        //Velocities
        left = k * W * (R + sign * wheelBase / 2); //  Angluar Velocity * Radius
        right = k * W * (R - sign * wheelBase / 2);
        //The greater one indicates the direction of the turn

        if (left > 15) { left = 15; }
        if (right > 15) { right = 15; }

        SmartDashboard.putNumber("Left:", left);
        SmartDashboard.putNumber("Right:", right);

        index++;
        return new DriveSignal(left, right);
    }

    private double getRadius(double x1, double y1, double x2, double y2) {
        //x axis is the long side of the field.
        double dx = Math.abs(y2 - y1);
        double dy = x2 - x1;

        SmartDashboard.putNumber("dx:", dx);
        SmartDashboard.putNumber("dy:", dy);
        //Equation derivation in the link above
        return (dx < 0.1) ? 9999 : (Math.pow(dx, 2) + Math.pow(dy, 2)) / (2 * dx);
        //horrifying
    }

    private double getAngularVelocity(double th1, double th2, int i1, int i2) { // W = Theta/s (radians)
        double dTh = Math.abs(th2 - th1);
        SmartDashboard.putNumber("dt:", dTh);
        //Jank limits
        return (Math.abs(dTh) < 0.0001) ? 0.0001 : (dTh) / (0.02 * (double)(i2 - i1)); //Delta theta / delta time
    }

    private double getSign(double th1, double th2) {
        //Heading basically works like a sine unit circle
        //So, left > 0, right < 0

        //Returns 1(left) or -1(right)
        return (th2 - th1) / Math.abs(th2 - th1);
    }

    private int getLookaheadIndex(int index) { //Find the segment closest to the lookahead distance
        double d = 0;
        double dTh = 0;

        //If it's within ? inches of the lookahead distance, it counts it
        for (int i = index; i < length; i++) {
            //d = sqrt( dx^2 + dy^2 )
            d = Math.sqrt( Math.pow(path.get(i).x - odom.getX(), 2) + Math.pow(path.get(i).y - odom.getY(), 2) );
            dTh = path.get(i).heading - path.get(index).heading;
            //checks if distance is within 0.05ft of the lookahead distance
            //Also just returns current index as the lookahead index if dTheta > 90deg
            if (Math.abs(lookahead -  d) <= 0.3 || Math.abs(dTh) > (Math.PI / 2))
                return i;
        }
        //If it doesn't find one, sets the lookahead index to the final segment
        return length - 1;
    }

    private int getSimpleLookahead(int index) {
        return (index + 30) > length ? length - 1 : index + 30;
    }

    public void setOdom(Odometry odo) {
        odom = odo;
    }

    public Odometry getInitOdom() {
        return new Odometry(path.get(0).x, path.get(0).y, path.get(0).heading);
    }

    public boolean isFinished() {
        return index == path.length();
    }
}