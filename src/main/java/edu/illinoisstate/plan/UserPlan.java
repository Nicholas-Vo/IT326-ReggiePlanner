package edu.illinoisstate.plan;

import edu.illinoisstate.UserAccount;
import edu.illinoisstate.course.Course;

import java.util.List;

public class UserPlan {
    private final UserAccount user;
    private final List<Course> courseList;

    public UserPlan(UserAccount user, List<Course> courseList) {
        this.user = user;
        this.courseList = courseList;
    }

    public UserAccount getUser() {
        return user;
    }

    public List<Course> getCourseList() {
        return courseList;
    }

}
