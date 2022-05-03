package edu.illinoisstate.Testing;

import edu.illinoisstate.course.Course;
import edu.illinoisstate.course.CourseRandomizer;
import edu.illinoisstate.database.DatabaseHandler;
import edu.illinoisstate.gui.GenerateNewPlan;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class GenerateNewPlanTest {

    CourseRandomizer random;
    ArrayList<Course> plan;


    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        random = new CourseRandomizer();
        plan = new ArrayList<Course>();
        plan.isEmpty();



    }
    @Test
     void checkplan(){
        plan.add(DatabaseHandler.getCourseByID("180"));
        assertNotNull(plan);
    };
    @Test
    void checkplan2(){
        plan.isEmpty();
        assertNotNull(plan);
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
        plan = null;
    }
}