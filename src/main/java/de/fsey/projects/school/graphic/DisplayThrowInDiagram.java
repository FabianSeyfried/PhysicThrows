package de.fsey.projects.school.graphic;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class DisplayThrowInDiagram extends JPanel {

    private List<Point2D.Double> pointsOfGraph;

    int[] coordinates={100,20};

    Point2D.Double point = new Point2D.Double(1,2);




    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g1 = (Graphics2D) g;
        g1.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int width = getWidth();
        int height = getHeight();

        System.out.println(width);
        System.out.println(height);

        //y Axis
        g1.draw(new Line2D.Double(25, 25, 25,height-25));
        //x Axis
        g1.draw(new Line2D.Double(25,height-25,width-25,height-25));


        for (Point2D.Double point : pointsOfGraph) {

            double x = 25 + point.getX(); // start at the axis and not directly at window start
            double y = (height -25) - point.getY(); // start from x axis below (height -25) and then subtract point

            g1.fill(new Ellipse2D.Double(x,y,2,2));
        }

    }



    public void createAndShowGui(List<Point2D.Double> pointsOfGraph){

        DisplayThrowInDiagram displayThrowInDiagram = new DisplayThrowInDiagram();
        displayThrowInDiagram.setPointsOfGraph(pointsOfGraph);

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


}
