package de.fsey.projects.school.pojo;

import java.util.AbstractMap;

/**
 * The params angleInDegrees, startVelocity, and height are pre-filled by the user.
 * The other parameters are calculated while the program executes.
 * The equation of the railCurve is saved in two different ways: if quadratic, the gradient is the key of a Map, the YAxis is the value.
 *                                                               if linear, the XAxis is saved into a parameter
 */
public class ThrowPOJO {

    //input
    private double angleInDegrees;
    private double startVelocity;
    private double height;

    private AbstractMap.SimpleEntry<Double , Double> quadraticThrowEquations;

    private double linearGraphOfForVerticalThrowsX;
    private double timeOfThrow;
    private double distanceOfThrow;


    public ThrowPOJO() {
    }

    public ThrowPOJO(double angleInDegrees, double startVelocity, double height, AbstractMap.SimpleEntry<Double, Double> quadraticThrowEquations, double linearGraphOfForVerticalThrowsX, double timeOfThrow, double distanceOfThrow) {
        this.angleInDegrees = angleInDegrees;
        this.startVelocity = startVelocity;
        this.height = height;
        this.quadraticThrowEquations = quadraticThrowEquations;
        this.linearGraphOfForVerticalThrowsX = linearGraphOfForVerticalThrowsX;
        this.timeOfThrow = timeOfThrow;
        this.distanceOfThrow = distanceOfThrow;
    }

    public ThrowPOJO(double angleInDegrees, double startVelocity, double height) {
        this.angleInDegrees = angleInDegrees;
        this.startVelocity = startVelocity;
        this.height = height;
    }

    public double getAngleInDegrees() {
        return angleInDegrees;
    }
    public void setAngleInDegrees(double angleInDegrees) {
        this.angleInDegrees = angleInDegrees;
    }

    public double getStartVelocity() {
        return startVelocity;
    }
    public void setStartVelocity(double startVelocity) {
        this.startVelocity = startVelocity;
    }

    public double getHeight() {
        return height;
    }
    public void setHeight(double height) {
        this.height = height;
    }

    public double getTimeOfThrow() {
        return timeOfThrow;
    }
    public void setTimeOfThrow(double timeOfThrow) {
        this.timeOfThrow = timeOfThrow;
    }

    public double getDistanceOfThrow() {
        return distanceOfThrow;
    }
    public void setDistanceOfThrow(double distanceOfThrow) {
        this.distanceOfThrow = distanceOfThrow;
    }

    public AbstractMap.SimpleEntry<Double, Double> getQuadraticThrowEquations() {
        return quadraticThrowEquations;
    }
    public void setQuadraticThrowEquations(AbstractMap.SimpleEntry<Double, Double> quadraticThrowEquations) {
        this.quadraticThrowEquations = quadraticThrowEquations;
    }

    public double getLinearGraphOfForVerticalThrowsX() {
        return linearGraphOfForVerticalThrowsX;
    }
    public void setLinearGraphOfForVerticalThrowsX(double linearGraphOfForVerticalThrowsX) {
        this.linearGraphOfForVerticalThrowsX = linearGraphOfForVerticalThrowsX;
    }
}
