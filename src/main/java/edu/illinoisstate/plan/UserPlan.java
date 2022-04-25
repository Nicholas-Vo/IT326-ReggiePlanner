package edu.illinoisstate.plan;

import edu.illinoisstate.course.Course;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class UserPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long databaseID;
    private UUID userAccountUUID;
    @ManyToMany
    private List<Course> fallCourses = new ArrayList<>();
    @ManyToMany
    private List<Course> springCourses = new ArrayList<>();
    @ManyToMany
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

}
