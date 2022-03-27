package edu.illinoisstate.gui;

import edu.illinoisstate.UserAccount;

import javax.swing.*;
import java.awt.*;

/**
 * This class is executed when a user successfully signs in
 */
public class UserHomePage extends ProgramWindow {
    protected final JFrame window = new JFrame();
    protected final JPanel panel = new JPanel();

    private final UserAccount user;

    public UserHomePage(UserAccount user) {
        window.setSize(600, 600);
        window.setTitle("ReggiePlanner v1.0.0");

        this.user = user;

        createWindow();
    }

    public void createWindow() {
        JLabel label = new JLabel(user.getUsername() + "'s homepage", SwingConstants.CENTER);
        label.setFont(new Font("Impact", Font.CENTER_BASELINE, 20));
        label.setPreferredSize(new Dimension(250, 100));

        JButton generatePlanBtn = new JButton("Generate new class plan");
        generatePlanBtn.addActionListener(e -> {

        });

        JButton editPlanBtn = new JButton("Edit existing plan");
        editPlanBtn.addActionListener(e -> {

        });

        JButton editProfileBtn = new JButton("Edit user profile");
        editProfileBtn.addActionListener(e -> {

        });


        panel.add(generatePlanBtn);
        panel.add(editPlanBtn);
        panel.add(editProfileBtn);
        panel.add(label);

        window.add(panel);
        window.setVisible(true);
    }

}
