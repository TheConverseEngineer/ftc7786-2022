package org.firstinspires.ftc.teamcode.profiles;

public class ConstantProfile extends MotionProfile{

    double value, len;

    /** Create a new constant motion profile
     * @param value     the value to return
     * @param len       the length of this profile
     */
    public ConstantProfile(double value, double len) {
        this.value = value;
        this.len = len;
    }

    @Override
    public double get(double u) {
        return value;
    }

    @Override
    public double getLen() {
        return len;
    }
}
