package edu.illinoisstate.course;


import com.sun.istack.NotNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents a course at Illinois State University.
 */
@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long id;
    private String courseID;
    private String name;
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
        }

        // The course level
        level = Integer.parseInt(courseID.substring(2, 3));
    }

    public Course() {
        // Empty no-param constructor needed for HSQLDB
    }

    /**
     * Gets the Course's name.
     *
     * @return this Course's name.
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

    /**
     * Gets the Course's credit hours.
     *
     * @return this Course's credit hours.
     */
    public double getCredits() {
        return credits;
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
