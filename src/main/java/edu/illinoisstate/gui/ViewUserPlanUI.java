package edu.illinoisstate.gui;

import edu.illinoisstate.RWindow;
import edu.illinoisstate.UserAccount;
import edu.illinoisstate.course.Course;
import edu.illinoisstate.course.CourseHandler;
import edu.illinoisstate.database.Database;
import edu.illinoisstate.database.DatabaseHandler;
import edu.illinoisstate.utils.Utils;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

public class ViewUserPlanUI {
    private final RWindow window = new RWindow("Generate a new plan");
    private final UserAccount user;
    private final Database db = DatabaseHandler.database();

    public ViewUserPlanUI(UserAccount user) {
        window.setSize(550, 550);
        window.setLocationRelativeTo(null);
        Utils.allowEscapeToClose(window, window.getPanel());

        this.user = user;

        createWindow();
    }

    private void createWindow() {
        String[] fallData = {"IT279", "IT355", "COM223", "IT378", "IT388"};
        JPanel fallPanel = new JPanel(new BorderLayout());
        JLabel fallLabel = new JLabel("Fall");

        fallPanel.add(fallLabel, BorderLayout.NORTH);
        fallPanel.add(new JList<>(fallData), BorderLayout.SOUTH);

        String[] sprintData = {"IT330", "IT343", "IT180", "IT360", "IT372"};
        JPanel springPanel = new JPanel(new BorderLayout());
        JLabel springLabel = new JLabel("Spring");

        springPanel.add(springLabel, BorderLayout.NORTH);
        springPanel.add(new JList<>(sprintData), BorderLayout.SOUTH);

        String[] summerData = {"IT330", "IT343", "IT180", "IT360", "IT372"};

        JPanel summerPanel = new JPanel(new BorderLayout());
        JLabel summerLabel = new JLabel("Summer");

        summerPanel.add(summerLabel, BorderLayout.NORTH);
        summerPanel.add(new JList<>(summerData), BorderLayout.SOUTH);

        //window.getRootPane().setDefaultButton(addBtn);
        window.addComponents(fallPanel, springPanel, summerPanel);
    }

}















