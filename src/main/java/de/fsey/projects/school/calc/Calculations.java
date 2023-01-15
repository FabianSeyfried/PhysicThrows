package de.fsey.projects.school.calc;

import de.fsey.projects.school.pojo.ThrowPOJO;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class Calculations {

    private final double GRAVITY_CONSTANT = 9.81;

    public Calculations() {
    }

    public ThrowPOJO determineTypeOfThrow(ThrowPOJO throwPOJO) {

        // senkrechter wurf -- freier fall? -> winkel spielt keine rolle wenn v0 = 0
        if (throwPOJO.getStartVelocity() == 0 || throwPOJO.getAngleInDegrees() == 90 || throwPOJO.getAngleInDegrees() == 270) {
            throwPOJO = verticalThrowCalculations(throwPOJO);
        }
        // waagrechter wurf
        else if (throwPOJO.getStartVelocity() > 0 && throwPOJO.getAngleInDegrees() == 0 || throwPOJO.getAngleInDegrees() == 180) {
            throwPOJO = horizontalThrowCalculations(throwPOJO);
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
                throwPOJO = ObliqueThrowCalculations(throwPOJO);
            }
        }

        System.out.println("Calc");
        return throwPOJO;
    }


    public ThrowPOJO horizontalThrowCalculations(ThrowPOJO throwPOJO) {
        System.out.println("horizontal");

        double timeOfThrow = Math.sqrt((2 * throwPOJO.getHeight()) / GRAVITY_CONSTANT);
        double distanceOfThrow = throwPOJO.getStartVelocity() * (Math.sqrt((2 * throwPOJO.getHeight()) / GRAVITY_CONSTANT));

        double railCurveEquationA = -0.5 * (GRAVITY_CONSTANT / (Math.pow(throwPOJO.getStartVelocity(), 2)));
        double railCurveEquationC = throwPOJO.getHeight();


        throwPOJO.setTimeOfThrow(timeOfThrow);
        throwPOJO.setDistanceOfThrow(distanceOfThrow);
        throwPOJO.setEquationPartsABC(new double[]{railCurveEquationA, 0.0, railCurveEquationC});
        throwPOJO.setThrowIsVertical(false);

        return throwPOJO;

    }

    public ThrowPOJO ObliqueThrowCalculations(ThrowPOJO throwPOJO) {
        System.out.println("oblique");
        double angleInRadians = Math.toRadians(throwPOJO.getAngleInDegrees());

        double timeOfThrow = ((throwPOJO.getStartVelocity() * Math.sin(angleInRadians)) / GRAVITY_CONSTANT) +
                ((Math.sqrt(Math.pow(throwPOJO.getStartVelocity() * Math.sin(angleInRadians), 2) + 2 * GRAVITY_CONSTANT * throwPOJO.getHeight())) / GRAVITY_CONSTANT);

        double distanceOfThrow = (throwPOJO.getStartVelocity() * Math.cos(angleInRadians)) * timeOfThrow;

        double railCurveEquationA = -0.5 * GRAVITY_CONSTANT / Math.pow(throwPOJO.getStartVelocity() * Math.cos(angleInRadians), 2);
        double railCurveEquationB = Math.ceil(Math.tan(angleInRadians));
        double railCurveEquationC = throwPOJO.getHeight();


        throwPOJO.setTimeOfThrow(timeOfThrow);
        throwPOJO.setDistanceOfThrow(distanceOfThrow);
        throwPOJO.setEquationPartsABC(new double[]{railCurveEquationA, railCurveEquationB, railCurveEquationC});
        throwPOJO.setThrowIsVertical(false);

        System.out.println(throwPOJO.toString());
        return throwPOJO;

    }

    public ThrowPOJO verticalThrowCalculations(ThrowPOJO throwPOJO) {
        System.out.println("vertical");
        //TODO: program behavior here
        double timeOfThrow = 0;
        //distance here means the elevation gained in a throw upwards. is 0 if free fall or throw upside down
        double distanceOfThrow = 0;

        //freier fall
        if (throwPOJO.getStartVelocity() == 0) {
            timeOfThrow = Math.sqrt((2 * throwPOJO.getHeight()) / GRAVITY_CONSTANT);
            distanceOfThrow = 0;


        }
        //wurf nach oben
        else if (throwPOJO.getStartVelocity() > 0 && throwPOJO.getAngleInDegrees() == 90) {

            double timeUp = throwPOJO.getStartVelocity() / GRAVITY_CONSTANT;
            distanceOfThrow = (Math.pow(throwPOJO.getStartVelocity(), 2)) / (2 * GRAVITY_CONSTANT);
            timeOfThrow = timeUp + (Math.sqrt(2 * (throwPOJO.getHeight() + distanceOfThrow) / GRAVITY_CONSTANT));


        }
        //wurf nach unten
        else if (throwPOJO.getStartVelocity() > 0 && throwPOJO.getAngleInDegrees() == 270) {
            distanceOfThrow = 0;
            timeOfThrow = (-throwPOJO.getStartVelocity() + (Math.sqrt((Math.pow(throwPOJO.getStartVelocity(), 2)) + 2 * GRAVITY_CONSTANT * throwPOJO.getHeight()))) / GRAVITY_CONSTANT;

        }


        throwPOJO.setTimeOfThrow(timeOfThrow);
        throwPOJO.setDistanceOfThrow(distanceOfThrow);
        throwPOJO.setEquationPartsABC(new double[]{0, 0, 0});
        throwPOJO.setThrowIsVertical(true);

        return throwPOJO;


    }

    public ThrowPOJO calcAirFriction(ThrowPOJO throwPOJO) {

        //calculate the velocity for x and y and get a total value from both
        List<Double> totalVelocities = new ArrayList<>();
        double Vx = throwPOJO.getStartVelocity() * Math.cos(Math.toRadians(throwPOJO.getAngleInDegrees()));

        for (double i = 0; i < throwPOJO.getTimeOfThrow(); i = i + 0.25) {
            double Vy = - (GRAVITY_CONSTANT * i )  + ( throwPOJO.getStartVelocity() * Math.sin(Math.toRadians(throwPOJO.getAngleInDegrees())) );
            double totalVelocity = Math.sqrt( Math.pow(Vx, 2 ) + Math.pow(Vy, 2));
            totalVelocities.add(totalVelocity);
        }



        // golf ball
        double mass = 0.040; // kg
        double forceInNewton = mass * GRAVITY_CONSTANT;
        double radiusCM = 2; // cm
        double surfaceOfBallInMeters = (Math.pow(radiusCM, 2) * Math.PI) / 100; //cm^2
        double airFlowResistance = 0.45;  // Cw
        double density = 1.225; // kg / m^3


        //calculate the air friction with the values of the total velocity
        double airFrictionInNewton = 0;
        for (int i = 0; i < totalVelocities.size(); i++){
            airFrictionInNewton = 0.5 * surfaceOfBallInMeters * airFlowResistance * density * Math.pow(totalVelocities.get(i), 2);
            System.out.println(airFrictionInNewton);
            //TODO: save air friction values into list and do sth. with it
        }


        System.out.println("Surface: " + surfaceOfBallInMeters);
        System.out.println("Force in Newton: " + forceInNewton);
        System.out.println("Air friction: " + airFrictionInNewton);



        // GOAL: get new (only one) value for g, then calculate the throw again to get a throw with air friction
        // F = m x g => g = F /m
        return throwPOJO;
    }


    /**
     * Method extracts parts of equation and determines the points of the graph by setting x into the equation until the y-values are 0.
     *
     * @param throwPOJO
     * @return
     */
    public List<Point2D.Double> getPointsOfGraph(ThrowPOJO throwPOJO) {
        List<Point2D.Double> points = new ArrayList<>();
        double x = 0;
        double y;

        if (throwPOJO.getEquationPartsABC() != null && !throwPOJO.isThrowIsVertical()) {
            double a = throwPOJO.getEquationPartsABC()[0];
            double b = throwPOJO.getEquationPartsABC()[1];
            double c = throwPOJO.getEquationPartsABC()[2];

            do {
                // nur quadratische graphen
                y = a * Math.pow(x, 2) + b * x + c;
                points.add(new Point2D.Double(x, y));
                x = x + 0.25;
            } while (y > 0);
        } else if (throwPOJO.isThrowIsVertical()) {
            return getPointsOfGraphIfVerticalThrow(throwPOJO);
        }

        return points;
    }

    public List<Point2D.Double> getPointsOfGraphIfVerticalThrow(ThrowPOJO throwPOJO) {
        List<Point2D.Double> points = new ArrayList<>();

        double highestPoint = throwPOJO.getHeight() + throwPOJO.getDistanceOfThrow();
        double startPoint = throwPOJO.getHeight();

        if (startPoint == highestPoint) {
            for (double i = startPoint; i >= 0; i--) {
                points.add(new Point2D.Double(1, i));
            }
        } else if (startPoint != highestPoint) {
            double increment = startPoint;

            highestPoint = Math.ceil(highestPoint);
            while (increment < highestPoint) {
                increment++;
                points.add(new Point2D.Double(1, increment));
            }
            if (increment == highestPoint) {
                while (increment >= 0) {
                    points.add(new Point2D.Double(1, increment));
                    increment--;
                }
            }
        }
        return points;
    }

}
