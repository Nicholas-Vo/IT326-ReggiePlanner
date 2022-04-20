package edu.illinoisstate;

import edu.illinoisstate.database.DatabaseHandler;
import edu.illinoisstate.gui.MainProgramWindow;

/**
 * Authors: Nick Voss
 */
public class ReggiePlanner {
    public static String VERSION = "1.0.0";

    public static void main(String[] args) {
        new MainProgramWindow();
        var database = new DatabaseHandler();

        database.loadCoursesFromFile();
    }

}
