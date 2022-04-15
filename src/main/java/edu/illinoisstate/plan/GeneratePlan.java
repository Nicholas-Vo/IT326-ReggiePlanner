package edu.illinoisstate.plan;

import edu.illinoisstate.UserAccount;
import edu.illinoisstate.course.Course;
import edu.illinoisstate.database.Database;

import java.util.ArrayList;
import java.util.List;

public class GeneratePlan {
    private final Database db = Database.getInstance();
    private final List<Course> completedCourses = new ArrayList<>();

    public GeneratePlan(UserAccount user) {
        generate();
    }

    private void generate() {
        Course course = db.getCourseByID("IT279");

        if (course == null) {
            System.out.println("Error, course not found.");
        } else {
            completedCourses.add(course);
            // Send "successfully added course" message
        }

        // Now we have a list of courses the student has taken,
        // use this list to generate a plan
        // ...


    }

}
