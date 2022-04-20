package edu.illinoisstate.plan;

import edu.illinoisstate.UserAccount;
import edu.illinoisstate.course.Course;

import javax.persistence.ElementCollection;
import java.util.List;

public class UserPlan {
    private final UserAccount userAccount;
    @ElementCollection
    private final List<Course> courseList;

    public UserPlan(PlanBuilder builder) {
        userAccount = builder.account;
        courseList = builder.courseList;
    }

    public List<Course> getCourseList() {
        return courseList;
    }

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public static class PlanBuilder {
        private UserAccount account;
        private List<Course> courseList;

        public void setAccount(UserAccount account) {
            this.account = account;
        }

        public void setCourseList(List<Course> list) {
            courseList = list;
        }

        public UserPlan build() {
            return new UserPlan(this);
        }
    }

}
