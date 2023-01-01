package de.fsey.projects.school.graphic;

import de.fsey.projects.school.pojo.ThrowPOJO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.text.DecimalFormat;
import java.util.List;

public class DisplayThrowInDiagram extends JPanel {

    private List<Point2D.Double> pointsOfGraph;
    private ThrowPOJO throwPOJO;

    public DisplayThrowInDiagram() {
    }


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

        //label for x-axis (
        for (int i = 25; i < width-25; i = i + 50) {
            g1.drawString(String.valueOf(i - 25), i -3, height-10);
        }


        g1.setColor(Color.GRAY);
        if (throwPOJO.getEquationPartsABC()[0] == 0) {
            g1.drawString("Calculated data:" + " Time: " + df.format(throwPOJO.getTimeOfThrow()) + " Distance: " + df.format(throwPOJO.getDistanceOfThrow()) +
                            " Throw Equation: " + df.format(throwPOJO.getEquationPartsABC()[1]) + " x + " + df.format(throwPOJO.getEquationPartsABC()[2]),
                    20, 20);

        }else if(throwPOJO.getEquationPartsABC()[0] != 0){
            g1.drawString("Calculated data:" + " Time: " + df.format(throwPOJO.getTimeOfThrow()) + " Distance: " + df.format(throwPOJO.getDistanceOfThrow()) +
                            " Throw Equation: " + df.format(throwPOJO.getEquationPartsABC()[0]) + " x^2 + " + df.format(throwPOJO.getEquationPartsABC()[1]) + " x + " + df.format(throwPOJO.getEquationPartsABC()[2]),
                    20, 20);
        }

        //pointsOfGraph.forEach(System.out::println);

        g1.setColor(Color.BLUE);
        //draw points
        for (int i = 0; i < pointsOfGraph.size(); i++) {

            double x = 25 + pointsOfGraph.get(i).getX(); // start at the axis and not directly at window start
            double y = (height -25) - pointsOfGraph.get(i).getY(); // start from x axis below (height -25) and then subtract point

            g1.fill(new Ellipse2D.Double(x,y,2,2));
        }

    }



    public void createAndShowGui(List<Point2D.Double> pointsOfGraph, ThrowPOJO throwPOJO){

        DisplayThrowInDiagram displayThrowInDiagram = new DisplayThrowInDiagram();
        displayThrowInDiagram.setPointsOfGraph(pointsOfGraph);
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
}
