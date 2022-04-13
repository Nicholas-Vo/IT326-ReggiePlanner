package edu.illinoisstate.course;


import javax.persistence.*;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

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

    /**
     * Construct a new course given a course String
     */
//    public Course(String input) {
//        String[] arr = input.split(" \"");
//
//        this.courseID = arr[0];
//        arr = arr[1].split("\"");
//        this.name = arr[0];
//        arr = arr[1].split(" ");
//        this.credits = Double.parseDouble(arr[1]);
//        this.gpaRequirement = Double.parseDouble(arr[2]);
//    }

    public Course(String input) {
        String regex = "(IT[0-9]{3}).*(\"(.*?)\").*([0-9]).*([0-9])";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        if (matcher.find()) {
            this.courseID = matcher.group(1);
            this.name = matcher.group(3);
            this.credits = Integer.parseInt(matcher.group(4));
            this.gpaRequirement = Integer.parseInt(matcher.group(5));
        }
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
        this.courseID = id;
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
        return courseID + " - " + name + "\nCredit hours: " + credits;
    }
}
