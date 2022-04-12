package edu.illinoisstate;

import edu.illinoisstate.database.Database;
import edu.illinoisstate.gui.MainProgramWindow;

import java.io.IOException;

/**
 * Authors: Nick Voss
 */
public class ReggiePlanner {
    public static String VERSION = "1.0.0";

    public static void main(String[] args) throws IOException {
        new MainProgramWindow();
        new Database();
    }

}
