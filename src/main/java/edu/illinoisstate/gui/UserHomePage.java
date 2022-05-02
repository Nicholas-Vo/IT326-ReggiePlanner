package edu.illinoisstate.gui;

import edu.illinoisstate.RMenu;
import edu.illinoisstate.RWindow;
import edu.illinoisstate.UserAccount;
import edu.illinoisstate.database.DatabaseHandler;
import edu.illinoisstate.plan.UserPlan;
import edu.illinoisstate.settings.ContactDevelopersUI;
import edu.illinoisstate.settings.DeleteAccountUI;
import edu.illinoisstate.utils.WindowTracker;

import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;


/**
 * This class is executed when a user successfully signs in
 */
public class UserHomePage {
    private final UserAccount user;
    private final RWindow window = new RWindow("ReggiePlanner v1.0.0");
    private final CardLayout cardLayout = new CardLayout();
    private final JPanel homePanel = new JPanel(cardLayout);
    private final JMenuBar menuBar = new JMenuBar();

    public UserHomePage(UserAccount user) {
        window.setSize(600, 600);
        window.setLocationRelativeTo(null);
        WindowTracker.addToActiveWindows(window);

        window.setLayout(new CardLayout());

        this.user = user;

        createWindow();
    }

    public void createWindow() {
        JLabel label = new JLabel("ReggiePlanner", SwingConstants.CENTER);
        label.setFont(new Font("Jumble", Font.BOLD, 35));
        label.setPreferredSize(new Dimension(800, 100));

        RMenu generatePlanMenu = new RMenu("Generate new plan");
        generatePlanMenu.addButtonClickAction(() -> {
            UserPlan plan = DatabaseHandler.getUserPlan(user);

            if (plan != null) {
                String confirmMsg = "Are you sure you wish to generate a new plan?";
                if (JOptionPane.showConfirmDialog(window, confirmMsg) != 0) {
                    return;
                }
            }

            new GenerateNewPlan(window, homePanel, user);
            homePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
            cardLayout.show(homePanel, "Generate");
            window.pack();
        });

        RMenu editPlan = new RMenu("Edit your plan");
        editPlan.addButtonClickAction(() -> {
            UserPlan plan = DatabaseHandler.getUserPlan(user);

            if (plan == null) {
                JOptionPane.showMessageDialog(window, "You must generate a plan before doing that.");
                return;
            }

            new EditYourPlan(window, homePanel, user);
            homePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
            cardLayout.show(homePanel, "Edit");
            window.pack();
        });

        RMenu addUserNote = new RMenu("Add note");
        addUserNote.addButtonClickAction(() -> {

        });

        JMenu settingsMenu = new JMenu("Settings");
        JMenuItem editProfile = new JMenuItem("Edit profile");
        editProfile.addActionListener(e -> {
        });
        JMenuItem contactDevs = new JMenuItem("Contact developers");
        contactDevs.addActionListener(e -> new ContactDevelopersUI(user));
        JMenuItem deleteAccount = new JMenuItem("Delete account");
        deleteAccount.addActionListener(e -> new DeleteAccountUI(user));

        settingsMenu.add(editProfile);
        settingsMenu.add(contactDevs);
        settingsMenu.add(deleteAccount);

        RMenu logout = new RMenu("Log out");
        logout.addButtonClickAction(() -> {
            WindowTracker.closeAllActiveWindows();
            new MainProgramWindow(); // re-open main program window
        });

        menuBar.add(generatePlanMenu);
        menuBar.add(editPlan);
        menuBar.add(addUserNote);
        menuBar.add(settingsMenu);
        menuBar.add(logout);
        homePanel.add(label);

        window.setJMenuBar(menuBar);
        window.addComponents(homePanel);
    }

}
