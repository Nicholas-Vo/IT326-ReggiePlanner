package edu.illinoisstate.gui;

import edu.illinoisstate.Controller;
import edu.illinoisstate.PlanList;
import edu.illinoisstate.RButton;
import edu.illinoisstate.RWindow;
import edu.illinoisstate.UserAccount;
import edu.illinoisstate.course.CourseRandomizer;
import edu.illinoisstate.database.DatabaseHandler;
import edu.illinoisstate.plan.UserPlan;
import edu.illinoisstate.utils.HintTextBox;
import edu.illinoisstate.utils.Utils;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Font;
import java.util.List;

public class GenerateNewPlan {
    private final JLabel fallLabel = new JLabel("Fall  ");
    private final JLabel springLabel = new JLabel("Spring  ");
    private final JLabel summerLabel = new JLabel("Summer  ");
    private final UserAccount user;
    private final RWindow window;

    public GenerateNewPlan(RWindow window, JPanel homePanel, UserAccount user) {
        this.window = window;
        this.user = user;

        homePanel.add(getPanel(), "Generate");
    }

    public JPanel getPanel() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel listPanel = new JPanel();
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.LINE_AXIS));

        JPanel btnPanel = new JPanel();
        JPanel otherPanel = new JPanel();

        CourseRandomizer randomizer = new CourseRandomizer();

        PlanList fallList = new PlanList(randomizer.generate(5, 1));
        fallLabel.setFont(new Font("Jumble", Font.BOLD, 12));
        listPanel.add(fallLabel);
        listPanel.add(Box.createHorizontalStrut(5));
        listPanel.add(fallList);
        listPanel.add(Box.createHorizontalStrut(5));

        PlanList springList = new PlanList(randomizer.generate(5, 2));
        springLabel.setFont(new Font("Jumble", Font.BOLD, 12));
        listPanel.add(springLabel);
        listPanel.add(Box.createHorizontalStrut(5));
        listPanel.add(springList);

        PlanList summerList = new PlanList(randomizer.generate(3, 2));
        summerLabel.setFont(new Font("Jumble", Font.BOLD, 12));
        listPanel.add(Box.createHorizontalStrut(5));
        listPanel.add(summerLabel);
        listPanel.add(Box.createHorizontalStrut(5));

        summerLabel.setVisible(false);
        summerList.setVisible(false);

        int[] amountOfSummerCourse = {0}; // int array needed because of following lambda
        JCheckBox summerCheckBox = new JCheckBox("Include summer");
        summerCheckBox.addActionListener(e -> {
            boolean selected = summerCheckBox.isSelected();

            if (selected) {
                String question = "How many summer courses do you wish to take?";
                try {
                    int userInput = Integer.parseInt(JOptionPane.showInputDialog(question));
                    amountOfSummerCourse[0] = Math.min(userInput, 5);
                } catch (NumberFormatException nfe) {
                    amountOfSummerCourse[0] = 1;
                    JOptionPane.showMessageDialog(window, "That's not a valid number.");
                }
            }
            if (amountOfSummerCourse[0] <= 3) {
                summerList.setListData(randomizer.generate(amountOfSummerCourse[0], 2));
                listPanel.add(summerList);
                summerLabel.setVisible(selected);
                summerList.setVisible(selected);
            }
            else {
                JOptionPane.showMessageDialog(window, "Summer courses must be 3 or less.");
                summerCheckBox.setSelected(false);
            }
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
        excludeField.setMaximumSize(excludeField.getPreferredSize());

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

        RButton priceBtn = new RButton("Calculate Price of Plan");
        priceBtn.addActionListener(e -> {
            double totalPrice = 0;

            for (PlanList list : List.of(fallList, springList)) {
                totalPrice += list.getPrice();
            }
            if (summerCheckBox.isSelected()) {
                totalPrice += summerList.getPrice();
            }
            JOptionPane.showMessageDialog(window, "The expected price is $" + Utils.formatNumber(totalPrice) + ".");
        });

        RButton saveBtn = new RButton("Save plan");
        saveBtn.addActionListener(e -> {
            if (Controller.modifyPlan(user.uuid(), fallList.courses(), springList.courses(), summerList.courses())) {
                JOptionPane.showMessageDialog(window, "Your plan has been saved.");
            } else {
                JOptionPane.showMessageDialog(window, "We ran into an error! Please try again.");
            }
        });

        mainPanel.add(listPanel, BorderLayout.NORTH);

        btnPanel.setLayout(new BoxLayout(btnPanel, BoxLayout.LINE_AXIS));
        btnPanel.add(Box.createHorizontalStrut(5));
        btnPanel.add(refreshBtn);
        btnPanel.add(Box.createHorizontalStrut(5));
        btnPanel.add(saveBtn);
        btnPanel.add(Box.createHorizontalStrut(5));
        btnPanel.add(summerCheckBox);
        btnPanel.add(Box.createHorizontalStrut(5));
        btnPanel.add(priceBtn);
        btnPanel.add(Box.createVerticalStrut(50));
        mainPanel.add(btnPanel);

        otherPanel.setLayout(new BoxLayout(otherPanel, BoxLayout.LINE_AXIS));
        otherPanel.add(excludeField);
        otherPanel.add(addCourseBtn);
        otherPanel.add(Box.createHorizontalStrut(5));
        otherPanel.add(excludeLabel);
        mainPanel.add(otherPanel, BorderLayout.SOUTH);

        return mainPanel;
    }

}















