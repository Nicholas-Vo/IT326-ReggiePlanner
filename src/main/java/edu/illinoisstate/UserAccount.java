package edu.illinoisstate;

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
    private String email;
    private boolean isActiveAccount = true;

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

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
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

    /**
     * Print out the student as a string representation
     * @return a String representation
     */
    public String toString() {
        return "UUID: " + uuid + "\nUsername: " + username + "\nEmail: " + email;
    }

    /**
     * Determine if one UserAccount is equal to another by checking UUID.
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
