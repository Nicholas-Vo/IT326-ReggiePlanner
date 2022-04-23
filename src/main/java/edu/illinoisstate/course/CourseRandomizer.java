package edu.illinoisstate.course;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CourseRandomizer {
    private final List<Course> coursePool;
    private final int courseLevel;

    public CourseRandomizer(List<Course> courses, int courseLevel) {
        this.courseLevel = courseLevel;
        coursePool = courses;

        // Filter out courses that don't match the input course level
        coursePool.removeIf(course -> course.getLevel() != courseLevel);
    }

    public List<Course> getCourses(int amount) {
        Random random = new Random();
        List<Course> generatedCourses = new ArrayList<>();

        for (int i = 0; i < amount; i++) {
            Course course = coursePool.get(random.nextInt(coursePool.size()));

            generatedCourses.add(course);
        }
        return generatedCourses;
    }
}
