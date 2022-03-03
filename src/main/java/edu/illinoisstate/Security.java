package edu.illinoisstate;

public class Security {

    public static boolean isValidUsername(String username) {
        return username.length() > 3 && username.length() < 16;
    }

    public static boolean isValidPassword(String username, String password) {
        if (username.equalsIgnoreCase(password)) {
            return false;
        }

        return password.length() > 3 && password.length() < 16;
    }

    public static boolean isValidEmail(String email) {
        return email.length() >= 4 && email.length() <= 16;
    }

    public static boolean validateUsername(String username) {
        return true;
    }

}
