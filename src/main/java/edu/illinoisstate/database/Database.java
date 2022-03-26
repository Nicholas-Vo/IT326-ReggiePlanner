package edu.illinoisstate.database;

import edu.illinoisstate.UserAccount;
import edu.illinoisstate.course.Course;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import static org.hsqldb.DatabaseManager.getSession;

/**
 * This will eventually be the database class
 */
public class Database {
    private static Database database;
    private final Set<Course> courseList = new HashSet<>();
    private final EntityManager entityManager;

    public Database() {
        database = this;

        var factory = Persistence.createEntityManagerFactory("default");
        entityManager = factory.createEntityManager();

        entityManager.getTransaction().begin();

        courseList.add(new Course("IT297", "Data Structures & Algorithms", 3, 3));
        courseList.add(new Course("IT355", "Secure Software Development", 3, 3));
        courseList.add(new Course("IT327", "Concepts Of Programming Languages", 3, 3));
        courseList.add(new Course("IT388", "Introduction To Parallel Processing", 3, 3));
        courseList.add(new Course("IT340", "Introduction To Artificial Intelligence", 3, 3));
        courseList.add(new Course("IT168", "Structured Problem Solving Using The Computer", 4, 3));

        /*
        Only add the course to the database if it doesn't already exist
         */
        for (Course course : courseList) {
            if (!containsCourse(course)) {
                entityManager.persist(course);
            }
        }

        entityManager.getTransaction().commit();
    }

    public static Database getInstance() {
        return database;
    }

    private boolean containsCourse(Course course) {
        Query query = entityManager.createQuery("from Course");
        List<Course> courses = query.getResultList();

        for (Course c : courses) {
            if (c.getCourseID().equals(course.getCourseID())) {
                return true;
            }
        }

        return false;
    }

    public UserAccount getUser(String username) {
        return new UserAccount(UUID.randomUUID(),"", "", "");
    }

    public List<Course> getCourseList() {
        return null;
    }

}
