package edu.illinoisstate.gui;

import edu.illinoisstate.ReggiePlanner;
import edu.illinoisstate.utils.Utils;

import javax.swing.*;
import java.awt.*;

/**
 * This is the window that first appears when you run the program
 */
public class MainProgramWindow {
    private final JFrame window = new JFrame();
    private final JPanel panel = new JPanel();

    public MainProgramWindow() {
        window.setSize(500, 450);
        window.setLocationRelativeTo(null); // Center the window on the screen
        window.setTitle("ReggiePlanner");
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        window.getContentPane().setLayout(new BoxLayout(window.getContentPane(), BoxLayout.Y_AXIS));
        window.setIconImage(Utils.getImage("reggie.png"));

        createWindow();
    }

    public void createWindow() {
        JLabel label = new JLabel("ReggiePlanner"); // This is the big ReggiePlanner "logo" on the main window
        label.setFont(new Font("Jumble", Font.BOLD, 35));
        label.setPreferredSize(new Dimension(250, 100));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton createAccountButton = new JButton("Create a new account");
        createAccountButton.addActionListener(e -> new CreateAccount());
        createAccountButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(e -> new Login(window));
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton resetPasswordButton = new JButton("Forgot password");
        resetPasswordButton.addActionListener(e -> new ForgotPassword());
        resetPasswordButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton exitProgramButton = new JButton("Exit");
        exitProgramButton.addActionListener(e -> System.exit(0));
        exitProgramButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // This is the actual image of reggie that appears
        JLabel reggieLogo = new JLabel(new ImageIcon(Utils.getImage("reggie.png")));
        reggieLogo.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(label);
        Utils.addWhiteSpace(panel,5);
        panel.add(reggieLogo);
        Utils.addWhiteSpace(panel,5);
        panel.add(loginButton);
        Utils.addWhiteSpace(panel,5);
        panel.add(createAccountButton);
        Utils.addWhiteSpace(panel,5);
        panel.add(resetPasswordButton);
        Utils.addWhiteSpace(panel,5);
        panel.add(exitProgramButton);

        JLabel verLabel = new JLabel("Version " + ReggiePlanner.VERSION);
        verLabel.setFont(new Font("Abadi", Font.PLAIN, 12));
        verLabel.setPreferredSize(new Dimension(250, 100));
        verLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // this info JPanel just holds the version, separated for aesthetic
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


