package edu.illinoisstate.gui;

import edu.illinoisstate.PlanList;
import edu.illinoisstate.RButton;
import edu.illinoisstate.RWindow;
import edu.illinoisstate.UserAccount;
import edu.illinoisstate.course.Course;
import edu.illinoisstate.database.DatabaseHandler;
import edu.illinoisstate.email.EmailHandler;
import edu.illinoisstate.utils.HintTextBox;
import edu.illinoisstate.utils.Utils;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * This window appears when the user selects the "Forgot password" button on the main window.
 */
public class SearchPlanUI {
    private final RWindow window = new RWindow("Search for a course");

    public SearchPlanUI() {
        window.setSize(400, 300);
        window.setLocationRelativeTo(null);

        createWindow();
    }

    public void createWindow() {
        JTextField courseTextField = new HintTextBox("course", 15);
        JLabel resultLabel = new JLabel();
        resultLabel.setVisible(false);

        PlanList resultList = new PlanList(List.of());

        List<Course> courses = DatabaseHandler.getCourseList();
        RButton searchBtn = new RButton("Search", () -> {
            if (courseTextField.getText().isEmpty()) {
                resultLabel.setText("No course found.");
                resultLabel.setVisible(true);
                return;
            }

            List<Course> results = new ArrayList<>();
            courses.forEach(course -> {
                if (course.getCourseID().contains(courseTextField.getText().toUpperCase(Locale.ROOT))) {
                    results.add(course);
                }
            });

            resultList.setListData(results);
        });

        window.getRootPane().setDefaultButton(searchBtn); // Allows Enter key to submit
        window.addComponents(courseTextField, searchBtn, resultList, resultLabel);
    }

}


















