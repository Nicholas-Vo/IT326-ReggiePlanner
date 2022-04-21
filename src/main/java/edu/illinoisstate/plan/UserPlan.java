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
    private List<Course> courses = new ArrayList<>();
    @ElementCollection
    private List<Course> fallCourses = new ArrayList<>();
    @ElementCollection
    private final List<Course> springCourses = new ArrayList<>();
    @ElementCollection
    private final List<Course> summerCourses = new ArrayList<>();

    public UserPlan(UserAccount account) {
        userAccount = account;
    }

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public List<Course> getFallCourses() {
        return fallCourses;
    }

    public void initializeCourseList(List<Course> allCourses) {
        courses = allCourses;
    }

    public String[] getCourseIDs(Semester semester) {
        List<Course> theList = null;

        switch (semester) {
            case FALL -> theList = fallCourses;
            case SPRING -> theList = springCourses;
            case SUMMER -> theList = summerCourses;
        }

        String[] theArray = new String[theList.size()];

        for (int i = 0; i < theList.size(); i++) {
            theArray[i] = theList.get(i).getCourseID();
        }

        return theArray;
    }

    public void generate(Semester semester, int amount) {
        List<Course> theList = null;

        switch (semester) {
            case FALL -> theList = fallCourses;
            case SPRING -> theList = springCourses;
            case SUMMER -> theList = summerCourses;
        }

        var r = new CourseRandomizer(courses, 1);
        fallCourses = r.getCourses(amount);
    }

}
