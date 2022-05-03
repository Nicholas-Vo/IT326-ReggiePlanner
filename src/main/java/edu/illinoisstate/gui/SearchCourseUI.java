package edu.illinoisstate.gui;

import edu.illinoisstate.PlanList;
import edu.illinoisstate.RButton;
import edu.illinoisstate.RWindow;
import edu.illinoisstate.course.Course;
import edu.illinoisstate.database.DatabaseHandler;
import edu.illinoisstate.utils.HintTextBox;

import javax.swing.JLabel;
import javax.swing.JTextField;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * This window appears when the user selects the "Forgot password" button on the main window.
 */
public class SearchCourseUI {
    private final RWindow window = new RWindow("Search for a course");

    public SearchCourseUI() {
        window.setSize(400, 300);
        window.setLocationRelativeTo(null);

        createWindow();
    }

    public void createWindow() {
        JTextField courseTextField = new HintTextBox("course", 15);

        PlanList resultList = new PlanList(List.of());

        List<Course> courses = DatabaseHandler.getCourseList();
        RButton searchBtn = new RButton("Search", () -> {
            List<Course> results = new ArrayList<>();
            for (Course course : courses) {
                if (course.getCourseID().contains(courseTextField.getText().toUpperCase(Locale.ROOT))) {
                    if (results.size() > 8) {
                        continue;
                    }
                    results.add(course);
                }
            }

            resultList.setListData(results);
        });

        window.getRootPane().setDefaultButton(searchBtn); // Allows Enter key to submit
        window.addComponents(courseTextField, searchBtn, resultList);
    }

}


















