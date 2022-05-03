package edu.illinoisstate.gui;
import edu.illinoisstate.database.DatabaseHandler;

import edu.illinoisstate.utils.WindowTracker;
import javax.swing.JScrollPane;
import edu.illinoisstate.utils.Utils;
import edu.illinoisstate.utils.WindowTracker;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Dimension;

public class CourseList {
    private final JDialog window = new JDialog();
    private final JPanel panel = new JPanel();

    public CourseList() {
        window.setSize(700, 700);
        window.setLocationRelativeTo(null); // Center the window on the screen
        window.setTitle("Course List");
        window.setModal(true); // this prevents use of other windows
        window.setResizable(false);
        WindowTracker.addToActiveWindows(window);
        createWindow();
    }
    public void createWindow() {

        JTextArea textBox = new JTextArea();
        textBox.setPreferredSize(new Dimension(500, 500));
        textBox.setLineWrap(true);
        textBox.setFont(UIManager.getFont("TextField.font"));
        textBox.setBorder(new LineBorder(Color.BLACK));
        textBox.setText(String.valueOf(DatabaseHandler.getCourseList()));
        JScrollPane scroll = new JScrollPane(textBox);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        panel.add(scroll);

    }
}
//java.util.List<Course> courses = DatabaseHandler.getCourseList();
