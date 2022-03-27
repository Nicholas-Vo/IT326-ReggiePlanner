package edu.illinoisstate.gui;

import edu.illinoisstate.ReggiePlanner;
import edu.illinoisstate.UserAccount;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;

/**
 * This class is executed when a user successfully signs in
 */
public class UserHomePage extends LoginWindow {
    protected final JFrame window = new JFrame();
    protected final JPanel panel = new JPanel();

    private final UserAccount user;

    public UserHomePage(UserAccount user) {
        window.setSize(600, 600);
        window.setTitle("ReggiePlanner v" + ReggiePlanner.VERSION);
        addToActiveWindows(window);

        this.user = user;

        createWindow();
    }

    public void createWindow() {
        JLabel label = new JLabel(user.getUsername() + "'s homepage", SwingConstants.CENTER);
        label.setFont(new Font("Impact", Font.BOLD, 20));
        label.setPreferredSize(new Dimension(250, 100));

        JButton generatePlanBtn = new JButton("Generate new class plan");
        generatePlanBtn.addActionListener(e -> {

        });

        JButton editPlanBtn = new JButton("Edit existing plan");
        editPlanBtn.addActionListener(e -> {

        });

        JButton settingButton = new JButton("Settings");
        settingButton.addActionListener(e -> {

        });

        JButton logoutButton = new JButton("Logout");
        logoutButton.addActionListener(e -> {
            closeAllActiveWindows(); // Close all active windows
            new MainProgramWindow(); // re-open main program window
        });

        panel.add(generatePlanBtn);
        panel.add(editPlanBtn);
        panel.add(settingButton);
        panel.add(logoutButton);
        panel.add(label);

        window.add(panel);
        window.setVisible(true);
    }

}
