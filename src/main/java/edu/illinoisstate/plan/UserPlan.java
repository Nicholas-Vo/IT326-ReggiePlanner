package edu.illinoisstate.plan;

import edu.illinoisstate.course.Course;
import edu.illinoisstate.database.Database;

import java.util.List;

public class UserPlan {
    private final List<Course> courseList;

    public UserPlan() {
        this.courseList = Database.getInstance().getCourseList();
    }

    public List<Course> getCourseList() {
        return courseList;
    }
}
