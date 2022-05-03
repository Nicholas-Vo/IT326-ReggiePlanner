package edu.illinoisstate.database;

import com.sun.istack.Nullable;
import edu.illinoisstate.UserAccount;
import edu.illinoisstate.course.Course;
import edu.illinoisstate.plan.UserPlan;
import org.hibernate.Session;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * All database logic goes here
 */
@SuppressWarnings("unchecked") // suppress unchecked cast warnings
public class Database {
    private final EntityManager entityManager; // the database EntityManager
    private static Database instance;
    private UserPlan thePlan = null;

    private Database() {
        var factory = Persistence.createEntityManagerFactory("default");

        entityManager = factory.createEntityManager();
    }

    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    private UserAccount account = null;

    public void save(UserAccount account) {
        this.account = account;
    }

    public void save(UserPlan plan) {
        thePlan = plan;
    }

    private final List<Course> courses = new ArrayList<>();

    public void save(Course course) {
        courses.add(course);
    }

    /**
     * delete a user account from the database
     *
     * @param account the account to delete
     */
    public void deleteUserAccount(UserAccount account) {
        account = null;
    }

    /**
     * determine if a user exists in the database
     *
     * @param toSearch the user to check
     * @return boolean value
     */
    public boolean containsUser(UserAccount toSearch) {
        return account.getUsername().equals(toSearch.getUsername());
    }

    /**
     * determine if a course exists in the database
     *
     * @param course: the course to check
     * @return boolean value
     */
    public boolean containsCourse(Course course) {
        return courses.contains(course);
    }

    public boolean containsPlan(UserPlan aPlan) {
        return thePlan != null;
    }

    /**
     * Retrieve a UserAccount from the database; this may return null
     */
    @Nullable
    public UserAccount getUserAccount(String username) {
        return account;
    }

    /**
     * Retrieve a UserAccount from the database; this may return null
     */
    @Nullable
    public UserAccount getUserAccount(UUID uuid) {
        return account;
    }

    /**
     * Retrieve a UserAccount from the database; this may return null
     */
    @Nullable
    public UserPlan getPlanByID(UUID uuid) {
        return thePlan;
    }

    /*
    Query methods
     */

    public List<String> getUsernamesList() {
        return List.of(account.getUsername());
    }

    public List<String> getExistingEmailList() {
        return List.of(account.email());
    }

    public List<Course> getCourseList() {
        return courses;
    }

    @Nullable
    public Course getCourseByID(String courseID) {
        for (Course course : courses) {
            if (course.getCourseID().equalsIgnoreCase(courseID)) {
                return course;
            }
        }
        return null;
    }

}