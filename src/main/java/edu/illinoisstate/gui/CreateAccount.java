package edu.illinoisstate.gui;

import edu.illinoisstate.RButton;
import edu.illinoisstate.RWindow;
import edu.illinoisstate.UserAccount;
import edu.illinoisstate.database.Database;
import edu.illinoisstate.utils.HintPasswordTextBox;
import edu.illinoisstate.utils.HintTextBox;
import edu.illinoisstate.utils.Security;
import edu.illinoisstate.utils.Utils;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.util.UUID;

public class CreateAccount {
    private final RWindow window = new RWindow("Create a new account");

    public CreateAccount() {
        window.setSize(500, 300);
        window.setLocationRelativeTo(null);

        createWindow();
    }

    public void createWindow() {
        JLabel label = new JLabel("Create an account");
        JTextField emailField = new HintTextBox("email", 15);
        JTextField usernameField = new HintTextBox("username", 15);
        JTextField passwordField = new HintPasswordTextBox("password", 15);

        RButton submitButton = new RButton("Submit", () -> {
            String username = usernameField.getText();
            if (username.length() < 4 || username.length() > 16) {
                JOptionPane.showMessageDialog(window, "Sorry, that's an invalid username length.");
                return;
            }

            if (!Security.isValidPassword(usernameField.getText(), passwordField.getText())) {
                JOptionPane.showMessageDialog(window, "Sorry, that's an invalid password.");
                return;
            }

            String email = emailField.getText();
            if (email.length() < 4 || email.length() > 24) {
                JOptionPane.showMessageDialog(window, "Sorry, that's an invalid email.");
                return;
            }

            Database database = Database.getInstance();
            if (database.getExistingEmailList().contains(emailField.getText())) {
                JOptionPane.showMessageDialog(window, "That email is already registered within our system.");
                return;
            }

            if (!email.contains("@")) {
                JOptionPane.showMessageDialog(window, "Sorry, that's an invalid email provider.");
                return;
            }

            if (database.getUsernamesList().contains(usernameField.getText())) {
                JOptionPane.showMessageDialog(window, "Sorry, that username already exists.");
                return;
            }

            UserAccount account = new UserAccount(UUID.randomUUID(), emailField.getText(),
                    usernameField.getText(), Security.hash(passwordField.getText()));

            database.saveUserAccount(account);
            JOptionPane.showMessageDialog(window, "Account created: You may now log in.");
            window.dispatchEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSING));
            System.out.println("Created new user account \"" + account.getUsername() + "\".");
        });

        window.getRootPane().setDefaultButton(submitButton); // Allows Enter key to submit
        window.addComponents(emailField, usernameField, passwordField, label, submitButton);
    }

}

