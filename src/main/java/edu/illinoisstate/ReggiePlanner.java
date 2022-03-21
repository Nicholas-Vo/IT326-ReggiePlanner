package edu.illinoisstate;

import edu.illinoisstate.database.DatabaseHandler;
import edu.illinoisstate.gui.MainProgramWindow;

/*
Represents the program itself
 */
public class ReggiePlanner {
    public final String VERSION = "1.0.0";
    private final DatabaseHandler database; // The instance of the database
    private static ReggiePlanner program; // The instance of this class
    private static SecurityHandler security;
    private static UserAccount user; // The user signed in to the program

    public ReggiePlanner() {
        database = new DatabaseHandler();
    }

    /**
      Return the single instance of the database
     */
    public DatabaseHandler getDatabase() {
        return database;
    }

    public SecurityHandler getSecurityHandler() {
        return security;
    }

    public UserAccount getUser() {
        return user;
    }

    /**
     * Return the instance of this class
     */
    public static ReggiePlanner getProgram() {
        return program;
    }

    /**
     * Start the program!
     */
    public void execute() {
        MainProgramWindow program = new MainProgramWindow();
        program.execute();
    }

}
