package edu.illinoisstate.gui;

import edu.illinoisstate.PlanList;
import edu.illinoisstate.RButton;
import edu.illinoisstate.RWindow;
import edu.illinoisstate.UserAccount;
import edu.illinoisstate.course.CourseRandomizer;
import edu.illinoisstate.database.Database;
import edu.illinoisstate.database.DatabaseHandler;
import edu.illinoisstate.plan.UserPlan;
import edu.illinoisstate.utils.Utils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.BorderLayout;

public class ViewUserPlanUI {
    private final RWindow window = new RWindow("Generate a new plan");
    private final JPanel fallPanel = new JPanel(new BorderLayout());
    private final JLabel fallLabel = new JLabel("Fall");
    private final JPanel springPanel = new JPanel(new BorderLayout());
    private final JLabel springLabel = new JLabel("Spring");
    private final JPanel summerPanel = new JPanel(new BorderLayout());
    private final JLabel summerLabel = new JLabel("Summer");

    private final UserAccount user;
    private final Database db = Database.getInstance();

    public ViewUserPlanUI(UserAccount user) {
        window.setSize(550, 550);
        window.setLocationRelativeTo(null);
        Utils.allowEscapeToClose(window, window.getPanel());

        this.user = user;

        createWindow();
    }

    private void createWindow() {
        var r = new CourseRandomizer(db.getCourseList());

        PlanList fallList = new PlanList(r.generate(5, 1));
        fallPanel.add(fallLabel, BorderLayout.NORTH);
        fallPanel.add(fallList, BorderLayout.SOUTH);

        PlanList springList = new PlanList(r.generate(5, 2));
        springPanel.add(springLabel, BorderLayout.NORTH);
        springPanel.add(springList, BorderLayout.SOUTH);

        PlanList summerList = new PlanList(r.generate(3, 2));
        summerPanel.add(summerLabel, BorderLayout.NORTH);
        summerPanel.add(summerList, BorderLayout.SOUTH);

        RButton refreshBtn = new RButton("Generate", () -> {
            fallList.setListData(r.generate(5, 1));
            springList.setListData(r.generate(5, 2));
            summerList.setListData(r.generate(3, 2));
        });

        UserPlan thePlan = new UserPlan(user.uuid());

        RButton saveBtn = new RButton("Save plan", () -> {
            JOptionPane.showMessageDialog(window, "Plan saved to file.");

            thePlan.setFallCourses(fallList.getCourses());
            thePlan.setSpringCourses(springList.getCourses());
            thePlan.setSummerCourses(summerList.getCourses());

            db.save(thePlan);
        });

        //window.getRootPane().setDefaultButton(addBtn);
        window.addComponents(fallPanel, springPanel, summerPanel, refreshBtn, saveBtn);
    }

}















