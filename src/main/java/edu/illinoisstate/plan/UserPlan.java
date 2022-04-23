package edu.illinoisstate.plan;

import edu.illinoisstate.UserAccount;
import edu.illinoisstate.course.Course;
import edu.illinoisstate.course.CourseRandomizer;
import edu.illinoisstate.course.Semester;

import javax.persistence.ElementCollection;
import java.util.ArrayList;
import java.util.List;

public class UserPlan {
    private final UserAccount userAccount;
    @ElementCollection
    private List<Course> generatedCourses = new ArrayList<>();
    private final List<Course> excludedCourses = new ArrayList<>();
    private final List<Course> coursePool;

    public UserPlan(List<Course> courseList, UserAccount account) {
        userAccount = account;
        coursePool = courseList;
    }

    public void addCompletedCourse(Course course) {
        excludedCourses.add(course);
    }

    public UserAccount getUserAccount() {
        return userAccount;
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
