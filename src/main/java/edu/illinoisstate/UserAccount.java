package edu.illinoisstate;

import edu.illinoisstate.database.Database;
import edu.illinoisstate.utils.Security;

import javax.persistence.*;
import java.util.UUID;

/**
 * Represents a user account
 */
@Entity
public class UserAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long databaseID;
    private UUID uuid;
    private String username;
    private String passwordHash;
    private String temporaryPasswordHash;
    private String email;
    private boolean isActiveAccount = true;
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

    public UUID uuid() {
        return uuid;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPassword(String passwordHash) {
        this.passwordHash = Security.hash(passwordHash);
        save();
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
        save();
    }

    public void setUsername(String username) {
        this.username = username;
        save();
    }

    public String getUsername() {
        return username;
    }

    public void setEmail(String email) {
        this.email = email;
        save();
    }

    public boolean mustChangePassword() {
        return mustChangePassword;
    }

    public void setForcePasswordChangeValue(boolean newValue) {
        mustChangePassword = newValue;
        save();
    }

    public String email() {
        return email;
    }

    /**
     * Set the user account active or inactive.
     *
     * @param active: boolean value; true for active, false for inactive
     */
    public void setActive(boolean active) {
        isActiveAccount = active;
        save();
    }

    /**
     * Is the account currently active?
     *
     * @return a boolean value: true for active, false for inactive
     */
    public boolean isActiveAccount() {
        return isActiveAccount;
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
        Database.getInstance().saveUserAccount(this);
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
