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

    @Transactional
    public void save(UserAccount account) {
        entityManager.getTransaction().begin();

        if (!entityManager.contains(account)) {
            entityManager.persist(account);
            entityManager.flush();
        }

        entityManager.getTransaction().commit();
    }

    public void save(UserPlan plan) {
        thePlan = plan;
    }

    @Transactional
    public void save(Course course) {
        entityManager.getTransaction().begin();

        if (!entityManager.contains(course)) {
            entityManager.persist(course);
            entityManager.flush();
        }

        entityManager.getTransaction().commit();
    }

    /**
     * delete a user account from the database
     *
     * @param account the account to delete
     */
    public void deleteUserAccount(UserAccount account) {
        entityManager.getTransaction().begin();

        entityManager.remove(account);
        System.out.println("Account " + account.getUsername() + " has been deleted.");

        entityManager.getTransaction().commit();
    }

    /**
     * determine if a user exists in the database
     *
     * @param toSearch the user to check
     * @return boolean value
     */
    public boolean containsUser(UserAccount toSearch) {
        Query query = query("FROM UserAccount");

        for (UserAccount aUser : (List<UserAccount>) query.getResultList()) {
            if (aUser.equals(toSearch)) {
                return true;
            }
        }

        return false;
    }

    /**
     * determine if a course exists in the database
     *
     * @param course: the course to check
     * @return boolean value
     */
    public boolean containsCourse(Course course) {
        Query query = query("FROM Course");
        List<Course> courses = query.getResultList();

        for (Course c : courses) {
            if (c.getCourseID().equals(course.getCourseID())) {
                return true;
            }
        }

        return false;
    }

    public boolean containsPlan(UserPlan aPlan) {
        return thePlan != null;
    }

    /**
     * Retrieve a UserAccount from the database; this may return null
     */
    @Nullable
    public UserAccount getUserAccount(String username) {
        Query query = query("FROM UserAccount");

        for (UserAccount aUser : (List<UserAccount>) query.getResultList()) {
            if (aUser.getUsername().equalsIgnoreCase(username)) {
                return aUser;
            }
        }
        return null;
    }

    /**
     * Retrieve a UserAccount from the database; this may return null
     */
    @Nullable
    public UserAccount getUserAccount(UUID uuid) {
        Query query = query("FROM UserAccount");

        for (UserAccount aUser : (List<UserAccount>) query.getResultList()) {
            if (aUser.uuid().equals(uuid)) {
                return aUser;
            }
        }
        return null;
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
        return (List<String>) query("SELECT username FROM UserAccount").getResultList();
    }

    public List<String> getExistingEmailList() {
        return (List<String>) query("SELECT email FROM UserAccount").getResultList();
    }

    public List<Course> getCourseList() {
        return (List<Course>) query("FROM Course").getResultList();
    }

    @Nullable
    public Course getCourseByID(String courseID) {
        List<Course> courses = getCourseList();

        for (Course course : courses) {
            if (course.getCourseID().equalsIgnoreCase(courseID)) {
                return course;
            }
        }
        return null;
    }

    private Query query(String query) {
        return entityManager.createQuery(query);
    }

}