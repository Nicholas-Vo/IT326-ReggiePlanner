package edu.illinoisstate.gui;

import edu.illinoisstate.ReggiePlanner;

import javax.swing.*;
import java.awt.*;

/**
 * This is the window that first appears when you run the program
 */
public class MainProgramWindow {
    protected final JFrame window = new JFrame();
    protected final JPanel panel = new JPanel();

    public MainProgramWindow() {
        window.setSize(500, 450);
        window.setLocationRelativeTo(null); // Center the window on the screen
        window.setTitle("ReggiePlanner");
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        window.getContentPane().setLayout(new BoxLayout(window.getContentPane(), BoxLayout.Y_AXIS));

        createWindow();
    }

    public void createWindow() {
        JLabel label = new JLabel("ReggiePlanner");
        label.setFont(new Font("Jumble", Font.BOLD, 35));
        label.setPreferredSize(new Dimension(250, 100));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        JButton createAccountButton = new JButton("Create a new account");
        createAccountButton.addActionListener(e -> new CreateAccount());
        createAccountButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton loginButton = new JButton("Login to an existing account");
        loginButton.addActionListener(e -> new Login(window));
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton resetPasswordButton = new JButton("Forgot password");
        resetPasswordButton.addActionListener(e -> new ForgotPassword());

        resetPasswordButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(new JToolBar.Separator(new Dimension(20, 20))); // subtle white space
        panel.add(label);
        panel.add(new JToolBar.Separator(new Dimension(20, 20))); // subtle white space
        panel.add(createAccountButton);
        panel.add(new JToolBar.Separator(new Dimension(20, 3)));
        panel.add(loginButton);
        panel.add(new JToolBar.Separator(new Dimension(20, 3)));
        panel.add(resetPasswordButton);

        panel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel verLabel = new JLabel("Version " + ReggiePlanner.VERSION);
        verLabel.setFont(new Font("Abadi", Font.PLAIN, 12));
        verLabel.setPreferredSize(new Dimension(250, 100));
        verLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel infoPanel = new JPanel();
        infoPanel.add(Box.createHorizontalStrut(5)); // Add white space
        infoPanel.add(verLabel);
        infoPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));

        window.add(panel);
        window.add(infoPanel);
        window.setVisible(true);
    }

}


