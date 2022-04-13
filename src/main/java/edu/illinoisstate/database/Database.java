package edu.illinoisstate.database;

import com.sun.istack.Nullable;
import edu.illinoisstate.UserAccount;
import edu.illinoisstate.course.Course;
import edu.illinoisstate.utils.Utils;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

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

        try {
            // Get course data from courses.txt
            List<String> list = Files.readAllLines(Utils.getFilePath("courses.txt"), StandardCharsets.UTF_8);

            // Create course object and store it in database, if it doesn't already exist
            list.forEach(data -> {
                Course course = new Course(data);

                if (!containsCourse(course)) {
                    entityManager.persist(course);
                    System.out.println("Saving new course " + course.getCourseID() + " to database.");
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        entityManager.getTransaction().commit();
    }


    /**
     * obtain an instance of the Database singleton class
     *
     * @return the class instance
     */
    public static Database getInstance() {
        return database;
    }

    /**
     * save a user account to the database
     *
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

    private Query query(String query) {
        return entityManager.createQuery(query);
    }

}