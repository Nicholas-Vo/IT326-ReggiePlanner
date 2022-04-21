package edu.illinoisstate;

import edu.illinoisstate.course.CourseHandler;
import edu.illinoisstate.database.DatabaseHandler;
import edu.illinoisstate.gui.MainProgramWindow;

/**
 * Authors: Nick Voss
 */
public class ReggiePlanner {

    public static void main(String[] args) {
        new MainProgramWindow();
        var database = new DatabaseHandler();

        database.loadCoursesFromFile();

        new CourseHandler();
    }

}
