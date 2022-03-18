package edu.illinoisstate.database;

import edu.illinoisstate.UserAccount;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class DatabaseHandler {
    private final Map<UUID, UserAccount> database = new HashMap<>(); // todo: replace this with a real DB

    public UserAccount getUserByUsername(String username) {
        return new UserAccount("email", username, "pw");
    }

    /**
     * Get a user Object given a UUID
     * @param uuid the UUID
     * @return the user Object, null if non-existent
     */
    public UserAccount getUser(UUID uuid) {
        return database.get(uuid);
    }

}
