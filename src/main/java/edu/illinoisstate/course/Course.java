package edu.illinoisstate.course;


import javax.persistence.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    private int level;

    /**
     * Construct a new course given a course String from file
     */
    public Course(String input) {
        String regex = "(IT[0-9]{3}).*(\"(.*?)\").*([0-9]).*([0-9])";
        Matcher matcher = Pattern.compile(regex).matcher(input);

        if (matcher.find()) {
            courseID = matcher.group(1);
            name = matcher.group(3);
            credits = Integer.parseInt(matcher.group(4));
            gpaRequirement = Integer.parseInt(matcher.group(5));
        }

        level = courseID.charAt(2);
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

    public int getLevel() {
        return level;
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

    /**
     * Check for equality
     */
    public boolean equals(Object o) {
        if (!(o instanceof Course course)) {
            return false;
        }

        return course.courseID.equals(courseID);
    }
}
