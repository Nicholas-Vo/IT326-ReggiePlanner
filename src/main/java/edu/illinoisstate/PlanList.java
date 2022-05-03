package edu.illinoisstate;

import edu.illinoisstate.course.Course;
import org.hsqldb.rights.User;

import javax.swing.JList;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class PlanList extends JList<String> {
    private final List<Course> courses;

    public PlanList(List<Course> courses) {
        this.courses = courses;
        super.setListData(toStringArray(this.courses));
        super.setFont(super.getFont().deriveFont(16.0f));
    }

    public void setListData(List<Course> courses) {
        super.setListData(toStringArray(courses));
    }

    public void setDeleteOnDoubleClick() {
        super.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    courses.removeIf(course -> course.getCourseID().equals(getSelectedValue()));
                    PlanList.super.setListData(toStringArray(courses));
                }
            }
        });
    }

    private String[] toStringArray(List<Course> courses) {
        String[] theArray = new String[courses.size()];

        for (int i = 0; i < courses.size(); i++) {
            theArray[i] = courses.get(i).getCourseID();
        }

        return theArray;
    }

    public double getPrice() {
        int total = 0;
        double COST_PER_CREDIT = 384.13;
        for (Course course : courses) {
            total += course.getCredits() * COST_PER_CREDIT;
        }
        return total;
    }

    public List<Course> courses() {
        return courses;
    }

}
