package org.firstinspires.ftc.teamcode.utils;

import org.firstinspires.ftc.teamcode.utils.generics.Cartesian;
import org.firstinspires.ftc.teamcode.utils.generics.Polar;
import org.firstinspires.ftc.teamcode.utils.generics.Unit;
import org.firstinspires.ftc.teamcode.utils.generics.Vector2DType;

/** Simple data class that represents a 2 dimensional vector */
@SuppressWarnings("unused")
public class Vector2D <T extends Vector2DType> {
    public final double x, y;
    private static final double EPSILON = 0.00001;

    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /** Adds two vectors together.
     * @param a     the first vector to add
     * @param b     the second vector to add
     * @return      the resultant sum
     */
    public static Vector2D<Cartesian> add(Vector2D<Cartesian> a, Vector2D<Cartesian> b) {
        return new Vector2D<>(a.x + b.x, a.y + b.y);
    }

    /** Subtracts two vectors.
     * @param a     the first vector to subtract
     * @param b     the second vector to subtract
     * @return      the resultant difference
     */
    public static Vector2D<Cartesian> subtract(Vector2D<Cartesian> a, Vector2D<Cartesian> b) {
        return new Vector2D<>(a.x - b.x, a.y - b.y);
    }

    /** Normalizes a cartesian vector into a unit vector
     * @param a     the vector to normalize
     * @return      the normalized vector
     */
    public static Vector2D<Unit> normalizeCartesian(Vector2D<Cartesian> a) {
        double size = Math.sqrt(a.x*a.x + a.y*a.y);
        return new Vector2D<>(a.x/size, a.y/size);
    }

    /** Normalizes a polar vector into a unit vector
     * @param a     the vector to normalize
     * @return      the normalized vector
     */
    public static Vector2D<Unit> normalizePolar(Vector2D<Polar> a) {
        return new Vector2D<>(Math.cos(a.y), Math.sin(a.y));
    }

    /** Scales a unit vector into a cartesian vector
     * @param a         the unit vector
     * @param scalar    the scalar value
     * @return          the resultant vector
     */
    public static Vector2D<Cartesian> scale(Vector2D<Unit> a, double scalar) {
        return new Vector2D<>(a.x * scalar, a.y * scalar);
    }

    /** Converts a polar vector into a cartesian one
     * @param a     the vector to convert
     * @return      the converted vector
     */
    public static Vector2D<Cartesian> toCartesian(Vector2D<Polar> a) {
        return new Vector2D<>(a.x*Math.cos(a.y), a.x*Math.sin(a.y));
    }

    /** Converts a cartesian vector into a polar one
     * @param a     the vector to convert
     * @return      the converted vector
     */
    public static Vector2D<Polar> toPolar(Vector2D<Cartesian> a) {
        return new Vector2D<>(Math.hypot(a.x, a.y), Math.atan2(a.y, a.x));
    }

    /** Compare two vectors, using the defined epsilon value
     * @param a     the first vector to compare
     * @param b     the second vector to compare
     * @return      true if the vector's difference is within epsilon */
    public static <m_T extends Vector2DType> boolean equal(Vector2D<m_T> a, Vector2D<m_T> b) {
        return Math.abs(b.x - a.x) < EPSILON && Math.abs(b.y - a.y) < EPSILON;
    }

    @Override
    public String toString() {
        return "(" + this.x + ", " + this.y + ")";
    }
}
