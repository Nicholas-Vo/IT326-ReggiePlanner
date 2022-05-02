package edu.illinoisstate;

import edu.illinoisstate.database.DatabaseHandler;
import edu.illinoisstate.utils.Security;
import javax.persistence.*;

import java.util.UUID;

/**
 * Represents a user account
 */
@Entity
public class UserAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long databaseID;
    private UUID uuid;
    private long planID;
    private String username;
    private String passwordHash;
    private String temporaryPasswordHash;
    private String email;
    private String userNote;
    private boolean mustChangePassword = false;

    public UserAccount(UUID uuid, String email, String username, String passwordHash) {
        this.uuid = uuid;
        this.email = email;
        this.username = username;
        this.passwordHash = passwordHash;
    }

    public UserAccount() {
        // Empty no-param constructor needed for HSQLDB
    }

    public void setPlanID(long id) {
        this.planID = id;
    }

    public long getPlanID() {
        return planID;
    }

    public void setUserNote(String note) {
        userNote = note;
    }

    public String getUserNote() {
        return userNote;
    }

    public UUID uuid() {
        return uuid;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPassword(String passwordHash) {
        this.passwordHash = Security.hash(passwordHash);
    }

    public void setUUID(UUID uuid) {
        this.uuid = uuid;
    }

    public String getTemporaryPasswordHash() {
        return temporaryPasswordHash;
    }

    /**
     * Set a temp password to be hashed. If input is null, temp. pw is set to null
     * @param input: String input to be hashed, or null
     */
    public void setTempPassword(String input) {
        /*
        If the input is null, set temp. hash to null - otherwise hash it
         */
        temporaryPasswordHash = (input == null) ? null : Security.hash(input);
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean mustChangePassword() {
        return mustChangePassword;
    }

    public void setForcePasswordChangeValue(boolean newValue) {
        mustChangePassword = newValue;
    }

    public String email() {
        return email;
    }

    /**
     * Print out the student as a string representation
     *
     * @return a String representation
     */
    public String toString() {
        return "UUID: " + uuid + "\nUsername: " + username + "\nEmail: " + email;
    }

    /**
     * Authenticate user password
     *
     * @param passwordInput: the password the user entered
     * @return boolean value
     */
    public boolean authenticate(String passwordInput) {
        String generatedHash = Security.hash(passwordInput); // Hash user input to check against DB

        if (temporaryPasswordHash != null) {
            mustChangePassword = true; // Force user to change PW at next login
            return temporaryPasswordHash.equalsIgnoreCase(generatedHash);

        }

        return passwordHash.equalsIgnoreCase(generatedHash);
    }

    /**
     * Save all user data to database
     */
    public void save() {
        DatabaseHandler.saveAccount(this);
    }

    /**
     * Determine if one UserAccount is equal to another by checking UUID.
     *
     * @param otherUser the user to compare
     * @return boolean value
     */
    @Override
    public boolean equals(Object otherUser) {
        // If the Object isn't a UserAccount, the answer is no
        if (!(otherUser instanceof UserAccount account)) {
            return false;
        }

        return account.uuid.equals(uuid);
    }

}
