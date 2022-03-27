package edu.illinoisstate.gui;

import edu.illinoisstate.UserAccount;
import edu.illinoisstate.database.Database;
import edu.illinoisstate.utils.HintTextBox;

import javax.swing.*;
import java.awt.event.WindowEvent;

import static edu.illinoisstate.utils.Utils.hash;

public class Settings {
    protected final JFrame window = new JFrame();
    protected final JPanel panel = new JPanel();

    public Settings() {
        window.setSize(500, 300);
        window.setTitle("Settings");

        createWindow();
    }

    public void createWindow() {
        JButton editProfileBtn = new JButton("Edit user profile");
        editProfileBtn.addActionListener(e -> {

        });

        JButton contactDeveloperBtn = new JButton("Contact the developers");
        contactDeveloperBtn.addActionListener(e -> {

        });

        panel.add(editProfileBtn);
        panel.add(contactDeveloperBtn);

        window.add(panel);
        window.setVisible(true);
    }

}
