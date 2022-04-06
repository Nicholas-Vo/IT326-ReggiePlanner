package edu.illinoisstate.course;


import javax.persistence.*;

/**
 * Represents a course at Illinois State University.
 */
@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    private String courseID;
    private String name;
    private double gpaRequirement;
    private double credits;

    /**
     * Creates a new Course with the given name, courseID, and credit hours.
     */
    public Course(String courseID, String name, double credits, double gpaRequirement) {
        this.name = name;
        this.courseID = courseID;
        this.credits = credits;
        this.gpaRequirement = gpaRequirement;
    }

    public Course() {
        // Empty no-param constructor needed for HSQLDB
    }

    /**
     * Gets the Course's name.
     *
     * @return this Student's name.
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the Course's courseID.
     *
     * @return this Course's course ID.
     */
    public String getCourseID() {
        return courseID;
    }

    public void setID(String id) {
        this.courseID = courseID;
    }

    /**
     * Gets the Course's GPA requirement to enroll.
     *
     * @return this Course's GPA requirement.
     */
    public double getGpaReq() {
        return gpaRequirement;
    }

    public void setGpaReq(double req) {
        gpaRequirement = req;
    }

    /**
     * Gets the Course's credit hours.
     *
     * @return this Course's credit hours.
     */
    public double getCredits() {
        return credits;
    }

    public void setCredits(double credits) {
        this.credits = credits;
    }

    /**
     * Get the course in String form
     *
     * @return the course in String form
     */
    public String toString() {
        return id + " - " + name + "\nCredit hours: " + credits;
    }
}
