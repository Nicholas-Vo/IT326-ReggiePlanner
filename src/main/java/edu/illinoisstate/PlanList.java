package edu.illinoisstate;

import edu.illinoisstate.course.Course;

import javax.swing.JList;
import java.util.List;

public class PlanList extends JList<String> {
    private final List<Course> courses;

    public PlanList(List<Course> courses) {
        this.courses =  courses;
        super.setListData(toStringArray(this.courses));
    }

    public void setListData(List<Course> courses) {
        super.setListData(toStringArray(courses));
    }

    private String[] toStringArray(List<Course> courses) {
        String[] theArray = new String[courses.size()];

        for (int i = 0; i < courses.size(); i++) {
            theArray[i] = courses.get(i).getCourseID();
        }

        return theArray;
    }

    public List<Course> courses() {
        return courses;
    }

}
