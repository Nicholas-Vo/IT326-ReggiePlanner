package edu.illinoisstate.gui;

import edu.illinoisstate.RButton;
import edu.illinoisstate.utils.Utils;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

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
        window.setResizable(false);
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

        RButton createAccountButton = new RButton("Create a new account", CreateAccount::new, .5f);
        RButton loginButton = new RButton("Login", () -> new Login(window), .5f);
        RButton resetPasswordButton = new RButton("Forgot password", ForgotPassword::new, .5f);
        RButton exitProgramButton = new RButton("Exit", () -> System.exit(0), .5f);

        // This is the actual image of reggie that appears
        JLabel reggieLogo = new JLabel(new ImageIcon(Utils.getImage("reggie.png")));
        reggieLogo.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(label);
        panel.add(reggieLogo);
        panel.add(loginButton);
        panel.add(createAccountButton);
        panel.add(resetPasswordButton);
        panel.add(exitProgramButton);

        JLabel verLabel = new JLabel("v1.0.0");
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


