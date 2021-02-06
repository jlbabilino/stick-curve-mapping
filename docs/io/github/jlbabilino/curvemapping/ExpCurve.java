package io.github.jlbabilino.curvemapping;
/**
 * This class maps the value of a stick input to an exponential curve.
 * It is a subclass of <code>Curve</code>.
 *
 * @version 2021-01-30
 */
public class ExpCurve extends Curve {
    /** fields */
    /** The value of the base of the exponent used in calculating the curve. */
    private double expVal;
    /** constructors */
    /**
     * Constructs an Exponential Curve object which can
     * be used to map a stick input exponentially. Initialized
     * with default values:
     * <code>
     *     expVal = 1.0;
     *     offset = 0.0;
     *     scalar = 1.0;
     *     deadzone = 0.0;
     * </code>
     */
    public ExpCurve() {
        setExpVal(1.0);
        setOffset(0.0);
        setScalar(1.0);
        setDeadzone(0.0);
    }
    /**
     * Constructs an Exponential Curve object which can
     * be used to map a stick input exponentially. Initialized
     * with values provided.
     * 
     * @param expVal value of the base of the exponent used in the curve
     * @param offset value used to offset the final curve
     * @param scalar value used to scale the value before offset
     * @param deadzone value for the width of the deadband in the center of the curve
     */
    public ExpCurve(double expVal, double offset, double scalar, double deadzone) {
        setExpVal(expVal);
        setOffset(offset);
        setScalar(scalar);
        setDeadzone(deadzone);
    }
    /** methods */
    /**
     * Returns the value of <code>input</code> mapped value based on the curve.
     * 
     * @param  input value to be mapped
     * @return mapped value
     */
    public double calculateMappedVal(double input) {
        double val = calculateOffset(calculateScalar(calculateExpVal(calculateDeadzone(input))));
        if (val > 1.0) {
            val = 1.0;
        } else if (val < -1.0) {
            val = -1.0;
        }
        return val;
    }
    /**
     * Returns the value of the input mapped by
     * an exponential curve of base <code>expVal</code>.
     * 
     * @param  input the input value to be mapped
     * @return mapped value
     */
    private double calculateExpVal(double input) {
        double val = input;
        if (expVal != 1.0) {
            val = (Math.pow(expVal, Math.abs(input)) - 1.0) / (expVal - 1.0) * Math.signum(input);
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
    /**
     * Sets the value of <code>expVal</code>, the base of the exponent
     * used to map the input.
     * 
     * @param expVal the new value of <code>expVal</code>
     */
    public void setExpVal(double expVal) {
        if (expVal <= 0.0) {
            expVal = 1.0;
        }
        this.expVal = expVal;
    }
    /** accessor methods */
    /**
     * Returns the value of <code>expVal</code>, the base of the exponent
     * used to map the input.
     * 
     * @return the current value of <code>expVal</code>
     */
    public double getExpVal() {
        return expVal;
    }
}