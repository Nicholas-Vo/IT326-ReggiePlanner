package edu.illinoisstate.gui;

import edu.illinoisstate.database.DatabaseHandler;

import javax.swing.*;
import java.awt.*;

public class MainProgramWindow extends ProgramWindow {
    private final DatabaseHandler database;

    public MainProgramWindow() {
        window.setSize(800, 600);
        window.setTitle("ReggiePlanner");

        database = new DatabaseHandler();
    }

    @Override
    public void execute() {
        JLabel label = new JLabel("ReggiePlanner");
        label.setFont(new Font("Impact", Font.PLAIN, 35));
        label.setPreferredSize(new Dimension(250, 100));

        JButton createAccountButton = new JButton("Create a new account");
        createAccountButton.addActionListener(e -> new CreateAccountWindow().execute());

        JButton loginButton = new JButton("Login to an existing account");
        loginButton.addActionListener(e -> {
            loginButton.setEnabled(false); // todo this doesn't work?
            new LoginWindow(database).execute();
        });

        JButton resetPasswordButton = new JButton("Forgot password");
        resetPasswordButton.addActionListener(e -> new ForgotPasswordWindow(database).execute());

        panel.add(label);
        panel.add(createAccountButton);
        panel.add(loginButton);
        panel.add(resetPasswordButton);

        window.add(panel);
        window.setVisible(true);
    }

}


