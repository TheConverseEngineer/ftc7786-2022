package org.firstinspires.ftc.teamcode.paths;

import org.firstinspires.ftc.teamcode.utils.Vector2D;

public class Segment extends Path {
    private final double x1, x2, y1, y2;
    private final double xDelta, yDelta;
    private final double length;

    public Segment(double x1, double y1, double x2, double y2) {
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
        this.xDelta = x2 - x1;
        this.yDelta = y2 - y1;
        this.length = Math.sqrt(xDelta*xDelta + yDelta*yDelta);
    }

    @Override
    public Vector2D get(double u) {
        return new Vector2D(x1 + xDelta*u/length, y1 + yDelta*u/length);
    }

    @Override
    public double getLen() {
        return length;
    }

    @Override
    public ClosestPointReport getClosestPoint(Vector2D p) {
        double u = ((p.x-x1)*xDelta + (p.y-y1)*yDelta) / (xDelta*xDelta+yDelta*yDelta);
        if (u < 0) return new ClosestPointReport(0, p.distTo(x1, y1));
        else if (u > 1) return new ClosestPointReport(length, p.distTo(x2, y2));
        else return new ClosestPointReport(u*length, p.distTo(x1 + u*xDelta, y1 + u*yDelta));
    }
}
