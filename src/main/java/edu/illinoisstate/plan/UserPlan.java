package edu.illinoisstate.plan;

import com.sun.istack.Nullable;
import edu.illinoisstate.course.Course;
import edu.illinoisstate.database.DatabaseHandler;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserPlan {
    private Long databaseID;
    private UUID userAccountUUID;

    private List<Course> fallCourses = new ArrayList<>();
    private List<Course> springCourses = new ArrayList<>();
    private List<Course> summerCourses = new ArrayList<>();

    public UserPlan(UUID userAccountUUID) {
        this.userAccountUUID = userAccountUUID;
    }

    public UserPlan() {

    }

    public void setFallCourses(List<Course> courses) {
        this.fallCourses = courses;
    }

    public void setSpringCourses(List<Course> courses) {
        this.springCourses = courses;
    }

    public void setSummerCourses(List<Course> courses) {
        this.summerCourses = courses;
    }

    public UUID getUserAccountUUID() {
        return userAccountUUID;
    }

    public void save() {
        DatabaseHandler.savePlan(this);
    }

    public List<Course> getSummerCourses() {
        return summerCourses;
    }

    public List<Course> getSpringCourses() {
        return springCourses;
    }

    public List<Course> getFallCourses() {
        return fallCourses;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        fallCourses.forEach(c -> builder.append(c.getCourseID()).append(": ").append(c.getName()));
        springCourses.forEach(c -> builder.append(c.getCourseID()).append(": ").append(c.getName()));
        summerCourses.forEach(c -> builder.append(c.getCourseID()).append(": ").append(c.getName()));
        return builder.toString();
    }
}
