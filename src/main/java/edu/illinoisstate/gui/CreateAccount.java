package edu.illinoisstate.gui;

import edu.illinoisstate.UserAccount;
import edu.illinoisstate.database.Database;
import edu.illinoisstate.utils.HintPasswordTextBox;
import edu.illinoisstate.utils.HintTextBox;
import edu.illinoisstate.utils.Utils;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.util.UUID;

public class CreateAccount {
    private final JDialog window = new JDialog();
    private final JPanel panel = new JPanel();

    public CreateAccount() {
        window.setSize(450, 300);
        window.setLocationRelativeTo(null); // Center the window on the screen
        window.setTitle("Create a new account");
        window.setModal(true); // this prevents use of other windows
        window.setIconImage(Utils.getImage("reggie.png"));

        createWindow();
    }

    public void createWindow() {
        JLabel label = new JLabel("Create an account");

        JTextField emailField = new HintTextBox("email", 15);
        panel.add(emailField);
        JTextField usernameField = new HintTextBox("username", 15);
        panel.add(usernameField);
        JTextField passwordField = new HintPasswordTextBox("password", 15);
        panel.add(passwordField);

        JButton submitButton = new JButton("Submit");
        window.getRootPane().setDefaultButton(submitButton); // Allows Enter key to submit

        submitButton.addActionListener(e -> {
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

        panel.add(label);
        panel.add(submitButton);

        window.add(panel);
        window.setVisible(true);
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

