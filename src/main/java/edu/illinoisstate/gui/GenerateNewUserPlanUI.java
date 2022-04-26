package edu.illinoisstate.gui;

import edu.illinoisstate.Controller;
import edu.illinoisstate.PlanList;
import edu.illinoisstate.RButton;
import edu.illinoisstate.RWindow;
import edu.illinoisstate.UserAccount;
import edu.illinoisstate.course.CourseRandomizer;
import edu.illinoisstate.database.DatabaseHandler;
import edu.illinoisstate.utils.HintTextBox;
import edu.illinoisstate.utils.Utils;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.BorderLayout;

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
        summerPanel.setVisible(false);

        final int[] amountOfSummerCourse = {0}; // int array needed because of following lambda
        JCheckBox summerCheckBox = new JCheckBox("Include summer");
        summerCheckBox.addActionListener(e -> {
            boolean selected = summerCheckBox.isSelected();

            if (selected) {
                String question = "How many summer courses do you wish to take?";

                try {
                    amountOfSummerCourse[0] = Integer.parseInt(JOptionPane.showInputDialog(question));
                } catch (NumberFormatException nfe) {
                    amountOfSummerCourse[0] = 1;
                    JOptionPane.showMessageDialog(window, "That's not a valid number.");
                }
            }

            summerLabel.setVisible(selected);
            summerPanel.setVisible(selected);
        });

        RButton refreshBtn = new RButton("Generate");
        refreshBtn.addActionListener(e -> {
            fallList.setListData(randomizer.generate(5, 1));
            springList.setListData(randomizer.generate(5, 2));
            if (summerCheckBox.isSelected()) {
                summerList.setListData(randomizer.generate(Math.min(amountOfSummerCourse[0], 5), 2));
            }
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
                refreshBtn, saveBtn, excludeField, addCourseBtn, excludeLabel, summerCheckBox);
    }

}















