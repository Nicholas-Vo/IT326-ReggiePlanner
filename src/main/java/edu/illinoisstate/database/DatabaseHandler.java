package edu.illinoisstate.database;

import edu.illinoisstate.UserAccount;
import edu.illinoisstate.course.Course;
import edu.illinoisstate.plan.UserPlan;
import edu.illinoisstate.utils.Utils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;

public class DatabaseHandler {
    /**
     * Grab all courses from the courses.txt file, and persist them to database if they don't exist already
     */
    public void loadCoursesFromFile() {
        try {
            // Get course data from courses.txt
            List<String> list = Files.readAllLines(Utils.getFilePath("courses.txt"), StandardCharsets.UTF_8);

            // Create course object and store it in database
            list.forEach(data -> Database.getInstance().save(new Course(data)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Course> getCourseList() { return Database.getInstance().getCourseList(); }

    public static void saveAccount(UserAccount account) {
        Database.getInstance().save(account);
    }

    public static void savePlan(UserPlan plan) {
        Database.getInstance().save(plan);
    }

    public static UserPlan getUserPlan(UserAccount user) {
        return Database.getInstance().getPlanByID(user.uuid());
    }

    public static boolean usernameAlreadyExists(String username) {
        return Database.getInstance().getUsernamesList().contains(username);
    }

    public static boolean emailExistsInSystem(String email) {
        return Database.getInstance().getExistingEmailList().contains(email.toLowerCase());
    }
}
