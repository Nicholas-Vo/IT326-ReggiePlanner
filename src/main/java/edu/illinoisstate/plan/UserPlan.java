package edu.illinoisstate.plan;

import edu.illinoisstate.UserAccount;
import edu.illinoisstate.course.Course;

import javax.persistence.ElementCollection;
import java.util.List;

public class UserPlan {
    private UserAccount userAccount;
    @ElementCollection
    private final List<Course> fallList;

    public UserPlan() {

    }

    public List<Course> getFallList() {
        return fallList;
    }

    public UserAccount getUserAccount() {
        return userAccount;
    }

}
