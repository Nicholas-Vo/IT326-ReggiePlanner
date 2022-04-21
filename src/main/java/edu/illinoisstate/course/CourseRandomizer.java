package edu.illinoisstate.course;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CourseRandomizer {
    private List<Course> courses = new ArrayList<>();
    private int courseLevel;

    public CourseRandomizer(List<Course> courses, int courseLevel) {
        this.courseLevel = courseLevel;
        this.courses = courses;
    }

    public List<Course> getCourses(int amount) {
        Random random = new Random();

        for (int i = 0; i < amount; i++) {
            Course course = courses.get(random.nextInt(courses.size()));

            courses.add(course);
        }
        return courses;
    }
}
