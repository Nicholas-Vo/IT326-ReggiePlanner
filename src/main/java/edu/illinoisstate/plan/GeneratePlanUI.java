package edu.illinoisstate.plan;

import edu.illinoisstate.RButton;
import edu.illinoisstate.RWindow;
import edu.illinoisstate.UserAccount;
import edu.illinoisstate.course.Course;
import edu.illinoisstate.database.Database;
import edu.illinoisstate.database.DatabaseHandler;
import edu.illinoisstate.utils.HintTextBox;
import edu.illinoisstate.utils.Utils;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.util.ArrayList;
import java.util.List;

public class GeneratePlanUI {
    private final RWindow window = new RWindow("Create a new plan");
    private final UserAccount user;

    private final Database db = DatabaseHandler.database();

    public GeneratePlanUI(UserAccount user) {
        window.setSize(550, 550);
        window.setLocationRelativeTo(null);
        Utils.allowEscapeToClose(window, window.getPanel());

        this.user = user;

        createWindow();
    }

    private void createWindow() {
        List<Course> courseList = new ArrayList<>();

        RButton addBtn = new RButton("Add course");
        HintTextBox inputField = new HintTextBox("enter course name here", 15);

        JLabel instructionLabel = new JLabel("");
        JLabel errorLabel = new JLabel("Error: Class not found.");
        errorLabel.setVisible(false);
        JLabel successLabel = new JLabel("Successfully added course.");
        successLabel.setVisible(false);
        JLabel currentList = new JLabel("Your current course plan:");

        addBtn.addActionListener(e -> {
            errorLabel.setVisible(false);
            successLabel.setVisible(false);

            if (db.getCourseByID(inputField.getText()) == null) {
                errorLabel.setVisible(true);
                return;
            }

            Course course = db.getCourseByID(inputField.getText());
            courseList.add(course);
            System.out.println("Successfully added course " + course.getCourseID() + ": " + course.getName() + ".");
            successLabel.setVisible(true);

            inputField.setText("");
        });

        window.getRootPane().setDefaultButton(addBtn); // Allows Enter key to submit
        window.addComponents(inputField, addBtn, errorLabel, successLabel);
    }

}















