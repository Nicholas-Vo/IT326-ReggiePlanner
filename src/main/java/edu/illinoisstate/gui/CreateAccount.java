package edu.illinoisstate.gui;

import edu.illinoisstate.RButton;
import edu.illinoisstate.ReggieWindow;
import edu.illinoisstate.UserAccount;
import edu.illinoisstate.database.Database;
import edu.illinoisstate.utils.HintPasswordTextBox;
import edu.illinoisstate.utils.HintTextBox;
import edu.illinoisstate.utils.Utils;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.util.Arrays;
import java.util.UUID;

public class CreateAccount {
    private final ReggieWindow window = new ReggieWindow("Create a new account");
    private final JPanel panel = new JPanel();

    public CreateAccount() {
        window.setSize(500, 300);

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

            if (!isValidPassword(usernameField.getText(), passwordField.getText())) {
                JOptionPane.showMessageDialog(window, "Sorry, that's an invalid password.");
                return;
            }

            String email = emailField.getText();
            if (email.length() < 4 || email.length() > 24) {
                JOptionPane.showMessageDialog(window, "Sorry, that's an invalid email.");
                return;
            }

            if (!email.contains("@")) {
                JOptionPane.showMessageDialog(window, "Sorry, that's an invalid email provider.");
                return;
            }

            Database database = Database.getInstance();
            if (database.getUsernamesList().contains(usernameField.getText())) {
                JOptionPane.showMessageDialog(window, "Sorry, that username already exists.");
                return;
            }

            UserAccount account = new UserAccount(UUID.randomUUID(), emailField.getText(),
                    usernameField.getText(), Utils.hash(passwordField.getText()));

            database.saveUserAccount(account);
            JOptionPane.showMessageDialog(window,
                    "A confirmation email has been sent to " + emailField.getText() + ".");
            window.dispatchEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSING));
            System.out.println("Created new user account \"" + account.getUsername() + "\".");
        });

        window.getRootPane().setDefaultButton(submitButton); // Allows Enter key to submit
        window.addComponents(emailField, usernameField, passwordField, label, submitButton);
    }

    /**
     * Determine whether to reject input password
     *
     * @param username the input username
     * @param password the input password
     * @return bool value
     */
    private boolean isValidPassword(String username, String password) {
        if (username.equalsIgnoreCase(password)) {
            return false;
        }

        return password.length() > 3 && password.length() < 16;
    }
}

