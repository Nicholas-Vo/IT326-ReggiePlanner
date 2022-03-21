package edu.illinoisstate;

import edu.illinoisstate.database.DatabaseHandler;
import edu.illinoisstate.gui.MainProgramWindow;
import edu.illinoisstate.gui.ProgramWindow;

/*
Represents the program itself
 */
public class ReggiePlanner {
    public final String VERSION = "1.0.0";
    private final DatabaseHandler database;

    public ReggiePlanner() {
        database = new DatabaseHandler();
    }

    /**
      Return the single instance of the database
     */
    public DatabaseHandler getDatabase() {
        return database;
    }

    /**
     * Start the program!
     */
    public void execute() {
        ProgramWindow mainWindow = new MainProgramWindow(this);
        mainWindow.execute();
    }

}
