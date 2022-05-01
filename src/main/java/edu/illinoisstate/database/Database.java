package edu.illinoisstate.database;

import edu.illinoisstate.UserAccount;
import edu.illinoisstate.course.Course;
import edu.illinoisstate.plan.UserPlan;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;
import java.util.UUID;

/**
 * The database implementation
 */
@SuppressWarnings("unchecked")
public class Database {
    private final EntityManager entityManager;
    private static Database instance;

    private Database() {
        var factory = Persistence.createEntityManagerFactory("default2");

        entityManager = factory.createEntityManager();
    }

    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    public void save(UserAccount acc) {
        entityManager.getTransaction().begin();

        if (containsUser(acc)) {
            entityManager.merge(acc); // merge() updates existing record
        } else {
            entityManager.persist(acc); // add new record
        }

        entityManager.getTransaction().commit();
    }

    public void save(UserPlan aPlan) {
        entityManager.getTransaction().begin();

        if (containsPlan(aPlan)) {
            entityManager.merge(aPlan);
        } else {
            entityManager.persist(aPlan);
        }

        entityManager.getTransaction().commit();
    }

    public void save(Course course) {
        entityManager.getTransaction().begin();

        if (containsCourse(course)) {
            entityManager.merge(course);
        } else {
            entityManager.persist(course);
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
        Query query = query("FROM UserPlan");

        for (UserPlan plan : (List<UserPlan>) query.getResultList()) {
            if (plan.equals(aPlan)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Retrieve a UserAccount from the database; this may return null
     */
    public UserAccount getUserAccount(String username) {
        Query query = query("FROM UserAccount");

        for (UserAccount aUser : (List<UserAccount>) query.getResultList()) {
            if (aUser.getUsername().equalsIgnoreCase(username)) {
                return aUser;
            }
        }
        return null;
    }

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
    public UserPlan getPlanByID(UUID uuid) {
        Query query = query("FROM UserPlan");

        for (UserPlan plan : (List<UserPlan>) query.getResultList()) {
            if (plan.getUserAccountUUID().equals(uuid)) {
                return plan;
            }
        }
        return null;
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