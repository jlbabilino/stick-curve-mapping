package io.github.jlbabilino.curvemapping;
/**
 * Main class used for testing - delete eventually
 *
 * @author Justin Babilino
 * @version 0.0.3
 */
public class Main {
    public static void main(String[] args) {
        double[][] testPoints = { {-1.0, -0.2},
                                  {-0.5, -1.0},
                                  { 0.0,  0.3},
                                  { 0.5, -0.8},
                                  { 1.0,  0.9} };
        // double[][] testPoints = { {-0.6, -0.5},
        //                           { 0.0,  0.0},
        //                           { 0.6,  0.5} };
        SplineCurve curve = new SplineCurve();
        curve.setSplineType(SplineType.SMOOTH);
        curve.setPoints(testPoints);
        // LinCurve curve = new LinCurve(0.0, 1.0, 0.05);
        curve.printPoints(101);
        curve.calculateMappedVal(0.4);
    }
}