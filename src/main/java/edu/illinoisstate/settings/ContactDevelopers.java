package edu.illinoisstate.settings;

import edu.illinoisstate.UserAccount;
import edu.illinoisstate.database.Database;
import edu.illinoisstate.gui.MainProgramWindow;
import edu.illinoisstate.utils.HintTextBox;
import edu.illinoisstate.utils.Utils;
import edu.illinoisstate.utils.WindowTracker;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class ContactDevelopers {
    private final JDialog window = new JDialog();
    private final JPanel panel = new JPanel();
    private final UserAccount user;

    public ContactDevelopers(UserAccount user) {
        window.setSize(600, 400);
        window.setLocationRelativeTo(null); // Center the window on the screen
        window.setTitle("Contact us");
        window.setModal(true); // this prevents use of other windows
        window.setResizable(false);
        window.setIconImage(Utils.getImage("reggie.png"));

        this.user = user; // set local UserAccount to passed in user

        WindowTracker.addToActiveWindows(window);
        createWindow();
    }

    public void createWindow() {
        JLabel label1 = new JLabel("We'd love to hear from you!");
        JLabel label2 = new JLabel("Please keep an eye on your email address for a response.");

        JTextArea textBox = new JTextArea();
        textBox.setPreferredSize(new Dimension(250, 250));
        textBox.setLineWrap(true);
        textBox.setBorder(new LineBorder(Color.BLACK));

        JLabel messageSent = new JLabel("Message sent. Thank you!");
        messageSent.setVisible(false);

        JButton confirmBtn = new JButton("Send message");
        confirmBtn.addActionListener(e -> {
            messageSent.setVisible(true);
            // todo send email with message to admin account here
            textBox.setText("");
        });

        panel.add(label1);
        panel.add(textBox);
        panel.add(confirmBtn);
        panel.add(label2);

        window.add(panel);
        window.setVisible(true);
    }

}
