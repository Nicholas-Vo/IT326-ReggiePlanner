package edu.illinoisstate;

import edu.illinoisstate.database.DatabaseHandler;

public class SecurityHandler {
    private final DatabaseHandler database;

    public SecurityHandler(DatabaseHandler database) {
        this.database = database;
    }

    /**
     * Validate username/password from database
     * @param username the input username
     * @param password the input password
     * @return bool value
     */
    public boolean validateUsernamePassword(String username, String password) {
        UserAccount account = database.getUserObject(username);

        // do validation here

        return password.equalsIgnoreCase("asdf"); // make this actually do something
    }

    public boolean isValidEmail(String email) {
        return email.length() >= 4 && email.length() <= 16;
    }

    public boolean validateUsername(String username) {
        return true;
    }

}
