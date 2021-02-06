package io.github.jlbabilino.curvemapping;
/**
 * Main class used for testing - delete eventually
 *
 * @version 2021-01-31
 */
public class Main {
    public static void main(String[] args) {
        double[][] testPoints = { {-1.0,  1.0},
                                  {-0.5, -1.0},
                                  { 0.0,  1.0},
                                  { 0.5, -1.0},
                                  { 1.0,  1.0} };
        // double[][] testPoints = { {-0.6, -0.5},
        //                           { 0.0,  0.0},
        //                           { 0.6,  0.5} };
        SplineCurve curve = new SplineCurve();
        curve.setPoints(testPoints);
        // LinCurve curve = new LinCurve(0.0, 1.0, 0.05);
        curve.printPoints(curve.getCurvePoints(101));
    }
}