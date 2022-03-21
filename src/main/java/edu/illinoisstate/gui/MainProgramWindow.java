package edu.illinoisstate.gui;

import edu.illinoisstate.ReggiePlanner;

import javax.swing.*;
import java.awt.*;

/**
 * This is the window that first appears when you run the program
 */
public class MainProgramWindow extends ProgramWindow {
    private final ReggiePlanner program = ReggiePlanner.getProgram();
    protected final JFrame window = new JFrame();
    protected final JPanel panel = new JPanel();

    public MainProgramWindow() {
        window.setSize(800, 600);
        window.setTitle("ReggiePlanner");

        createWindow();
    }

    public void createWindow() {
        JLabel label = new JLabel("ReggiePlanner");
        label.setFont(new Font("Impact", Font.PLAIN, 35));
        label.setPreferredSize(new Dimension(250, 100));

        JButton createAccountButton = new JButton("Create a new account");
        createAccountButton.addActionListener(e -> new CreateAccount());

        JButton loginButton = new JButton("Login to an existing account");
        loginButton.addActionListener(e -> {
            loginButton.setEnabled(false); // todo this doesn't work?

            new Login();
        });

        JButton resetPasswordButton = new JButton("Forgot password");
        resetPasswordButton.addActionListener(e -> new ForgotPassword());

        panel.add(label);
        panel.add(createAccountButton);
        panel.add(loginButton);
        panel.add(resetPasswordButton);

        window.add(panel);
        window.setVisible(true);
    }

}


