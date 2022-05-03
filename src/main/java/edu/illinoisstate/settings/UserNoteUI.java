package edu.illinoisstate.settings;

import edu.illinoisstate.UserAccount;
import edu.illinoisstate.utils.Utils;
import edu.illinoisstate.utils.WindowTracker;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Dimension;


public class UserNoteUI {

    private final JDialog window = new JDialog();
    private final JPanel panel = new JPanel();
    private final UserAccount user;

    public UserNoteUI(UserAccount user) {
        window.setSize(600, 400);
        window.setLocationRelativeTo(null); // Center the window on the screen
        window.setTitle("Save a note");
        window.setModal(true); // this prevents use of other windows
        window.setResizable(false);
        window.setIconImage(Utils.getImage("reggie.png"));

        this.user = user; // set local UserAccount to passed in user

        WindowTracker.addToActiveWindows(window);
        createWindow();
    }

    public void createWindow() {
        JLabel label1 = new JLabel("Write a note to save later!");

        JTextArea textBox = new JTextArea();
        textBox.setPreferredSize(new Dimension(250, 250));
        textBox.setLineWrap(true);
        textBox.setBorder(new LineBorder(Color.BLACK));

        JLabel messageSave = new JLabel("Message saved");
        messageSave.setVisible(false);

        JButton confirmBtn = new JButton("Save");
        confirmBtn.addActionListener(e -> {
            messageSave.setVisible(true);
            // todo save message
            textBox.setText("");
        });

        panel.add(label1);
        panel.add(textBox);
        panel.add(confirmBtn);
        panel.add(messageSave);

        window.add(panel);
        window.setVisible(true);
    }

}





