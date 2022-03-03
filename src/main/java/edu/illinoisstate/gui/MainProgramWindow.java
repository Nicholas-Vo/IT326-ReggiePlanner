package edu.illinoisstate.gui;

import edu.illinoisstate.UserAccount;
import edu.illinoisstate.plan.UserPlan;

import javax.swing.*;

public class MainProgramWindow extends ProgramWindow {
    private UserAccount account;

    public MainProgramWindow() {
        window.setSize(800, 800);
    }

    public void execute() {
        JLabel label = new JLabel("Create an account");

        JTextField emailField = new JTextField("email", 15);
        panel.add(emailField);
        JTextField usernameField = new JTextField("username", 15);
        panel.add(usernameField);
        JTextField passwordField = new JTextField("password", 15);
        panel.add(passwordField);

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(e -> {
            account = new UserAccount(emailField.getText(),
                    usernameField.getText(),
                    passwordField.getText());

            String msg = "Successfully created the account " + account.getUsername() + ".";
            JOptionPane.showMessageDialog(window, msg);
        });

        panel.add(label);
        panel.add(submitButton);

        window.add(panel);
        window.setVisible(true);
    }


}


