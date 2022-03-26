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
    private String email;
    private boolean isActiveAccount;

    public UserAccount(UUID uuid, String email, String username, String password) {
        this.uuid = uuid;
        this.email = email;
        this.username = username;
    }

    public UserAccount() {

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

    @Override
    public boolean equals(Object otherUser) {
        if (!(otherUser instanceof UserAccount)) {
            return false;
        }

        UserAccount account = (UserAccount) otherUser;

        return account.uuid.equals(uuid);
    }

}
