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

        //methode der kleinen schritte

        double angleInDegrees = throwPOJO.getAngleInDegrees();
        double currentHeight = throwPOJO.getHeight();
        double v = throwPOJO.getStartVelocity();


        double vX = v * Math.cos(Math.toRadians(angleInDegrees));
        double vY = v * Math.sin(Math.toRadians(angleInDegrees));



        List<Point2D.Double> pointsOfGraphWithAirFriction = new ArrayList<>();

        double time = 0;
        double x = 0;
        double y = currentHeight;

        /** change mass here to get different comparison values: for example to compare with
         * https://phet.colorado.edu/sims/html/projectile-motion/latest/projectile-motion_de.html */
        double mass = 17.0;
        double C = 0.1;
        double deltaT = 0.01;


        while (y >= 0) {

           v = Math.sqrt( vX*vX + vY*vY);
           double Fl = C * Math.pow(v,2);

           double Flx = - vX * ( Fl / v );
           double Fly = - vY * ( Fl / v );

           double Fx = + Flx;
           double Fy = - mass * GRAVITY_CONSTANT + Fly;

           double aX = Fx / mass;
           double aY = Fy / mass;

           vX = vX + aX * deltaT;
           vY = vY + aY * deltaT;

           x = x + vX * deltaT;
           y = y + vY * deltaT;
           time = time + deltaT;






            Point2D.Double point = new Point2D.Double(x,y);
            pointsOfGraphWithAirFriction.add(point);


            System.out.println("aX: " + aX + "\t" + " aY: " +  aY + "\t" + " vX: " + vX + "\t" + " vY: " + vY + "\t x: " + x + "\t y: " + y);
        }

        pointsOfGraphWithAirFriction.forEach(System.out::println);
        throwPOJO.setPointsOfGraphWithAirFriction(pointsOfGraphWithAirFriction);
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
