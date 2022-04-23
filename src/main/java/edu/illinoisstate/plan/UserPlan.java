package edu.illinoisstate.plan;

import com.sun.istack.Nullable;
import edu.illinoisstate.course.Course;
import edu.illinoisstate.course.CourseRandomizer;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class UserPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long databaseID;
    private final UUID userAccountUUID;
    @ManyToMany
    private List<Course> generatedCourses = new ArrayList<>();
    @ManyToMany
    private final List<Course> excludedCourses = new ArrayList<>();
    @ManyToMany
    private final List<Course> coursePool;

    public UserPlan(List<Course> courseList, UUID userAccountUUID) {
        this.userAccountUUID = userAccountUUID;
        coursePool = courseList;
    }

    public UserPlan() {
        this.coursePool = null;
        userAccountUUID = null;
    }

    public void addCompletedCourse(Course course) {
        excludedCourses.add(course);
    }

    public UUID getUserAccountUUID() {
        return userAccountUUID;
    }

    public String[] getCourseIDs() {
        String[] theArray = new String[generatedCourses.size()];

        for (int i = 0; i < generatedCourses.size(); i++) {
            theArray[i] = generatedCourses.get(i).getCourseID();
        }

        return theArray;
    }

    public void generate(int level, int amount) {
        var randomizer = new CourseRandomizer(coursePool, excludedCourses);
        generatedCourses = randomizer.getCourses(amount, level);
    }

}
