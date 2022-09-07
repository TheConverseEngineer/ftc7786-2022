package org.firstinspires.ftc.teamcode.utils;

/** A dataclass consisting of an x, y, and a theta.
 *  Intended for representing robot positions
 */
public class Pose2D {

    public double x, y, theta;

    public Pose2D(double x, double y, double theta) {
        this.x = x;
        this.y = y;
        this.theta = theta;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Pose2D)) return false;
        Pose2D other = (Pose2D) o;
        return Math.abs(other.x - this.x) < 0.0001 && Math.abs(other.x - this.x) < 0.0001 && Math.abs(other.theta - this.theta) < 0.0001;
    }

    @Override
    public String toString() {
        return "("+x+", "+y+", "+theta+")";
    }
}
