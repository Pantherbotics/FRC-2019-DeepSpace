package frc.robot.util;

import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Trajectory.Segment;

/*  3863 implementation of PurePursuit
    https://www.ri.cmu.edu/pub_files/pub3/coulter_r_craig_1992_1/coulter_r_craig_1992_1.pdf
    
*/

public class PurePursuit { //This is probably the worst thing I [Matthew] have ever written - MS

    private Trajectory path;
    private double wheelBase;
    private double lookahead = 3.75; //lookahead distance in ft
    private Odometry odom;
    private int index = 0;
    private int length = 0;

    public PurePursuit(double wheelBase, Trajectory path) {
        this.wheelBase = wheelBase;
        this.path = path;
        length = path.length();
    }

    public DriveSignal getNextDriveSignal() {
        double left;
        double right;

        int lookIndex = getLookaheadIndex(index);

        Segment current = path.get(index);
        Segment look = path.get(lookIndex);

        double R = getRadius(current.x, current.y, look.x, look.y);
        double W = getAngularVelocity(current.heading, look.heading, index, lookIndex);
        double sign = getSign(current.heading, look.heading);

        //Velocities
        left = W * (R + sign * wheelBase / 2); //  Angluar Velocity * Radius
        right = W * (R - sign * wheelBase / 2);
        //The greater one indicates the direction of the turn

        index++;
        return new DriveSignal(left, right);
    }

    private double getRadius(double x1, double y1, double x2, double y2) {
        double dx = x2 - x1;
        double dy = y2 - y1;
        //Equation derivation in the link above
        return (Math.pow(dx, 2) + Math.pow(dy, 2)) / (2 * dx);
    }

    private double getAngularVelocity(double th1, double th2, int i1, int i2) { // W = Theta/s (radians)
        return (th2 - th1) / (0.02 * (double)(i2 - i1));
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

        //If it's within 3 inches of the lookahead distance, it counts it
        for (int i = index; i < length; i++) {
            d = Math.sqrt(Math.pow(path.get(i).x - path.get(index).x, 2) + Math.pow(path.get(i).y - path.get(index).y, 2));
            dTh = path.get(i).heading - path.get(index).heading;
            //checks if distance is within 0.05ft of the lookahead distance
            //Also just returns current index as the lookahead index if dTheta > 90deg
            if (Math.abs(lookahead -  d) <= 0.05 || dTh > (Math.PI / 2))
                return i;
        }
        //If it doesn't find one, sets the lookahead index to the final segment
        return length - 1;
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