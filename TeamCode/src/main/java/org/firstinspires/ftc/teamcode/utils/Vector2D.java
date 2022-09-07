package org.firstinspires.ftc.teamcode.utils;

/** Simple data class that represents an X and a Y coordinate
 *
 */
public class Vector2D {
    public double x, y;

    private static final double EPSILON = 0.00001;

    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public static Vector2D zeroVector() {
        return new Vector2D(0, 0);
    }

    public void set(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Vector2D)) return false;
        Vector2D other = (Vector2D) o;
        return Math.abs(other.x - this.x) < EPSILON && Math.abs(other.y - this.y) < EPSILON;
    }

    public double distTo(double x, double y) {
        return Math.sqrt((this.x-x)*(this.x-x) + (this.y-y)*(this.y-y));
    }

    public double distTo(Vector2D other) {
        return Math.sqrt((this.x-other.x)*(this.x-other.x) + (this.y-other.y)*(this.y-other.y));
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    /** Adds two vectors together to form a new vector */
    public static Vector2D add(Vector2D a, Vector2D b) {
        return new Vector2D(a.x + b.x, a.y + b.y);
    }

    /** Subtracts two vectors together to form a new vector */
    public static Vector2D subtract(Vector2D a, Vector2D b) {
        return new Vector2D(a.x - b.x, a.y - b.y);
    }

    /** Scales a vectors by a double to form a new vector */
    public static Vector2D scale(Vector2D a, double scalar) {
        return new Vector2D(a.x * scalar, a.y * scalar);
    }

    /** Increments this vector object by another vector */
    public void add(Vector2D other) {
        this.x += other.x;
        this.y += other.y;
    }

    /** Decrements this vector object by another vector */
    public void subtract(Vector2D other) {
        this.x -= other.x;
        this.y -= other.y;
    }

    /** Scales this vector object by a double */
    public void scale(double scalar) {
        this.x *= scalar;
        this.y *= scalar;
    }
}
