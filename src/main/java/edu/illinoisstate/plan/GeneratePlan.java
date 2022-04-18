package edu.illinoisstate.plan;

import edu.illinoisstate.UserAccount;
import edu.illinoisstate.course.Course;
import edu.illinoisstate.database.Database;

import javax.swing.JButton;
import javax.swing.JTextField;
import java.util.ArrayList;
import java.util.List;

public class GeneratePlan {
    private final Database db = Database.getInstance();
    private final List<Course> completedCourses = new ArrayList<>();
    private final JButton addBtn = new JButton();
    private final JTextField inputField = new JTextField();

    public GeneratePlan(UserAccount user) {
        generate();
    }

    private void generate() {
        addBtn.addActionListener(e -> {
            String input = inputField.getText();

            if (db.getCourseByID(input) == null) {
                //JOptionPane.showMessageDialog("Error doesn't exist");
                return;
            }

            Course course = db.getCourseByID(input);
            completedCourses.add(course);
            // course added!
            inputField.setText("");
        });

        // Now we have a list of courses the student has taken,
        // use this list to generate a plan
        // ...
    }

}
