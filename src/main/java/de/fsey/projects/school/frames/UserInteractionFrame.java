package de.fsey.projects.school.frames;

import de.fsey.projects.school.calc.Calculations;
import de.fsey.projects.school.graphic.DisplayThrowInDiagram;
import de.fsey.projects.school.pojo.ThrowPOJO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.util.List;

public class UserInteractionFrame {

    public static void main(String[] args) {
        openProgram();
    }


    /**
     * Method creates a window to input all data of a throw. If submit button is clicked,
     * the type of throw is determined and calculated.
     */
    public static void openProgram() {

        //create frame, panel and layout
        JFrame frame = new JFrame("Data of a Throw");
        JPanel panel = new JPanel();
        GridLayout gridLayout = new GridLayout();


        frame.getContentPane().setLayout(gridLayout);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //create fields and buttons and add to panel
        JTextField startVelocity = new JTextField("Startgeschwindigkeit",12);
        JTextField angle = new JTextField("Abwurfwinkel", 10);
        JTextField height = new JTextField("Abwurfhöhe",10);
        JButton submitButton = new JButton("Start!");


        panel.add(startVelocity);
        panel.add(angle);
        panel.add(height);
        panel.add(submitButton);


        //general settings for frame
        frame.getContentPane().add(panel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setSize(450, 275);
        frame.setVisible(true);



        //button clicked
        submitButton.addActionListener(e -> {

            ThrowPOJO throwPOJO = new ThrowPOJO(Double.parseDouble((angle.getText())), Double.parseDouble(startVelocity.getText()), Double.parseDouble(height.getText()));
            Calculations calc = new Calculations();
            DisplayThrowInDiagram displayThrowInDiagram;

            try {
                throwPOJO = calc.determineTypeOfThrow(throwPOJO);
                List<Point2D.Double> pointList = calc.getPointsOfGraph(throwPOJO);
                throwPOJO.setPointsOfGraph(pointList);
                throwPOJO = calc.calcAirFriction(throwPOJO);



                displayThrowInDiagram = new DisplayThrowInDiagram();
                displayThrowInDiagram.createAndShowGui(pointList, throwPOJO);


            }catch (NumberFormatException exception){
                System.out.printf("Error occurred: %s", exception);
            }


        });

    }

}
