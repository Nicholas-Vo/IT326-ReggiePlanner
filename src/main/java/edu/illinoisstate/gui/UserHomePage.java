package edu.illinoisstate.gui;

import edu.illinoisstate.RButton;
import edu.illinoisstate.ReggiePlanner;
import edu.illinoisstate.RWindow;
import edu.illinoisstate.UserAccount;
import edu.illinoisstate.plan.GeneratePlanUI;
import edu.illinoisstate.settings.SettingsUI;
import edu.illinoisstate.utils.WindowTracker;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Dimension;
import java.awt.Font;

/**
 * This class is executed when a user successfully signs in
 */
public class UserHomePage {
    private final RWindow window = new RWindow("ReggiePlanner v" + ReggiePlanner.VERSION);
    private final UserAccount user;

    public UserHomePage(UserAccount user) {
        window.setSize(600, 600);
        window.setLocationRelativeTo(null);
        WindowTracker.addToActiveWindows(window);

        this.user = user;

        createWindow();
    }

    public void createWindow() {
        JLabel label = new JLabel(user.getUsername() + "'s homepage", SwingConstants.CENTER);
        label.setFont(new Font("Impact", Font.BOLD, 20));
        label.setPreferredSize(new Dimension(800, 100));

        RButton generatePlanBtn = new RButton("Generate new class plan");
        generatePlanBtn.addActionListener(e -> new GeneratePlanUI(user));

        RButton editPlanBtn = new RButton("Edit existing plan");
        editPlanBtn.addActionListener(e -> {

        });

        RButton createNoteBtn = new RButton("Create user note");
        createNoteBtn.addActionListener(e -> {

        });

        RButton settingButton = new RButton("Settings", () -> new SettingsUI(user));

        RButton logoutButton = new RButton("Logout", () -> {
            WindowTracker.closeAllActiveWindows();
            new MainProgramWindow(); // re-open main program window
        });

        window.addComponents(label, generatePlanBtn, editPlanBtn, createNoteBtn, settingButton, logoutButton);
    }

}
