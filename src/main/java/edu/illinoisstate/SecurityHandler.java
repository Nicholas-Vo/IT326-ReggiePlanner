package edu.illinoisstate;

import edu.illinoisstate.database.Database;

public class SecurityHandler {
    private static SecurityHandler securityHandler;

    public SecurityHandler() {
        securityHandler = this;
    }

    public static SecurityHandler getInstanace() {
        return securityHandler;
    }

    /**
     * Validate username/password from database
     * @param username the input username
     * @param password the input password
     * @return bool value
     */
    public boolean validateUsernamePassword(String username, String password) {
        UserAccount account = Database.getInstance().getUser(username);

        // do validation here

        return password.equalsIgnoreCase("test"); // make this actually do something
    }

    public boolean isValidEmail(String email) {
        return email.length() >= 4 && email.length() <= 16;
    }

    public boolean validateUsername(String username) {
        return true;
    }

}
