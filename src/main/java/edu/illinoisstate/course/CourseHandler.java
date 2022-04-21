package edu.illinoisstate.course;

import edu.illinoisstate.database.DatabaseHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CourseHandler {
    private static CourseHandler handler;
    private final List<Course> fallCourses = new ArrayList<>();
    private final List<Course> springCourses = new ArrayList<>();
    private final List<Course> summerCourses = new ArrayList<>();

    private final Random random = new Random();

    public CourseHandler() {
        handler = this;

        DatabaseHandler.database().getCourseList().forEach(course -> {
            if (course.getCourseID().charAt(2) == '1') {
                fallCourses.add(course);
            } else if (course.getCourseID().charAt(2) == '2') {
                springCourses.add(course);
            } else if (course.getCourseID().charAt(2) == '3') {
                summerCourses.add(course);
            }
        });
    }

    public static CourseHandler instance() {
        return handler;
    }

    public List<Course> getRandom100Courses() {
        List<Course> courses = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            courses.add(fallCourses.get(random.nextInt(fallCourses.size())));
        }

        return courses;
    }

    public List<Course> get200Courses() {
        return springCourses;
    }

    public List<Course> get300Courses() {
        return summerCourses;
    }
}
