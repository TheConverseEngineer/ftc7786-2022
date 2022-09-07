package org.firstinspires.ftc.teamcode.profiles;

public class LinearProfile extends MotionProfile {

    double start, slope, len;

    /** Create a new constant motion profile
     * @param start     the value at which this profile starts
     * @param end       the value at which this profile ends
     * @param len       the length of this profile
     */
    public LinearProfile(double start, double end, double len) {
        this.slope = (end-start)/len;
        this.start = start;
        this.len = len;
    }

    @Override
    public double get(double u) {
        return u<0 ? start : (u<len ? slope * u + start : slope * len + start);
    }

    @Override
    public double getLen() {
        return len;
    }
}
