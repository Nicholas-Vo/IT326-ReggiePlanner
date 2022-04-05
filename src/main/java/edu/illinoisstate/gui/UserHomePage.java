package edu.illinoisstate.gui;

import edu.illinoisstate.RButton;
import edu.illinoisstate.ReggiePlanner;
import edu.illinoisstate.ReggieWindow;
import edu.illinoisstate.UserAccount;
import edu.illinoisstate.settings.Settings;
import edu.illinoisstate.utils.Utils;
import edu.illinoisstate.utils.WindowTracker;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

/**
 * This class is executed when a user successfully signs in
 */
public class UserHomePage {
    private final ReggieWindow window = new ReggieWindow("ReggiePlanner v" + ReggiePlanner.VERSION);
    private final JPanel panel = new JPanel();

    private final UserAccount user;

    public UserHomePage(UserAccount user) {
        window.setSize(600, 600);
        window.setModal(false);
        WindowTracker.addToActiveWindows(window);

        this.user = user;

        createWindow();
    }

    public void createWindow() {
        JLabel label = new JLabel(user.getUsername() + "'s homepage", SwingConstants.CENTER);
        label.setFont(new Font("Impact", Font.BOLD, 20));
        label.setPreferredSize(new Dimension(800, 100));

        RButton generatePlanBtn = new RButton("Generate new class plan");
        generatePlanBtn.addActionListener(e -> {

        });

        RButton editPlanBtn = new RButton("Edit existing plan");
        editPlanBtn.addActionListener(e -> {

        });

        RButton createNoteBtn = new RButton("Create user note");
        createNoteBtn.addActionListener(e -> {

        });

        RButton settingButton = new RButton("Settings", () -> new Settings(user));

        RButton logoutButton = new RButton("Logout", () -> {
            WindowTracker.closeAllActiveWindows();
            new MainProgramWindow(); // re-open main program window
        });

        window.addComponents(label, generatePlanBtn, editPlanBtn, createNoteBtn, settingButton, logoutButton);
    }

}
