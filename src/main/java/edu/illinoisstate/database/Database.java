package edu.illinoisstate.database;

import com.sun.istack.Nullable;
import edu.illinoisstate.UserAccount;
import edu.illinoisstate.course.Course;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

/**
 * All database logic goes here
 */
@SuppressWarnings("unchecked") // suppress unchecked cast warnings
public class Database {
    private static Database database; // the single instance of this class
    private final EntityManager entityManager; // the database EntityManager

    public Database() {
        database = this;

        var factory = Persistence.createEntityManagerFactory("default");
        entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();

        List<Course> list = new ArrayList<>();
        list.add(new Course("IT297", "Data Structures & Algorithms", 3, 3));
        list.add(new Course("IT355", "Secure Software Development", 3, 3));
        list.add(new Course("IT327", "Concepts Of Programming Languages", 3, 3));
        list.add(new Course("IT388", "Introduction To Parallel Processing", 3, 3));
        list.add(new Course("IT340", "Introduction To Artificial Intelligence", 3, 3));
        list.add(new Course("IT168", "Structured Problem Solving Using The Computer", 4, 3));

        /*
        Only add the course to the database if it doesn't already exist
         */
        list.forEach(course -> {
            if (!containsCourse(course)) {
                entityManager.persist(course);
            }
        });

        entityManager.getTransaction().commit();
    }

    /**
     * obtain an instance of the Database singleton class
     * @return the class instance
     */
    public static Database getInstance() {
        return database;
    }

    /**
     * save a user account to the database
     * @param account the account to save
     */
    public void saveUserAccount(UserAccount account) {
        entityManager.getTransaction().begin();

        if (containsUser(account)) {
            entityManager.merge(account); // merge() updates existing record
        } else {
            entityManager.persist(account); // add new record
        }

        entityManager.getTransaction().commit();
    }

    /**
     * delete a user account from the database
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
     * @param toSearch the user to check
     * @return boolean value
     */
    public boolean containsUser(UserAccount toSearch) {
        Query query = entityManager.createQuery("FROM UserAccount");

        for (UserAccount aUser : (List<UserAccount>) query.getResultList()) {
            if (aUser.equals(toSearch)) {
                return true;
            }
        }

        return false;
    }

    /**
     * determine if a course exists in the database
     * @param course: the course to check
     * @return boolean value
     */
    public boolean containsCourse(Course course) {
        Query query = entityManager.createQuery("FROM Course");
        List<Course> courses = query.getResultList();

        for (Course c : courses) {
            if (c.getCourseID().equals(course.getCourseID())) {
                return true;
            }
        }

        return false;
    }

    /**
     * Retrieve a UserAccount from the database; this may return null
     */
    @Nullable
    public UserAccount getUserAccount(String username) {
        Query query = entityManager.createQuery("FROM UserAccount");

        for (UserAccount aUser : (List<UserAccount>) query.getResultList()) {
            if (aUser.getUsername().equalsIgnoreCase(username)) {
                return aUser;
            }
        }
        return null;
    }

    public List<String> getUsernamesList() {
        Query query = entityManager.createQuery("SELECT username FROM UserAccount");

        return (List<String>) query.getResultList();
    }

    public List<String> getExistingEmailList() {
        Query query = entityManager.createQuery("SELECT email FROM UserAccount");

        return (List<String>) query.getResultList();
    }

    public List<Course> getCourseList() {
        Query query = entityManager.createQuery("FROM Course");

        return (List<Course>) query.getResultList();
    }

}
