package edu.illinoisstate.Testing;

import edu.illinoisstate.course.Course;
import edu.illinoisstate.course.CourseRandomizer;
import edu.illinoisstate.database.DatabaseHandler;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class AddCourse {

    CourseRandomizer test;
    ArrayList<Course> testplan;


    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        test = new CourseRandomizer();
        testplan = new ArrayList<Course>();

    }
    @Test
    void check(){
        test.exclude(DatabaseHandler.getCourseByID("IT180"));
        testplan.add(DatabaseHandler.getCourseByID("IT180"));
        if(testplan.contains(DatabaseHandler.getCourseByID("IT180"))){
            testplan.remove(DatabaseHandler.getCourseByID("IT180"));
        }
    }


    @org.junit.jupiter.api.AfterEach
        void tearDown() {
            testplan = null;


    }
}