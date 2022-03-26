package edu.illinoisstate.gui;

import javax.swing.*;

/**
 * This class is executed when a user successfully signs in
 */
public class Home extends ProgramWindow {
    protected final JFrame window = new JFrame();
    protected final JPanel panel = new JPanel();

    public Home() {
        window.setSize(600, 600);
        window.setTitle("ReggiePlanner v1.0.0");

        createWindow();
    }

    public void createWindow() {
        JButton generatePlanBtn = new JButton("Generate new plan");

        for (int i = 0; i< 21342; i++) {
            System.out.println(i);
        }
        panel.add(generatePlanBtn);

        window.add(panel);
        window.setVisible(true);
    }

}
