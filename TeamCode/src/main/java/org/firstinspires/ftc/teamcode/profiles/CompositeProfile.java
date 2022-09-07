package org.firstinspires.ftc.teamcode.profiles;

import java.util.ArrayList;
import java.util.List;

/** Allows you to combine multiple motion profiles into a single profile */
public class CompositeProfile extends MotionProfile{

    MotionProfile[] profiles;
    double[] startPos;

    public CompositeProfile(MotionProfile[] profiles) {
        assert profiles.length >= 1; // Please don't make empty profiles!
        this.profiles = profiles;
        startPos = new double[profiles.length+1];
        startPos[0] = 0;
        for (int i = 1; i < startPos.length; i++) startPos[i] = startPos[i-1] + profiles[i-1].getLen();
    }

    @Override
    public double get(double u) {
        for (int i = 1; i < startPos.length; i++) {
            if (startPos[i] > u) return profiles[i-1].get(u - startPos[i-1]);
        }
        return profiles[profiles.length-1].get(startPos[startPos.length-1]);
    }

    @Override
    public double getLen() {
        return startPos[startPos.length-1];
    }

    public static ContinuousProfileBuilder buildContinuousProfile(double startPos) {
        return new ContinuousProfileBuilder(startPos);
    }

    public static class ContinuousProfileBuilder {
        private final List<MotionProfile> profiles;
        private double currentPos;

        public ContinuousProfileBuilder(double startPos) {
            profiles = new ArrayList<>();
            currentPos = startPos;
        }

        public ContinuousProfileBuilder goTo(double position, double duration) {
            profiles.add(new LinearProfile(currentPos, position, duration));
            currentPos = position;
            return this;
        }

        public ContinuousProfileBuilder constantFor(double duration) {
            profiles.add(new ConstantProfile(currentPos, duration));
            return this;
        }

        public CompositeProfile build() {
            return new CompositeProfile(profiles.toArray(new MotionProfile[0]));
        }
    }
}
