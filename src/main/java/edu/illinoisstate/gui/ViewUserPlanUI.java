package edu.illinoisstate.gui;

import edu.illinoisstate.RButton;
import edu.illinoisstate.RWindow;
import edu.illinoisstate.UserAccount;
import edu.illinoisstate.database.Database;
import edu.illinoisstate.database.DatabaseHandler;
import edu.illinoisstate.plan.UserPlan;
import edu.illinoisstate.utils.Utils;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.BorderLayout;

public class ViewUserPlanUI {
    private final RWindow window = new RWindow("Generate a new plan");
    private final UserAccount user;
    private final Database db = DatabaseHandler.database();

    private final JPanel fallPanel = new JPanel(new BorderLayout());
    private final JLabel fallLabel = new JLabel("Fall");
    private final JPanel springPanel = new JPanel(new BorderLayout());
    private final JLabel springLabel = new JLabel("Spring");
    private final JPanel summerPanel = new JPanel(new BorderLayout());
    private final JLabel summerLabel = new JLabel("Summer");

    public ViewUserPlanUI(UserAccount user) {
        window.setSize(550, 550);
        window.setLocationRelativeTo(null);
        Utils.allowEscapeToClose(window, window.getPanel());

        this.user = user;

        createWindow();
    }

    private void createWindow() {
        UserPlan thePlan = new UserPlan(db.getCourseList(), user.uuid());
        thePlan.generate(1, 5);

        JList<String> fallList = new JList<>(thePlan.getCourseIDs());

        fallPanel.add(fallLabel, BorderLayout.NORTH);
        fallPanel.add(fallList, BorderLayout.SOUTH);

        thePlan.generate(2, 5);
        JList<String> springList = new JList<>(thePlan.getCourseIDs());

        springPanel.add(springLabel, BorderLayout.NORTH);
        springPanel.add(springList, BorderLayout.SOUTH);

        thePlan.generate(2, 3);
        JList<String> summerList = new JList<>(thePlan.getCourseIDs());

        summerPanel.add(summerLabel, BorderLayout.NORTH);
        summerPanel.add(summerList, BorderLayout.SOUTH);

        RButton refreshBtn = new RButton("Generate", () -> {
            thePlan.generate(1, 5);
            fallList.setListData(thePlan.getCourseIDs());

            thePlan.generate(2, 5);
            springList.setListData(thePlan.getCourseIDs());

            thePlan.generate(2, 3);
            summerList.setListData(thePlan.getCourseIDs());
        });

        RButton saveBtn = new RButton("Save plan", () -> {
            JOptionPane.showMessageDialog(window,"Plan saved to file!");
            db.save(thePlan);
        });


        //window.getRootPane().setDefaultButton(addBtn);
        window.addComponents(fallPanel, springPanel, summerPanel, refreshBtn, saveBtn);
    }

}















