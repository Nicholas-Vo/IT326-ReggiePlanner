package edu.illinoisstate.gui;

import edu.illinoisstate.SecurityHandler;

import javax.swing.*;
import java.awt.event.WindowEvent;

public class Login extends ProgramWindow {
    protected final JFrame window = new JFrame();
    protected final JPanel panel = new JPanel();

    public Login() {
        window.setSize(500, 300);
        window.setTitle("Login");

        createWindow();
    }

    public void createWindow() {
        JTextField username = new JTextField("username", 15);
        panel.add(username);
        JTextField password = new JTextField("password", 15);
        panel.add(password);

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(e -> {
            if (!SecurityHandler.getInstanace().validateUsernamePassword(username.getText(), password.getText())) {
                JOptionPane.showMessageDialog(window, "Invalid username or password.");
                return;
            }

            window.dispatchEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSING));
            new Home();
        });

        panel.add(username);
        panel.add(password);
        panel.add(loginButton);

        window.add(panel);
        window.setVisible(true);
    }

}