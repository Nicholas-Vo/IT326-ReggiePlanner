package edu.illinoisstate.gui;

import edu.illinoisstate.ReggiePlanner;
import edu.illinoisstate.SecurityHandler;

import javax.swing.*;

public class Login extends ProgramWindow {
    private final ReggiePlanner program = ReggiePlanner.getProgram();
    private final SecurityHandler security = program.getSecurityHandler();
    protected final JFrame window = new JFrame();
    protected final JPanel panel = new JPanel();

    public Login() {
        window.setSize(600, 600);
        window.setTitle("Welcome back!");

        createWindow();
    }

    public void createWindow() {
        JTextField username = new JTextField("username", 15);
        panel.add(username);
        JTextField password = new JTextField("password", 15);
        panel.add(password);

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(e -> {
            if (!security.validateUsernamePassword(username.getText(), password.getText())) {
                JOptionPane.showMessageDialog(window, "Invalid username or password.");
                return;
            }

            new Home();
        });

        panel.add(username);
        panel.add(password);
        panel.add(loginButton);

        window.add(panel);
        window.setVisible(true);
    }

}
