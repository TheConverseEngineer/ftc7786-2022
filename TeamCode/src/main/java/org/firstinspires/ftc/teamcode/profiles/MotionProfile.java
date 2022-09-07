package org.firstinspires.ftc.teamcode.profiles;

public abstract class MotionProfile {
    /** Get the value of this motion profile at arbitrary value u
     * @param u     arbitrary value (generally distance or time)
     * @return      the value at u
     */
    public abstract double get(double u);

    /** Get the total length of this profile (max value of u) */
    public abstract double getLen();
}
