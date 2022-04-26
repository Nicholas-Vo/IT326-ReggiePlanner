package edu.illinoisstate.utils;

import edu.illinoisstate.database.DatabaseHandler;

import javax.swing.JOptionPane;

/**
 * Validate a new user account based on given arguments
 */
public class AccountValidator {
    private final StringBuilder reasons = new StringBuilder();

    public String getReason() {
        return reasons.toString();
    }

    /**
     * Checks to ensure the given arguments can create a valid new user account.
     * @param email: The input email
     * @param username: The input username
     * @param password: The input password
     * @return boolean value
     */
    public boolean check(String email, String username, String password) {
        if (username.length() < 4) {
            reasons.append("Username must be at least 4 characters long");
        }

        if (username.length() > 16) {
            reasons.append("Username cannot be longer than 16 characters");
        }

        if (email.length() < 4 || email.length() > 35) {
            reasons.append("\nInvalid email length");
        }

        if (!Security.isValidEmail(email)) {
            reasons.append("\nInvalid email");
        }

        if (!Security.isValidPassword(username, password)) {
            reasons.append("\nInvalid password");
        }

        if (DatabaseHandler.emailExistsInSystem(email)) {
            reasons.append("\nEmail already exists in the system");
        }

        if (DatabaseHandler.usernameAlreadyExists(username)) {
            reasons.append("\nUsername already exists in the system");
        }

        return reasons.isEmpty();
    }

}
