package edu.illinoisstate;

import edu.illinoisstate.course.Course;
import edu.illinoisstate.plan.UserPlan;

import java.util.List;
import java.util.UUID;

public class Controller {

    public static void createPlan(UUID userID, List<Course> fall, List<Course> spring, List<Course> summer) {
        UserPlan plan = new UserPlan(userID);
        plan.setFallCourses(fall);
        plan.setSpringCourses(spring);
        plan.setSummerCourses(summer);
        plan.save();
    }

}
