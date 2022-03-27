package edu.illinoisstate.gui;

import javax.swing.*;
import java.awt.*;

/**
 * This is the window that first appears when you run the program
 */
public class MainProgramWindow extends ProgramWindow {
    protected final JFrame window = new JFrame();
    protected final JPanel panel = new JPanel();

    public MainProgramWindow() {
        window.setSize(800, 600);
        window.setTitle("ReggiePlanner");

        createWindow();
    }

    public void createWindow() {
        JLabel label = new JLabel("ReggiePlanner");
        label.setFont(new Font("Impact", Font.BOLD, 35));
        label.setPreferredSize(new Dimension(250, 100));

        JButton createAccountButton = new JButton("Create a new account");
        createAccountButton.addActionListener(e -> new CreateAccount());

        JButton loginButton = new JButton("Login to an existing account");
        loginButton.addActionListener(e -> new Login());

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


