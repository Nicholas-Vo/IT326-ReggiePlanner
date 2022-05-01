package edu.illinoisstate.plan;

import com.sun.istack.NotNull;
import edu.illinoisstate.course.Course;
import edu.illinoisstate.database.DatabaseHandler;

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
    @NotNull
    private UUID userAccountUUID;
    @NotNull
    @Transient
    private List<Course> fallCourses = new ArrayList<>();
    @NotNull
    @Transient
    private List<Course> springCourses = new ArrayList<>();
    @NotNull
    @Transient
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

}
