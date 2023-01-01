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

    private double[] equationPartsABC = new double[3];
    private double timeOfThrow;
    private double distanceOfThrow;


    public ThrowPOJO() {
    }

    public ThrowPOJO(double angleInDegrees, double startVelocity, double height, double[] equationPartsABC, double timeOfThrow, double distanceOfThrow) {
        this.angleInDegrees = angleInDegrees;
        this.startVelocity = startVelocity;
        this.height = height;
        this.equationPartsABC = equationPartsABC;
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


    public double[] getEquationPartsABC() {
        return equationPartsABC;
    }
    public void setEquationPartsABC(double[] equationPartsABC) {
        this.equationPartsABC = equationPartsABC;
    }
}

