package edu.illinoisstate.gui;

import edu.illinoisstate.Controller;
import edu.illinoisstate.PlanList;
import edu.illinoisstate.RButton;
import edu.illinoisstate.RWindow;
import edu.illinoisstate.UserAccount;
import edu.illinoisstate.database.DatabaseHandler;
import edu.illinoisstate.plan.UserPlan;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Font;

public class EditYourPlan {
    private final JLabel fallLabel = new JLabel("Fall  ");
    private final JLabel springLabel = new JLabel("Spring  ");
    private final JLabel summerLabel = new JLabel("Summer  ");
    private final UserAccount user;
    private final RWindow window;

    public EditYourPlan(RWindow window, JPanel homePanel, UserAccount user) {
        this.window = window;
        this.user = user;

        homePanel.add(getPanel(), "Edit");
    }

    public JPanel getPanel() {
        UserPlan plan = DatabaseHandler.getUserPlan(user);

        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel listPanel = new JPanel();
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.LINE_AXIS));
        JPanel btnPanel = new JPanel();

        PlanList fallList = new PlanList(plan.getFallCourses());
        fallLabel.setFont(new Font("Jumble", Font.BOLD, 12));
        listPanel.add(fallLabel);
        listPanel.add(Box.createHorizontalStrut(5));
        listPanel.add(fallList);
        listPanel.add(Box.createHorizontalStrut(5));

        PlanList springList = new PlanList(plan.getSpringCourses());
        springLabel.setFont(new Font("Jumble", Font.BOLD, 12));
        listPanel.add(springLabel);
        listPanel.add(Box.createHorizontalStrut(5));
        listPanel.add(springList);

        PlanList summerList = new PlanList(plan.getSummerCourses());
        summerLabel.setFont(new Font("Jumble", Font.BOLD, 12));
        listPanel.add(Box.createHorizontalStrut(5));
        listPanel.add(summerLabel);
        listPanel.add(Box.createHorizontalStrut(5));
        listPanel.add(summerList);

        RButton saveBtn = new RButton("Update plan");
        saveBtn.addActionListener(e -> {
            if (Controller.modifyPlan(user.uuid(), fallList.courses(), springList.courses(), summerList.courses())) {
                JOptionPane.showMessageDialog(window, "Plan updated.");
            } else {
                JOptionPane.showMessageDialog(window, "We ran into an error. Please try again.");
            }
        });

        springList.setDeleteOnDoubleClick();
        springList.setDeleteOnDoubleClick();
        summerList.setDeleteOnDoubleClick();

        mainPanel.add(listPanel, BorderLayout.NORTH);

        btnPanel.setLayout(new BoxLayout(btnPanel, BoxLayout.LINE_AXIS));
        btnPanel.add(Box.createHorizontalStrut(5));
        btnPanel.add(saveBtn);
        btnPanel.add(Box.createHorizontalStrut(5));
        mainPanel.add(btnPanel);

        return mainPanel;
    }

}















