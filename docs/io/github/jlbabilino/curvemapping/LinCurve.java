package io.github.jlbabilino.curvemapping;
/**
 * This class maps the value of a stick input to an linear curve.
 * It is a subclass of <code>Curve</code>.
 *
 * @author jlbabilino
 * @version 2021-01-30
 */
public class LinCurve extends Curve {
    /** fields */
    /** constructors */
    /**
     * Constructs an Linear Curve object which can
     * be used to map a stick input proportionally.
     * Initialized with values provided.
     * 
     * @param offset value used to offset the final curve
     * @param scalar value used to scale the value before offset
     * @param deadzone value for the width of the deadband in the center of the curve
     */
    public LinCurve(double offset, double scalar, double deadzone) {
        setOffset(offset);
        setScalar(scalar);
        setDeadzone(deadzone);
    }
    /**
     * Constructs an Linear Curve object which can
     * be used to map a stick input proportionally.
     * Initialized with default values:
     * <code>
     *     offset = 0.0;
     *     scalar = 1.0;
     *     deadzone = 0.0;
     * </code>
     */
    public LinCurve() {
        setOffset(0.0);
        setScalar(1.0);
        setDeadzone(0.0);
    }
    /** methods */
    /**
     * Calculates and returns the final value mapped based on the curve
     * provided.
     * 
     * @param  input  value to be mapped
     * @return mapped value
     */
    public double calculateMappedVal(double input) {
        double val = calculateOffset(calculateScalar(calculateDeadzone(input)));
        if (val > 1.0) {
            val = 1.0;
        } else if (val < -1.0) {
            val = -1.0;
        }
        return val;
    }
    /**
     * Returns a set of points of length <code>pointCount</code> on the curve.
     * 
     * @param  pointCount the amount of points on the curve
     * @return a 2D double array of points on the curve
     */
    public double[][] getCurvePoints(int pointCount) {
        double[][] points = new double[pointCount][2];
        double dx = 2.0 / (pointCount - 1);
        for (int i = 0; i < pointCount; i++) {
            double x = -1.0 + (i * dx);
            points[i][0] = x;
            points[i][1] = calculateMappedVal(x);
        }
        return points;
    }
    /** mutator methods */
    /** accessor methods */
}