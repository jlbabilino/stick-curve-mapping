package io.github.jlbabilino.curvemapping;
/**
 * This class and its subclasses remap controller stick inputs
 * according to specifics provided of multiple classes of curves.
 * 
 * This is the superclass of several types of curves. It contains
 * most of the code that is used for all types of curves (Linear,
 * Exponential, Spline, and Step).
 * 
 * To create a Curve, do not create a <code>Curve</code> object directly,
 * use a subclass of <code>Curve</code> such as <code>ExpCurve</code>
 * instead.
 * 
 * @version 2021-01-30
 */
public class Curve {
    /** fields */
    /** The value added to the curve. */
    private double offset;
    /** The value multiplied to the curve. */
    private double scalar;
    /** The width of the deadband on the curve. */
    private double deadzone;
    /** methods **/
    /**
     * Returns the value of <code>input</code> mapped to create
     * a deadband of width <code>deadzone</code> in the center of the 
     * curve and squishes the rest of the curve to the outside 
     * proportionally.
     * 
     * @param input the input value to be mapped
     * @return mapped value
     */
    protected double calculateDeadzone(double input) {
        double deadRadius = deadzone / 2.0;
        double val = 0.0;
        if (input > deadRadius) {
            val = (1.0 / (1.0 - deadRadius)) * (input - deadRadius);
        } else if (input < -deadRadius) {
            val = (1.0 / (1.0 - deadRadius)) * (input + deadRadius);
        }
        return val;
    }
    /**
     * Returns the value of <code>input</code> multiplied by the
     * value of <code>scalar</code>.
     * 
     * @param input the input value to be mapped
     * @return mapped value
     */
    protected double calculateScalar(double input) {
        double val = input * scalar;
        return val;
    }
    /**
     * Returns the value of <code>input</code> summed with the
     * value of <code>offset</code>.
     * 
     * @param  input the input value to be mapped
     * @return mapped value
     */
    protected double calculateOffset(double input) {
        double val = input + offset;
        return val;
    }
    /**
     * Prints the values of a 2D double array of points. The output of
     * this method can be pasted into https://www.desmos.com/calculator
     * to see a visual representation of a <code>Curve</code>.
     * 
     * @param  points the set of points to be printed
     */
    public void printPoints(double[][] points) {
        System.out.println();
        for (double[] point : points) {
            System.out.print("(" + point[0] + "," + point[1] + ")" + ",");
        }
    }
    /** mutator methods **/
    /**
     * Sets the value of <code>offset</code>, the
     * value added to the final curve.
     * 
     * @param offset the new value of <code>offset</code>
     */
    public void setOffset(double offset) {
        this.offset = offset;
    }
    /**
     * Sets the value of <code>scalar</code>, the
     * value multiplied to the curve before it is offset.
     * 
     * @param scalar the new value of <code>scalar</code>
     */
    public void setScalar(double scalar) {
        this.scalar = scalar;
    }
    /**
     * Sets the value of <code>deadzone</code>, the
     * value for the width in the center of the curve
     * where any input results in an output of <code>0.0</code>.
     * 
     * @param deadzone the new value of <code>deadzone</code>
     */
    public void setDeadzone(double deadzone) {
        this.deadzone = Math.abs(deadzone);
    }
    /** accessor methods **/
    /** 
     * Returns the value of <code>offset</code>, the double
     * value added to the final curve.
     * 
     * @return the current value of <code>offset</code>
     */
    public double getOffset() {
        return offset;
    }
    /** 
     * Returns the value of <code>scalar</code>, the double
     * value multiplied to the curve before it is offset.
     * 
     * @return the current value of <code>scalar</code>
     */
    public double getScalar() {
        return scalar;
    }
    /**
     * Returns the value of <code>deadzone</code>, the
     * value for the width in the center of the curve
     * where any input results in an output of <code>0.0</code>
     * 
     * @return the current value of <code>deadzone</code>
     */
    public double getDeadzone() {
        return deadzone;
    }
}