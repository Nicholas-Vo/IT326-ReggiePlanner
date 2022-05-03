package edu.illinoisstate.gui;

import edu.illinoisstate.Controller;
import edu.illinoisstate.RButton;
import edu.illinoisstate.RWindow;
import edu.illinoisstate.utils.AccountValidator;
import edu.illinoisstate.utils.HintPasswordTextBox;
import edu.illinoisstate.utils.HintTextBox;
import edu.illinoisstate.utils.Utils;
import edu.illinoisstate.email.EmailHandler;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.WindowEvent;

public class CreateAccount {
    private final RWindow window = new RWindow("Create a new account");

    public CreateAccount() {
        window.setSize(500, 125);
        window.setLocationRelativeTo(null);
        Utils.allowEscapeToClose(window, window.getPanel());

        createWindow();
    }

    public void createWindow() {
        JLabel label = new JLabel("Create an account");
        JTextField emailField = new HintTextBox("email", 15);
        JTextField usernameField = new HintTextBox("username", 15);
        JTextField passwordField = new HintPasswordTextBox("password", 15);

        RButton submitButton = new RButton("Submit", () -> {
            String username = usernameField.getText();
            String password = passwordField.getText();
            String email = emailField.getText();

            AccountValidator validator = new AccountValidator();

            if (!validator.check(email, username, password)) {
                JOptionPane.showMessageDialog(window, validator.getReason());
                return;
            }

            if (Controller.createAccount(email, username, password)) {
                JOptionPane.showMessageDialog(window, "Account created: You may now log in.");
                System.out.println("Created new user account \"" + username + "\".");
                window.dispatchEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSING));
                EmailHandler emailHandler = new EmailHandler();
                emailHandler.sendAccountConfirmation(username,email);

            } else {
                JOptionPane.showMessageDialog(window, "Error creating account. Please contact support.");
            }
        });

        window.getRootPane().setDefaultButton(submitButton); // Allows Enter key to submit
        window.addComponents(emailField, usernameField, passwordField, label, submitButton);
    }

}

