package edu.illinoisstate.course;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CourseRandomizer {
    private final List<Course> coursePool;
    private final List<Course> excludedCourses;

    public CourseRandomizer(List<Course> coursePool, List<Course> excludedCourses) {
        this.coursePool = coursePool;
        this.excludedCourses = excludedCourses;
    }

    public List<Course> getCourses(int amount, int courseLevel) {
        Random random = new Random();

        List<Course> generatedCourses = new ArrayList<>();

        // Filter out courses that don't match the input course level
        List<Course> allCourses = new ArrayList<>(List.copyOf(coursePool));
        allCourses.removeIf(course -> course.getLevel() != courseLevel);

        for (int i = 0; i < amount; i++) {
            Course course = allCourses.get(random.nextInt(allCourses.size()));

            // Don't allow for repeat courses in the generated list
            if (excludedCourses.contains(course) || generatedCourses.contains(course)) {
                i--;
                continue;
            }

            generatedCourses.add(course);
        }
        return generatedCourses;
    }
}
