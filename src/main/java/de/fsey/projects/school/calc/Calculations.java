package de.fsey.projects.school.calc;

import de.fsey.projects.school.pojo.ThrowPOJO;

import java.awt.geom.Point2D;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Calculations {

    private final double GRAVITY_CONSTANT = 9.81;

    public Calculations() {
    }

    public ThrowPOJO determineTypeOfThrow(ThrowPOJO throwPOJO) {

        // senkrechter wurf -- freier fall? -> winkel spielt keine rolle wenn v0 = 0
        if (throwPOJO.getStartVelocity() == 0 || throwPOJO.getAngleInDegrees() == 90 || throwPOJO.getAngleInDegrees() == 270) {
            verticalThrowCalculations(throwPOJO);
        }
        // waagrechter wurf
        else if (throwPOJO.getStartVelocity() > 0 && throwPOJO.getAngleInDegrees() == 0 || throwPOJO.getAngleInDegrees() == 180) {
            horizontalThrowCalculations(throwPOJO);
        }

        //schräger wurf
        else {
            //fix broken input of angles
            if (throwPOJO.getStartVelocity() > 0) {

                if (throwPOJO.getAngleInDegrees() > 90 && throwPOJO.getAngleInDegrees() <= 180)
                    throwPOJO.setAngleInDegrees(throwPOJO.getAngleInDegrees() - 90);
                else if (throwPOJO.getAngleInDegrees() > 180 && throwPOJO.getAngleInDegrees() <= 360)
                    throwPOJO.setAngleInDegrees(throwPOJO.getAngleInDegrees() - 90);
                if (throwPOJO.getAngleInDegrees() > 360)
                    while (throwPOJO.getAngleInDegrees() > 360)
                        throwPOJO.setAngleInDegrees(throwPOJO.getAngleInDegrees() - 360);
                ObliqueThrowCalculations(throwPOJO);
            }
        }

        System.out.println("Calc");
        return throwPOJO;
    }


    public ThrowPOJO horizontalThrowCalculations(ThrowPOJO throwPOJO) {

        double timeOfThrow = Math.sqrt((2 * throwPOJO.getHeight()) / GRAVITY_CONSTANT);
        double distanceOfThrow = throwPOJO.getStartVelocity() * (Math.sqrt((2 * throwPOJO.getHeight()) / GRAVITY_CONSTANT));
        double railCurveGradient = -0.5 * (GRAVITY_CONSTANT / (Math.pow(throwPOJO.getStartVelocity(), 2)));
        double railCurveYAxis = throwPOJO.getHeight();


        throwPOJO.setTimeOfThrow(timeOfThrow);
        throwPOJO.setDistanceOfThrow(distanceOfThrow);
        // contains all parts of quadratic equation
        AbstractMap.SimpleEntry<Double, Double> entry = new AbstractMap.SimpleEntry<>(Map.entry(railCurveGradient, railCurveYAxis));
        throwPOJO.setQuadraticThrowEquations(entry);

        return throwPOJO;

    }

    public ThrowPOJO ObliqueThrowCalculations(ThrowPOJO throwPOJO) {

        return throwPOJO;

    }

    public ThrowPOJO verticalThrowCalculations(ThrowPOJO throwPOJO) {
//        double timeOfThrow = 0;
//        double distanceOfThrow = 0;
//        double railCurveX = 0;
//
//        //freier fall
//        if (throwPOJO.getStartVelocity() == 0) {
//            timeOfThrow = Math.sqrt((2 * throwPOJO.getStartVelocity()) / GRAVITY_CONSTANT);
//            distanceOfThrow = 0.5 * GRAVITY_CONSTANT * Math.pow(timeOfThrow, 2) + throwPOJO.getStartVelocity();
//            railCurveX = distanceOfThrow;
//
//
//        }
//        //wurf nach oben
//        else if (throwPOJO.getStartVelocity() > 0 && throwPOJO.getAngleInDegrees() == 90) {
//            timeOfThrow =  2* ( throwPOJO.getStartVelocity() / GRAVITY_CONSTANT);
//
//
//        }
//        //wurf nach unten
//        else if (throwPOJO.getStartVelocity() > 0 && throwPOJO.getAngleInDegrees() == 270) {
//
//        }
//
//        throwPOJO.setTimeOfThrow(timeOfThrow);
//        throwPOJO.setDistanceOfThrow(distanceOfThrow);
//        throwPOJO.setLinearGraphOfForVerticalThrowsX(railCurveX);
//        throwPOJO.setQuadraticThrowEquations(null);
//
//
        return throwPOJO;
//
//
    }


    /**
     * Method checks if the railCurve is quadratic or linear. Then proceeds to get the quadratic equation from pojo and calculates some points of the graph.
     * Points are then saved into the list of points. In case of a linear graph, only the highest and lowest points are saved into the list.
     *
     * @param quadraticEquation
     * @param linearGraphX
     * @param throwPOJO
     * @return
     */
    public List<Point2D.Double> getPointsOfGraph(AbstractMap.SimpleEntry<Double, Double> quadraticEquation, double linearGraphX, ThrowPOJO throwPOJO) {

        List<Point2D.Double> points = new ArrayList<>();
        double x = 0;
        double y;

        if (quadraticEquation != null) {
            double railCurveGradient = quadraticEquation.getKey();
            double railCurveYAxis = quadraticEquation.getValue();

            do {
                // nur quadratische graphen
                y = railCurveGradient * Math.pow(x, 2) + railCurveYAxis;
                points.add(new Point2D.Double(x, y));
                x++;
            } while (y > 0);

        } else {

            points.add(new Point2D.Double(linearGraphX, throwPOJO.getHeight()));
            points.add(new Point2D.Double(linearGraphX, 0));
        }

        return points;
    }
}
