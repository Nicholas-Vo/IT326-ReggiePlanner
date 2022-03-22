package edu.illinoisstate.database;

import edu.illinoisstate.UserAccount;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class DatabaseHandler {
    private static DatabaseHandler database;

    public DatabaseHandler() {
        database = this;
    }

    public static DatabaseHandler getInstance() {
        return database;
    }

    public UserAccount getUserByUsername(String username) {
        return new UserAccount("email", username, "pw");
    }

    private final Map<UUID, UserAccount> map = new HashMap<>();

    /**
     * Get a user Object given a UUID
     * @param uuid the UUID
     * @return the user Object, null if non-existent
     */
    public UserAccount getUser(UUID uuid) {
        return map.get(uuid);
    }

    public UserAccount getUserObject(String username) {
        return map.get(0);
    }

}
