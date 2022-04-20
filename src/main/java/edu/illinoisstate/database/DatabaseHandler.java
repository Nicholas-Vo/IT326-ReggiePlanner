package edu.illinoisstate.database;

import edu.illinoisstate.course.Course;
import edu.illinoisstate.utils.Utils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;

public class DatabaseHandler {
    private static Database db;

    public DatabaseHandler() {
        db = new Database();
    }

    public static Database database() {
        return db;
    }

    /**
     * Grab all courses from the courses.txt file, and persist them to database if they don't exist already
     */
    public void loadCoursesFromFile() {
        try {
            // Get course data from courses.txt
            List<String> list = Files.readAllLines(Utils.getFilePath("courses.txt"), StandardCharsets.UTF_8);

            // Create course object and store it in database
            list.forEach(data -> {
                db.save(new Course(data));
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}