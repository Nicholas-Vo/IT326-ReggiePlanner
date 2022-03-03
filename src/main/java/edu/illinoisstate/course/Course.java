package edu.illinoisstate.course;


/**
 * Represents a course at Illinois State University.
 */
public class Course {
    private String id;
    private String name;
    private double gpaRequirement;
    private double credits;

    /**
     * Creates a new Course with the given name, courseID, and credit hours.
     */
    public Course(String name, String id, double credits, double gpaRequirement) {
        this.name = name;
        this.id = id;
        this.credits = credits;
        this.gpaRequirement = gpaRequirement;
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
    public String getID() {
        return id;
    }

    public void setID(String id) {
        this.id = id;
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
     * Get the course in String form.
     *
     * @return the course in String form.
     */
    public String toString() {
        return id + " - " + name + "\nCredit hours: " + credits;
    }
}
