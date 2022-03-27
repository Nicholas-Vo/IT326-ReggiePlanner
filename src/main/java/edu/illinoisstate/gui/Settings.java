package edu.illinoisstate.gui;

import edu.illinoisstate.UserAccount;
import edu.illinoisstate.utils.Utils;

import javax.swing.*;

public class Settings extends LoginWindow {
    private final JDialog window = new JDialog();
    private final JPanel panel = new JPanel();
    private final UserAccount user;

    public Settings(UserAccount user) {
        window.setSize(500, 300);
        window.setLocationRelativeTo(null); // Center the window on the screen
        window.setTitle("Settings");
        window.setModal(true); // this prevents use of other windows
        window.setIconImage(Utils.getReggieImage());

        this.user = user;
        addToActiveWindows(window);
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
        deleteAccountBtn.addActionListener(e -> new DeleteAccount(user));

        panel.add(editProfileBtn);
        panel.add(contactDeveloperBtn);
        panel.add(deleteAccountBtn);

        window.add(panel);
        window.setVisible(true);
    }

}
