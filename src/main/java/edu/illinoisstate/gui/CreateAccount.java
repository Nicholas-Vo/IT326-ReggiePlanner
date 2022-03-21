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
    }

    @Override
    public void execute() {
        JLabel label = new JLabel("Create an account");

        JTextField emailField = new JTextField("email", 15);
        panel.add(emailField);
        JTextField usernameField = new JTextField("username", 15);
        panel.add(usernameField);
        JTextField passwordField = new JTextField("password", 15);
        panel.add(passwordField);

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(e -> {
            if (!security.isValidUsername(usernameField.getText())) {
                JOptionPane.showMessageDialog(window, "Sorry, that's an invalid username.");
                return;
            }

            if (!security.isValidPassword(usernameField.getText(), passwordField.getText())) {
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

}
