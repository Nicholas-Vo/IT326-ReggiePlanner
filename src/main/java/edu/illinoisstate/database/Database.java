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
        list.add(new Course("IT140", "Interacting in a Digital World", 3, 3));
        list.add(new Course("IT150", "Using Microcomputer Productivity Tools", 3, 3));
        list.add(new Course("IT164", "Introduction To Problem Solving Using the Computer", 3, 3));
        list.add(new Course("IT165", "Computer Programming For Scientists", 4, 3));
        list.add(new Course("IT166", "Python Programming For Science and Data Analysis", 4, 3));
        list.add(new Course("IT168", "Structured Problem Solving Using The Computer", 4, 3));
        list.add(new Course("IT170", "Scripting Languages and Automation", 3, 3));
        list.add(new Course("IT175", "Python for Networking and Security", 3, 3));
        list.add(new Course("IT178", "Computer Application Programming", 3, 3));
        list.add(new Course("IT179", "Intro to Data Structures", 3, 3));
        list.add(new Course("IT180", "C++ Programming", 1, 3));
        list.add(new Course("IT191", "Intro to IT Professional Practice", 1, 3));

        list.add(new Course("IT214", "Social, Legal, and Ethical Issues In Information Tech", 3, 3));
        list.add(new Course("IT225", "Computer Organization", 3, 3));
        list.add(new Course("IT226", "Advanced Practical Application Development", 3, 3));
        list.add(new Course("IT244", "Introduction To Business Intelligence", 3, 3));
        list.add(new Course("IT250", "Fundamentals of Information Assurance and Security", 3, 3));
        list.add(new Course("IT254", "Hardware and Software Concepts", 3, 3));
        list.add(new Course("IT256", "Linux Administration Fundamentals", 3, 3));
        list.add(new Course("IT261", "System Development", 3, 3));
        list.add(new Course("IT262", "Information Technology Project Management", 3, 3));
        list.add(new Course("IT272", "Cobol As A Second Language", 4, 3));
        list.add(new Course("IT275", "Java As A Second Language", 4, 3));
        list.add(new Course("IT279", "Algorithms and Data Structures", 3, 3));
        list.add(new Course("IT287", "Independent Studies", 1, 3)); //1-6 credits
        list.add(new Course("IT291", "Undergraduate Teaching Experiences In Information Technology", 3, 3)); //1-3 credits


        list.add(new Course("IT310", "Cloud Networking", 3, 3));
        list.add(new Course("IT311", "Internet of Things", 3, 3));
        list.add(new Course("IT326", "Principles of Software Engineering", 3, 3));
        list.add(new Course("IT327", "Concepts Of Programming Languages", 3, 3));
        list.add(new Course("IT328", "Introduction To The Theory of Computation", 3, 3));
        list.add(new Course("IT329", "Compiler Design", 3, 3));
        list.add(new Course("IT330", "Introduction To Enterprise Computer Systems", 3, 3));
        list.add(new Course("IT340", "Intro to Artificial Intelligence", 3, 3));
        list.add(new Course("IT341", "Object Oriented System Development", 3, 3));
        list.add(new Course("IT343", "Information Retrieval and Search Engines", 3, 3));
        list.add(new Course("IT344", "Applied Data Mining", 3, 3));
        list.add(new Course("IT348", "Intro to Machine Learning", 3, 3));
        list.add(new Course("IT350", "Operating Systems Concepts and Admin", 3, 3));
        list.add(new Course("IT351", "Practical Cryptography and Trusted Systems", 3, 3));
        list.add(new Course("IT352", "Data and Information Visualization", 3, 3));
        list.add(new Course("IT353", "Web Development Technologies", 3, 3));
        list.add(new Course("IT354", "Advanced Web Application Development", 3, 3));
        list.add(new Course("IT355", "Secure Software Development", 3, 3));
        list.add(new Course("IT356", "Introduction to Computer Graphics", 3, 3));
        list.add(new Course("IT357", "Tools and Techniques in Defensive Security", 3, 3));
        list.add(new Course("IT358", "Mobile and Cloud Computing", 3, 3));
        list.add(new Course("IT359", "Tools And Techniques in Penetration Testing", 3, 3));
        list.add(new Course("IT360", "Security Incident And Event Management And Forensics", 3, 3));
        list.add(new Course("IT363", "Systems Development II", 4, 3));
        list.add(new Course("IT367", "Designing The User Interface", 3, 3));
        list.add(new Course("IT368", "Topics In Information Systems", 3, 3));
        list.add(new Course("IT369", "Topics In Cybersecurity", 3, 3));
        list.add(new Course("IT370", "Server Management", 3, 3));
        list.add(new Course("IT372", "External Data Structures", 3, 3));
        list.add(new Course("IT373", "Wide Area Network Infrastructure", 3, 3));
        list.add(new Course("IT379", "Advanced Computer Networks", 3, 3));
        list.add(new Course("IT380", "Wireless Communication Systems", 3, 3));
        list.add(new Course("IT381", "Network Design and Analysis", 3, 3));
        list.add(new Course("IT382", "Distributed Systems", 3, 3));
        list.add(new Course("IT383", "Principles Of Operating Systems", 3, 3));
        list.add(new Course("IT385", "Topics In Computer Science", 3, 3));
        list.add(new Course("IT388", "Introduction To Parallel Processing", 3, 3));
        list.add(new Course("IT391", "Directed Project In Information Systems", 3, 3));
        list.add(new Course("IT392", "Enterprise Systems and Integration and Application Development", 3, 3));
        list.add(new Course("IT398", "Professional Practice", 6, 3)); //1-6 credits


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
    //hey

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
