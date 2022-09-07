package org.firstinspires.ftc.teamcode.paths;

import com.acmerobotics.dashboard.canvas.Canvas;

import org.checkerframework.checker.units.qual.C;
import org.firstinspires.ftc.teamcode.command.Command;
import org.firstinspires.ftc.teamcode.command.prefabs.InstantCommand;
import org.firstinspires.ftc.teamcode.utils.Vector2D;

import java.util.ArrayList;
import java.util.List;

public class CompositePath extends Path {

    Path[] paths;
    double[] startPos;

    public CompositePath(Path[] paths) {
        assert paths.length >= 1; // Please don't make empty paths!
        this.paths = paths;
        startPos = new double[this.paths.length+1];
        startPos[0] = 0;
        for (int i = 1; i < startPos.length; i++) startPos[i] = startPos[i-1] + paths[i-1].getLen();
        for (int i = 1; i < paths.length; i++) {
            if (!paths[i-1].get(paths[i-1].getLen()).equals(paths[i].get(0))) {
                throw new RuntimeException("Composite Path Exception: Path was not continuous:" + paths[i-1].get(paths[i-1].getLen()) + paths[i].get(0));
            }
        }
    }

    @Override
    public Vector2D get(double u) {
        for (int i = 1; i < startPos.length; i++) {
            if (startPos[i] > u) return paths[i-1].get(u - startPos[i-1]);
        }
        return Vector2D.zeroVector();
    }

    @Override
    public double getLen() {
        return startPos[startPos.length-1];
    }

    @Override
    public ClosestPointReport getClosestPoint(Vector2D p) {
        ClosestPointReport returnValue = new ClosestPointReport(0, p.distTo(paths[0].get(0)));
        for (int i = 0; i < paths.length; i++) {
            ClosestPointReport thisClosest = paths[i].getClosestPoint(p);
            if (thisClosest.dist <= returnValue.dist) {
                returnValue.disp = thisClosest.disp + startPos[i];
                returnValue.dist = thisClosest.dist;
            }
        }
        return returnValue;
    }

    /** Convenience function to draw this path on a FTC-Dashboard canvas */
    public void draw(Canvas canvas) {
        canvas.setFill("blue");
        double[] xPoints = new double[paths.length+1], yPoints = new double[paths.length+1];
        xPoints[0] = paths[0].get(0).x;
        yPoints[0] = paths[0].get(0).y;
        for (int i = 1; i <= paths.length; i++) {
            xPoints[i] = paths[i-1].get(paths[i-1].getLen()).x;
            yPoints[i] = paths[i-1].get(paths[i-1].getLen()).y;
        }
        canvas.strokePolyline(xPoints, yPoints);
    }

    public static PathBuilder buildPath(double x, double y) {
        return new PathBuilder(new Vector2D(x, y));
    }

    public static class PathBuilder {
        private final List<Path> paths;
        private final Vector2D currentPos;

        public PathBuilder(Vector2D startPos) {
            paths = new ArrayList<>();
            currentPos = startPos;
        }

        public PathBuilder to(double x, double y) {
            paths.add(new Segment(currentPos.x, currentPos.y, x, y));
            currentPos.set(x, y);
            return this;
        }

        public CompositePath build() {
            return new CompositePath(paths.toArray(new Path[0]));
        }
    }
}
