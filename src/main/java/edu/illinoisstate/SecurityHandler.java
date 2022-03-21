package edu.illinoisstate;

import edu.illinoisstate.database.DatabaseHandler;

public class SecurityHandler {
    private final ReggiePlanner program = ReggiePlanner.getProgram();
    private final DatabaseHandler database = program.getDatabase();

    /**
     * Validate username/password from database
     * @param username the input username
     * @param password the input password
     * @return bool value
     */
    public boolean validateUsernamePassword(String username, String password) {
        UserAccount account = database.getUserObject(username);

        return password.equalsIgnoreCase("asdf"); // make this actually do something
    }

    public boolean isValidUsername(String username) {
        return username.length() > 3 && username.length() < 16;
    }

    public boolean isValidPassword(String username, String password) {
        if (username.equalsIgnoreCase(password)) {
            return false;
        }

        return password.length() > 3 && password.length() < 16;
    }

    public boolean isValidEmail(String email) {
        return email.length() >= 4 && email.length() <= 16;
    }

    public boolean validateUsername(String username) {
        return true;
    }

}
