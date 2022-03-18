package edu.illinoisstate.gui;

import edu.illinoisstate.Security;
import edu.illinoisstate.database.DatabaseHandler;

import javax.swing.*;

public class LoginWindow extends ProgramWindow {


    public LoginWindow(DatabaseHandler database) {
        window.setSize(600, 600);
        window.setTitle("Welcome back!");

    }

    @Override
    public void execute() {
        JTextField username = new JTextField("username", 15);
        panel.add(username);
        JTextField password = new JTextField("password", 15);
        panel.add(password);

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(e -> {
            if (!Security.validateUsernamePassword(username.getText(), password.getText())) {
                JOptionPane.showMessageDialog(window, "Invalid username or password.");
                return;
            }

            new CreateAccountWindow().execute();
        });

        panel.add(username);
        panel.add(password);
        panel.add(loginButton);

        window.add(panel);
        window.setVisible(true);
    }

}
