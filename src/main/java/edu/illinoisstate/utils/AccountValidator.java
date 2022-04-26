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
        if (username.length() < 4 || username.length() > 16) {
            reasons.append("Invalid username length");
            return false;
        }

        if (email.length() < 4 || email.length() > 24) {
            reasons.append("Invalid email length");
            return false;
        }

        if (!Security.isValidEmail(email)) {
            reasons.append("Invalid email");
            return false;
        }

        if (!Security.isValidPassword(username, password)) {
            reasons.append("Invalid password");
            return false;
        }

        if (DatabaseHandler.emailExistsInSystem(email)) {
            reasons.append("Email already exists in the system");
            return false;
        }

        if (DatabaseHandler.usernameAlreadyExists(username)) {
            reasons.append("Username already exists in the system");
            return false;
        }

        return true;
    }

}
