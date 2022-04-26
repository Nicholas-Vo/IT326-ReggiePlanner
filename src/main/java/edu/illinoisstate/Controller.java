package edu.illinoisstate;

import edu.illinoisstate.course.Course;
import edu.illinoisstate.plan.UserPlan;
import edu.illinoisstate.utils.AccountValidator;
import edu.illinoisstate.utils.Security;

import java.util.List;
import java.util.UUID;

public class Controller {

    public static boolean createPlan(UUID userID, List<Course> fall, List<Course> spring, List<Course> summer) {
        UserPlan plan = new UserPlan(userID);
        plan.setFallCourses(fall);
        plan.setSpringCourses(spring);
        plan.setSummerCourses(summer);
        plan.save();
        return true;
    }

    public static boolean createAccount(String email, String username, String password) {
        AccountValidator validator = new AccountValidator();

        if (!validator.check(email, username, password)) {
            return false;
        }

        UserAccount account = new UserAccount();
        account.setUUID(UUID.randomUUID());
        account.setEmail(email);
        account.setUsername(username);
        account.setPassword(Security.hash(password));
        account.save();
        return true;
    }

}
