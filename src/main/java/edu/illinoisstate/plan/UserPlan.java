package edu.illinoisstate.plan;

import edu.illinoisstate.UserAccount;
import edu.illinoisstate.course.Course;
import edu.illinoisstate.database.Database;

import java.util.List;

public class UserPlan {
    private final UserAccount user;
    private final List<Course> courseList;

    public UserPlan(Database database) {
        this.user = database.getUser();
        this.courseList = database.getCourseList();
    }

    public UserAccount getUser() {
        return user;
    }

    public List<Course> getCourseList() {
        return courseList;
    }
}
