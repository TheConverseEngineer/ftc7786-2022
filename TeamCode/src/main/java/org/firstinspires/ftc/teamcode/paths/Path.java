package org.firstinspires.ftc.teamcode.paths;

import org.firstinspires.ftc.teamcode.profiles.CompositeProfile;
import org.firstinspires.ftc.teamcode.profiles.MotionProfile;
import org.firstinspires.ftc.teamcode.utils.Vector2D;

public abstract class Path {
    /** Get the value at a point in this path
     * @param u     distance along path
     * @return      the value at u
     */
    public abstract Vector2D get(double u);

    /** Get the total length of this path */
    public abstract double getLen();

    /** Get the closest point on this segment to point p */
    public abstract ClosestPointReport getClosestPoint(Vector2D p);

    public CompositeProfile defaultSpeedProfile(double maxSpeed, double accelDist, double staticSpeed) {
        return CompositeProfile.buildContinuousProfile(staticSpeed)
                                    .goTo(maxSpeed, 2*accelDist > this.getLen()? accelDist : this.getLen()/2)
                                    .constantFor(Math.max(0, this.getLen() - 2*accelDist))
                                    .goTo(staticSpeed, 2*accelDist > this.getLen()? accelDist : this.getLen()/2).build();
    }

    public CompositeProfile defaultSpeedProfile(double maxSpeed, double accelDist, double deAccelDist, double staticSpeed) {
        return CompositeProfile.buildContinuousProfile(staticSpeed)
                .goTo(maxSpeed, deAccelDist+accelDist > this.getLen()? accelDist : this.getLen()/2)
                .constantFor(Math.max(0, this.getLen() - deAccelDist - accelDist))
                .goTo(staticSpeed, deAccelDist+accelDist > this.getLen()? deAccelDist : this.getLen()/2).build();
    }

    public static class ClosestPointReport {
        public double disp, dist;

        /** Create a closestPointReport, consisting of a displacement and a distance
         * @param disp    The displacement to get to this point
         * @param dist    The distance to this point */
        public ClosestPointReport(double disp, double dist) {
            this.disp = disp;
            this.dist = dist;
        }
    }
}
