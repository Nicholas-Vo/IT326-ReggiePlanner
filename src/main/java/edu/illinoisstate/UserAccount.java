package edu.illinoisstate;

import java.util.UUID;

/**
 * Represents a user account
 */
public class UserAccount {
    private final UUID uuid;
    private String username;
    private String email;
    private String password;
    private boolean isActiveAccount;

    public UserAccount(String email, String username, String password) {
        this.uuid = UUID.randomUUID();
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public UUID uuid() {
        return uuid;
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

    public String email() {
        return email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String password() {
        return password;
    }

    /**
     * Set the user account active or inactive.
     * @param active: boolean value; true for active, false for inactive
     */
    public void setActive(boolean active) {
        isActiveAccount = active;
    }

    /**
     * Is the account currently active?
     * @return a boolean value: true for active, false for inactive
     */
    public boolean isActiveAccount() {
        return isActiveAccount;
    }

    public String toString() {
        return "UUID: " + uuid + "\nUsername: " + username + "\nEmail: " + email;
    }

}
