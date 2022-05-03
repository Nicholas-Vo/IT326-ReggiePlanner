package edu.illinoisstate.gui;

import edu.illinoisstate.database.DatabaseHandler;
import edu.illinoisstate.utils.WindowTracker;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

    public class CourseList {
        private final JDialog window = new JDialog();
        private final JPanel panel = new JPanel();

        public CourseList() {
            window.setSize(700, 900);
            window.setLocationRelativeTo(null); // Center the window on the screen
            window.setTitle("Course List");
            window.setModal(true); // this prevents use of other windows
            window.setResizable(false);
            WindowTracker.addToActiveWindows(window);
            createWindow();
        }

        public void createWindow() {

            JTextArea textBox = new JTextArea();
            textBox.setPreferredSize(new Dimension(630, 700));
            textBox.setLineWrap(true);
            textBox.setFont(UIManager.getFont("TextField.font"));
            textBox.setBorder(new LineBorder(Color.BLACK));
            textBox.setText(String.valueOf(DatabaseHandler.getCourseList()));

            panel.add(textBox);

            window.add(panel);
            window.setVisible(true);
        }
}
