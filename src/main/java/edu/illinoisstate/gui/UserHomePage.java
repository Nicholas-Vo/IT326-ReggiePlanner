package edu.illinoisstate.gui;

import edu.illinoisstate.ReggiePlanner;
import edu.illinoisstate.UserAccount;
import edu.illinoisstate.utils.Utils;
import edu.illinoisstate.utils.WindowTracker;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.TimeUnit;

/**
 * This class is executed when a user successfully signs in
 */
public class UserHomePage {
    private final JFrame window = new JFrame();
    private final JPanel panel = new JPanel();

    private final UserAccount user;

    public UserHomePage(UserAccount user) {
        window.setSize(600, 600);
        window.setLocationRelativeTo(null); // Center the window on the screen
        window.setTitle("ReggiePlanner v" + ReggiePlanner.VERSION);
        window.setIconImage(Utils.getImage("reggie.png"));
        WindowTracker.addToActiveWindows(window);

        this.user = user;

        createWindow();
    }

    public void createWindow() {
        JLabel label = new JLabel(user.getUsername() + "'s homepage", SwingConstants.CENTER);
        label.setFont(new Font("Impact", Font.BOLD, 20));
        label.setPreferredSize(new Dimension(800, 100));

        JButton generatePlanBtn = new JButton("Generate new class plan");
        generatePlanBtn.addActionListener(e -> {
            JOptionPane.showMessageDialog(window, "Calculating...");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            JOptionPane.showMessageDialog(window, "You should drop out.");
        });

        JButton editPlanBtn = new JButton("Edit existing plan");
        editPlanBtn.addActionListener(e -> {

        });

        JButton createNoteBtn = new JButton("Create user note");
        createNoteBtn.addActionListener(e -> {

        });

        JButton settingButton = new JButton("Settings");
        settingButton.addActionListener(e -> new Settings(user));

        JButton logoutButton = new JButton("Logout");
        logoutButton.addActionListener(e -> {
            WindowTracker.closeAllActiveWindows();
            new MainProgramWindow(); // re-open main program window
        });

        panel.add(label);
        panel.add(generatePlanBtn);
        panel.add(editPlanBtn);
        panel.add(createNoteBtn);
        panel.add(settingButton);
        panel.add(logoutButton);

        window.add(panel);
        window.setVisible(true);
    }

}
