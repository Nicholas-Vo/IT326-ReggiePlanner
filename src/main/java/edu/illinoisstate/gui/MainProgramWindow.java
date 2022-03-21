package edu.illinoisstate.gui;

import edu.illinoisstate.ReggiePlanner;

import javax.swing.*;
import java.awt.*;

/**
 * This is the window that first appears when you run the program
 */
public class MainProgramWindow extends ProgramWindow {

    public MainProgramWindow(ReggiePlanner program) {
        super(program, 800, 600, "ReggiePlanner");
    }

    @Override
    public void execute() {
        JLabel label = new JLabel("ReggiePlanner");
        label.setFont(new Font("Impact", Font.PLAIN, 35));
        label.setPreferredSize(new Dimension(250, 100));

        JButton createAccountButton = new JButton("Create a new account");
        createAccountButton.addActionListener(e -> new CreateAccountWindow(program).execute());

        JButton loginButton = new JButton("Login to an existing account");
        loginButton.addActionListener(e -> {
            loginButton.setEnabled(false); // todo this doesn't work?
            new LoginWindow(program).execute();
        });

        JButton resetPasswordButton = new JButton("Forgot password");
        resetPasswordButton.addActionListener(e -> new ForgotPasswordWindow(program).execute());

        panel.add(label);
        panel.add(createAccountButton);
        panel.add(loginButton);
        panel.add(resetPasswordButton);

        window.add(panel);
        window.setVisible(true);
    }

}


