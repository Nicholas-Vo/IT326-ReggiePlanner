package edu.illinoisstate.gui;

import javax.swing.*;

public class Settings extends LoginWindow {
    protected final JDialog window = new JDialog();
    protected final JPanel panel = new JPanel();

    public Settings() {
        window.setSize(500, 300);
        window.setLocationRelativeTo(null); // Center the window on the screen
        window.setTitle("Settings");
        window.setModal(true); // this prevents use of other windows

        createWindow();
    }

    public void createWindow() {
        JButton editProfileBtn = new JButton("Edit user profile");
        editProfileBtn.addActionListener(e -> {

        });

        JButton contactDeveloperBtn = new JButton("Contact the developers");
        contactDeveloperBtn.addActionListener(e -> {

        });

        JButton deleteAccountBtn = new JButton("Delete account");
        deleteAccountBtn.addActionListener(e -> {

        });

        panel.add(editProfileBtn);
        panel.add(contactDeveloperBtn);
        panel.add(deleteAccountBtn);

        window.add(panel);
        window.setVisible(true);
    }

}
