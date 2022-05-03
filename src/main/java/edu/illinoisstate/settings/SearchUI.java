package edu.illinoisstate.settings;

import edu.illinoisstate.course.Course;
import edu.illinoisstate.database.DatabaseHandler;

import java.util.ArrayList;
import java.util.List;


public class SearchUI {

    List<Course> allCourses = new ArrayList<>(List.copyOf(DatabaseHandler.getCourseList()));


    public void searchClasses() {
        allCourses.isEmpty();
    }

}