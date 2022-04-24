package edu.illinoisstate;

import edu.illinoisstate.course.CourseHandler;
import edu.illinoisstate.database.DatabaseHandler;
import edu.illinoisstate.gui.MainProgramWindow;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * Authors: Nick Voss
 */
public class ReggiePlanner {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        } catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        new MainProgramWindow();
        DatabaseHandler database = new DatabaseHandler();
        database.loadCoursesFromFile();

        new CourseHandler();
    }

}
