package edu.illinoisstate.gui;

import edu.illinoisstate.RButton;
import edu.illinoisstate.RWindow;
import edu.illinoisstate.UserAccount;
import edu.illinoisstate.course.Semester;
import edu.illinoisstate.database.Database;
import edu.illinoisstate.database.DatabaseHandler;
import edu.illinoisstate.plan.UserPlan;
import edu.illinoisstate.utils.Utils;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import java.awt.BorderLayout;

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
        UserPlan thePlan = new UserPlan(db.getCourseList(), user);

        thePlan.generate(1, 5);

        JPanel fallPanel = new JPanel(new BorderLayout());
        JLabel fallLabel = new JLabel("Fall");

        JList<String> fallList = new JList<>(thePlan.getCourseIDs());

        fallPanel.add(fallLabel, BorderLayout.NORTH);
        fallPanel.add(fallList, BorderLayout.SOUTH);

        String[] sprintData = {"IT330", "IT343", "IT180", "IT360", "IT372"};
        JPanel springPanel = new JPanel(new BorderLayout());
        JLabel springLabel = new JLabel("Spring");

        springPanel.add(springLabel, BorderLayout.NORTH);
        springPanel.add(new JList<>(sprintData), BorderLayout.SOUTH);

        String[] summerData = {"", "", "", "", ""};

        JPanel summerPanel = new JPanel(new BorderLayout());
        JLabel summerLabel = new JLabel("Summer");

        summerPanel.add(summerLabel, BorderLayout.NORTH);
        summerPanel.add(new JList<>(summerData), BorderLayout.SOUTH);

        RButton reGenerate = new RButton("Generate", () -> {
            thePlan.generate(1, 5);
            fallList.setListData(thePlan.getCourseIDs());
        });

        //window.getRootPane().setDefaultButton(addBtn);
        window.addComponents(fallPanel, springPanel, summerPanel, reGenerate);
    }

}















