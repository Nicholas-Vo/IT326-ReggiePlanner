package edu.illinoisstate.gui;

import edu.illinoisstate.*;
import edu.illinoisstate.course.Course;
import edu.illinoisstate.course.CourseRandomizer;
import edu.illinoisstate.database.Database;
import edu.illinoisstate.database.DatabaseHandler;
import edu.illinoisstate.plan.UserPlan;
import edu.illinoisstate.utils.HintTextBox;
import edu.illinoisstate.utils.Utils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

public class GenerateNewUserPlanUI {
    private final RWindow window = new RWindow("Generate a new plan");
    private final JPanel fallPanel = new JPanel(new BorderLayout());
    private final JLabel fallLabel = new JLabel("Fall");
    private final JPanel springPanel = new JPanel(new BorderLayout());
    private final JLabel springLabel = new JLabel("Spring");
    private final JPanel summerPanel = new JPanel(new BorderLayout());
    private final JLabel summerLabel = new JLabel("Summer");

    private final UserAccount user;

    public GenerateNewUserPlanUI(UserAccount user) {
        window.setSize(550, 550);
        window.setLocationRelativeTo(null);
        Utils.allowEscapeToClose(window, window.getPanel());

        this.user = user;

        createWindow();
    }

    private void createWindow() {
        CourseRandomizer randomizer = new CourseRandomizer();

        PlanList fallList = new PlanList(randomizer.generate(5, 1));
        fallPanel.add(fallLabel, BorderLayout.NORTH);
        fallPanel.add(fallList, BorderLayout.SOUTH);

        PlanList springList = new PlanList(randomizer.generate(5, 2));
        springPanel.add(springLabel, BorderLayout.NORTH);
        springPanel.add(springList, BorderLayout.SOUTH);

        PlanList summerList = new PlanList(randomizer.generate(3, 2));
        summerPanel.add(summerLabel, BorderLayout.NORTH);
        summerPanel.add(summerList, BorderLayout.SOUTH);

        RButton refreshBtn = new RButton("Generate");
        refreshBtn.addActionListener(e -> {
            fallList.setListData(randomizer.generate(5, 1));
            springList.setListData(randomizer.generate(5, 2));
            summerList.setListData(randomizer.generate(3, 2));
        });

        JLabel excludeLabel = new JLabel();
        excludeLabel.setVisible(false);

        HintTextBox excludeField = new HintTextBox("exclude class", 15);

        RButton addCourseBtn = new RButton("Add completed course");
        addCourseBtn.addActionListener(e -> {
            excludeLabel.setVisible(false);
            String courseID = excludeField.getText().toUpperCase();

            if (!DatabaseHandler.isValidCourse(courseID)) {
                excludeLabel.setText("Invalid course.");
                excludeLabel.setVisible(true);
                return;
            }

            excludeLabel.setText("Excluded " + courseID + " from your plan.");
            randomizer.exclude(DatabaseHandler.getCourseByID(courseID));
            excludeLabel.setVisible(true);
        });

        RButton saveBtn = new RButton("Save plan");
        saveBtn.addActionListener(e -> {
            if (Controller.createPlan(user.uuid(), fallList.courses(), springList.courses(), summerList.courses())) {
                JOptionPane.showMessageDialog(window, "Plan saved to file.");
            } else {
                JOptionPane.showMessageDialog(window, "We ran into an error! Please try again.");
            }
        });

        window.addComponents(fallPanel, springPanel, summerPanel,
                refreshBtn, saveBtn, excludeField, addCourseBtn, excludeLabel);
    }

}














