package edu.illinoisstate.gui;

import edu.illinoisstate.ReggiePlanner;
import edu.illinoisstate.SecurityHandler;
import edu.illinoisstate.UserAccount;

import javax.swing.*;

public class CreateAccount extends ProgramWindow {
    private final ReggiePlanner program = ReggiePlanner.getProgram();
    private final SecurityHandler security = program.getSecurityHandler();
    protected final JFrame window = new JFrame();
    protected final JPanel panel = new JPanel();

    public CreateAccount() {
        window.setSize(600, 600);
        window.setTitle("Create a new account");

        createWindow();
    }

    public void createWindow() {
        JLabel label = new JLabel("Create an account");

        JTextField emailField = new JTextField("email", 15);
        panel.add(emailField);
        JTextField usernameField = new JTextField("username", 15);
        panel.add(usernameField);
        JTextField passwordField = new JTextField("password", 15);
        panel.add(passwordField);

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(e -> {
            if (!isValidUsername(usernameField.getText())) {
                JOptionPane.showMessageDialog(window, "Sorry, that's an invalid username.");
                return;
            }

            if (!isValidPassword(usernameField.getText(), passwordField.getText())) {
                JOptionPane.showMessageDialog(window, "Sorry, that's an invalid password.");
                return;
            }

            if (!security.isValidEmail(emailField.getText())) {
                JOptionPane.showMessageDialog(window, "Sorry, that's an invalid email.");
                return;
            }

            UserAccount account = new UserAccount(emailField.getText(),
                    usernameField.getText(), passwordField.getText());

            String msg = "Successfully created the account " + account.getUsername() + ".";
            JOptionPane.showMessageDialog(window, msg);
        });

        panel.add(label);
        panel.add(submitButton);

        window.add(panel);
        window.setVisible(true);
    }

    /**
     * Determine if the username should be rejected or not.
     * @param username the input username
     * @return boolean value
     */
    public boolean isValidUsername(String username) {
        return username.length() > 3 && username.length() < 16;
    }

    public boolean isValidPassword(String username, String password) {
        if (username.equalsIgnoreCase(password)) {
            return false;
        }

        return password.length() > 3 && password.length() < 16;
    }

}
