package de.fsey.projects.school.pojo;

import java.awt.geom.Point2D;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.List;

/**
 * The params angleInDegrees, startVelocity, and height are pre-filled by the user.
 * The other parameters are calculated while the program executes.
 * The equation of the railCurve is saved in a array with size 3 for params a,b,c
 */
public class ThrowPOJO {

    //input
    private double angleInDegrees;
    private double startVelocity;
    private double height;

    private double[] equationPartsABC = new double[3];
    private double timeOfThrow;
    private double distanceOfThrow;
    private boolean throwIsVertical;

    List<Point2D.Double> pointsOfGraph;
    List<Point2D.Double> pointsOfGraphWithAirFriction;

    public ThrowPOJO() {
    }

    public ThrowPOJO(double angleInDegrees, double startVelocity, double height, double[] equationPartsABC, double timeOfThrow, double distanceOfThrow, boolean throwIsVertical, List<Point2D.Double> pointsOfGraph, List<Point2D.Double> pointsOfGraphWithAirFriction) {
        this.angleInDegrees = angleInDegrees;
        this.startVelocity = startVelocity;
        this.height = height;
        this.equationPartsABC = equationPartsABC;
        this.timeOfThrow = timeOfThrow;
        this.distanceOfThrow = distanceOfThrow;
        this.throwIsVertical = throwIsVertical;
        this.pointsOfGraph = pointsOfGraph;
        this.pointsOfGraphWithAirFriction = pointsOfGraphWithAirFriction;
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

    public boolean isThrowIsVertical() {
        return throwIsVertical;
    }

    public void setThrowIsVertical(boolean throwIsVertical) {
        this.throwIsVertical = throwIsVertical;
    }

    public List<Point2D.Double> getPointsOfGraph() {
        return pointsOfGraph;
    }

    public void setPointsOfGraph(List<Point2D.Double> pointsOfGraph) {
        this.pointsOfGraph = pointsOfGraph;
    }

    public List<Point2D.Double> getPointsOfGraphWithAirFriction() {
        return pointsOfGraphWithAirFriction;
    }

    public void setPointsOfGraphWithAirFriction(List<Point2D.Double> pointsOfGraphWithAirFriction) {
        this.pointsOfGraphWithAirFriction = pointsOfGraphWithAirFriction;
    }

    @Override
    public String toString() {
        return "ThrowPOJO{" +
                "angleInDegrees=" + angleInDegrees +
                ", startVelocity=" + startVelocity +
                ", height=" + height +
                ", equationPartsABC=" + Arrays.toString(equationPartsABC) +
                ", timeOfThrow=" + timeOfThrow +
                ", distanceOfThrow=" + distanceOfThrow +
                '}';
    }
}

