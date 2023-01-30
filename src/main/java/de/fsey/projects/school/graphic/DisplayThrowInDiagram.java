package de.fsey.projects.school.graphic;

import de.fsey.projects.school.pojo.ThrowPOJO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.text.DecimalFormat;
import java.util.List;

/**
 * Class is used to create a diagram from all before calculated parameters.
 */
public class DisplayThrowInDiagram extends JPanel {

    private List<Point2D.Double> pointsOfGraph;
    private List<Point2D.Double> pointsOfGraphWithAirFriction;
    private ThrowPOJO throwPOJO;

    public DisplayThrowInDiagram() {
    }


    /**
     * Method creates the diagram to show the curve of a physical throw.
     * Draws x-, y-Axis, and their labels and points of the graph.
     * Also a short description of calculated parameters about the throw.
     * @param g responsible to paint the coordinate system, graph, etc.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g1 = (Graphics2D) g;
        g1.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int width = getWidth();
        int height = getHeight();
        DecimalFormat df = new DecimalFormat("#0.00");



        //y & x Axis
        g1.draw(new Line2D.Double(25, 25, 25,height-25));
        g1.draw(new Line2D.Double(25,height-25,width-25,height-25));

        //label for  y-axis: (added +3 for accuracy)
        for (int i = 25; i < height-25; i = i + 50) {
            g1.drawString(String.valueOf(i - 25), 5, height - i +3);
        }

        //TODO REFACTOR CODE; replace air friction with airresistance
        //TODO improve lastPointWithAirRes to closer value -> way too inaccurate
        Point2D.Double lastPointWithAirRes = throwPOJO.getPointsOfGraphWithAirFriction().get(pointsOfGraphWithAirFriction.size() - 1);
        double lastPoint = throwPOJO.getDistanceOfThrow();
        g1.setColor(Color.RED);
        g1.drawString((int) lastPointWithAirRes.x + "m", (int) lastPointWithAirRes.x, height - 10);
        g1.setColor(Color.BLUE);
        g1.drawString((int) lastPoint + "m", (int) lastPoint, height-2);
        g1.setColor(Color.BLACK);

        //description
        g1.setColor(Color.GRAY);
        if (throwPOJO.getEquationPartsABC()[0] == 0 && !throwPOJO.isThrowIsVertical()) {
            g1.drawString("Calculated data:" + " Time: " + df.format(throwPOJO.getTimeOfThrow()) + " Distance: " + df.format(throwPOJO.getDistanceOfThrow()) +
                            " Throw Equation: " + df.format(throwPOJO.getEquationPartsABC()[1]) + " x + " + df.format(throwPOJO.getEquationPartsABC()[2]),
                    20, 20);

        }else if(throwPOJO.getEquationPartsABC()[0] != 0){
            g1.drawString("Calculated data:" + " Time: " + df.format(throwPOJO.getTimeOfThrow()) + " Distance: " + df.format(throwPOJO.getDistanceOfThrow()) +
                            " Throw Equation: " + df.format(throwPOJO.getEquationPartsABC()[0]) + " x^2 + " + df.format(throwPOJO.getEquationPartsABC()[1]) + " x + " + df.format(throwPOJO.getEquationPartsABC()[2]),
                    20, 20);
        }else if(throwPOJO.isThrowIsVertical()){
            g1.drawString("Calculated data:" + " Time: " + df.format(throwPOJO.getTimeOfThrow()) + " Vertical Distance: " + df.format(throwPOJO.getDistanceOfThrow()),
                    20, 20);
        }


        //draw graph
        g1.setColor(Color.BLUE);
        for (int i = 0; i < pointsOfGraph.size(); i++) {

            double x = 25 + pointsOfGraph.get(i).getX(); // start at the axis and not directly at window start
            double y = (height -25) - pointsOfGraph.get(i).getY(); // start from x axis below (height -25) and then subtract point

            g1.fill(new Ellipse2D.Double(x,y,2,2));
        }


        g1.setColor(Color.RED);
        for (int j = 0; j < pointsOfGraphWithAirFriction.size(); j++) {

            double x = 25 + pointsOfGraphWithAirFriction.get(j).getX(); // start at the axis and not directly at window start
            double y = (height -25) - pointsOfGraphWithAirFriction.get(j).getY(); // start from x axis below (height -25) and then subtract point

            g1.fill(new Ellipse2D.Double(x,y,2,2));
        }

    }


    /**
     * Function sets up the JFrame in which the diagram is rendered.
     * @param pointsOfGraph
     * @param throwPOJO
     */
    public void createAndShowGui(List<Point2D.Double> pointsOfGraph, ThrowPOJO throwPOJO, List<Point2D.Double> pointsOfGraphWithAirFriction){

        DisplayThrowInDiagram displayThrowInDiagram = new DisplayThrowInDiagram();
        displayThrowInDiagram.setPointsOfGraph(pointsOfGraph);
        displayThrowInDiagram.setPointsOfGraphWithAirFriction(pointsOfGraphWithAirFriction);
        displayThrowInDiagram.setThrowPOJO(throwPOJO);

        displayThrowInDiagram.setPreferredSize(new Dimension(400, 300));
        JFrame jFrame = new JFrame("Diagram");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.getContentPane().add(displayThrowInDiagram);
        jFrame.pack();
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);

    }


    public List<Point2D.Double> getPointsOfGraph() {
        return pointsOfGraph;
    }
    public void setPointsOfGraph(List<Point2D.Double> pointsOfGraph) {
        this.pointsOfGraph = pointsOfGraph;
    }

    public ThrowPOJO getThrowPOJO() {
        return throwPOJO;
    }
    public void setThrowPOJO(ThrowPOJO throwPOJO) {
        this.throwPOJO = throwPOJO;
    }

    public List<Point2D.Double> getPointsOfGraphWithAirFriction() {
        return pointsOfGraphWithAirFriction;
    }
    public void setPointsOfGraphWithAirFriction(List<Point2D.Double> pointsOfGraphWithAirFriction) {
        this.pointsOfGraphWithAirFriction = pointsOfGraphWithAirFriction;
    }
}
