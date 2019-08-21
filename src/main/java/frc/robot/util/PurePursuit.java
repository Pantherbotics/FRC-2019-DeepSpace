package frc.robot.util;

import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Trajectory.Segment;

/*  3863 implementation of PurePursuit
    https://www.ri.cmu.edu/pub_files/pub3/coulter_r_craig_1992_1/coulter_r_craig_1992_1.pdf
    
*/

public class PurePursuit{ //This is probably the worst thing I [Matthew] have ever written - MS

    private Trajectory path;
    private double wheelBase;
    private int lookahead = 250;
    private Odometry odom;
    private int index = 0;
    private int[] lookaheadArray;
    private double maxDT = Math.PI/32;

    public PurePursuit(double wheelBase, Trajectory path){
        this.wheelBase = wheelBase;
        this.path = path;
        lookaheadArray = new int[path.length()];
        findLookaheads(lookahead);
    }

    public DriveSignal getNextDriveSignal(){
        double left;
        double right;

        Segment current = path.get(index);
        Segment look = path.get(index + lookaheadArray[index]);

        double W = getW();
        double R = 1 / getK(odom.getX(), odom.getY(), look.x, look.y);
        double sign = getDT(index, index + lookaheadArray[index]) / Math.abs(getDT(index, index + lookaheadArray[index]));

        left = W * (R - sign * wheelBase / 2);
        right = W * (R + sign * wheelBase / 2);

        index++;
        return new DriveSignal(left, right);
    }

    private double getDT(int i, int f){
        return path.get(f).heading - path.get(i).heading;
    }

    private double getW(){ //Angular Velocity, Theta in radians btw
        return Math.abs(getDT(index, index + lookaheadArray[index])) / path.get(index).dt;
    }

    private double getK(double x1, double y1, double x2, double y2){ //Curvature
        double dx = x2 - x1;
        double dy = y2 - y1;
        return (2 * dx)/(Math.pow(dx, 2) + Math.pow(dy, 2)); //2x/(x^2 +y^2)
    }

    private void findLookaheads(int maxLookahead){ //Estimate a maximum possible lookahead, work downwards
        for(int i = 0; i < lookaheadArray.length; i++){
            lookaheadArray[i] = findLookahead(path.get(i).x, path.get(i).y, i, maxLookahead);
        }
    }

    private int findLookahead(double x, double y, int currentIndex, int currentLookahead){ //If a radius of a
        double K = getK(x, y, x + currentLookahead, y + currentLookahead);
        double dt = getDT(currentIndex, currentIndex + currentLookahead);
        if(Math.abs(dt) > maxDT){
            return findLookahead(x, y, currentIndex, currentLookahead - 1);
        } else{
            return currentLookahead;
        }
    }

    public void setOdom(Odometry odo){
        odom = odo;
    }

    public Odometry getInitOdom(){
        return new Odometry(path.get(0).x, path.get(0).y, path.get(0).heading);
    }

    public boolean isFinished(){
        return index == path.length();
    }
}