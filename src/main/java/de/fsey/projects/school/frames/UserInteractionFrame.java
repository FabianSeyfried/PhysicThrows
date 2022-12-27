package de.fsey.projects.school.frames;

import de.fsey.projects.school.frames.calc.Calculations;

import javax.swing.*;


import java.awt.GridLayout;

public class UserInteractionFrame {

    public static void main(String[] args) {
        openProgram();
    }



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
            Calculations calc = new Calculations();


            calc.start(Double.parseDouble((angle.getText())), Double.parseDouble(startVelocity.getText()), Double.parseDouble(height.getText()));
        });

    }

}
