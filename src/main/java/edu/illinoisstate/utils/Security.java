package edu.illinoisstate.utils;

import edu.illinoisstate.UserAccount;
import edu.illinoisstate.database.DatabaseHandler;
import org.apache.commons.lang3.RandomStringUtils;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Security {

    public static boolean isValidEmail(String email) {
        return email.contains("@");
    }

    public static boolean isValidPassword(String username, String input) {
        return verifyPassword(username, input);
    }

    public static boolean isValidPassword(UserAccount user, String input) {
        return verifyPassword(user.getUsername(), input);
    }

    /**
     * Determine whether to reject input password
     *
     * @param username: the username
     * @param input:    the input password
     * @return bool value
     */
    public static boolean verifyPassword(String username, String input) {
        /*
        don't allow the use of username as password
         */
        if (username.equalsIgnoreCase(input)) {
            return false;
        }

        if (DatabaseHandler.dbContainsUsername(username)) {
            UserAccount user = DatabaseHandler.getUser(username);
            String currentPW = hash(user.getPasswordHash());

            if (user.getTemporaryPasswordHash() != null) {
                String hashedInput = hash(input);
                String tempPW = hash(user.getTemporaryPasswordHash());

                if (hashedInput.equalsIgnoreCase(tempPW)) {
                    return false;
                }
            }

        /*
        don't allow new password to be similar to old password
         */
            String hashedInput = hash(input);
            if (hashedInput.equalsIgnoreCase(currentPW)) {
                return false;
            }
        }

        return input.length() > 3 && input.length() < 16;
    }

    /**
     * Generate a random password String
     *
     * @return a String password
     */
    public static String generateRandomPassword(UserAccount user) {
        String tempPassword = RandomStringUtils.random(16);
        user.setTempPassword(tempPassword); // Save temp PW

        return tempPassword; // Return PW as String for email
    }

    /**
     * Hash a given string
     *
     * @param theString: The string to hash
     * @return a hashed String
     */
    public static String hash(String theString) {
        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        Charset charSet = StandardCharsets.UTF_8;
        return new String(digest.digest(theString.getBytes(charSet)), charSet);
    }

}
