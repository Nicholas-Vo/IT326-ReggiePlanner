package edu.illinoisstate.course;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CourseRandomizer {
    private final List<Course> coursePool; // All the courses from the database
    private final List<Course> excludedCourses = new ArrayList<>(); // Courses that shouldn't be included in the generation

    public CourseRandomizer(List<Course> coursePool) {
        this.coursePool = coursePool;
    }

    public void exclude(Course course) {
        excludedCourses.add(course);
    }

    public List<Course> generate(int amount, int courseLevel) {
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
