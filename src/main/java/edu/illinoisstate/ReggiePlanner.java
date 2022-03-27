package edu.illinoisstate;

import edu.illinoisstate.database.Database;
import edu.illinoisstate.gui.MainProgramWindow;

public class ReggiePlanner {
    public static String VERSION = "1.0.0";

    public static void main(String[] args) {
        new MainProgramWindow();
        new Database();
    }

}
