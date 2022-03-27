package edu.illinoisstate.gui;

import edu.illinoisstate.UserAccount;
import edu.illinoisstate.database.Database;
import edu.illinoisstate.utils.HintTextBox;
import edu.illinoisstate.utils.Utils;

import javax.swing.*;
import java.awt.*;

public class DeleteAccount extends LoginWindow {
    private final JDialog window = new JDialog();
    private final JPanel panel = new JPanel();
    private final UserAccount user;

    public DeleteAccount(UserAccount user) {
        window.setSize(500, 300);
        window.setLocationRelativeTo(null); // Center the window on the screen
        window.setTitle("Are you sure?");
        window.setModal(true); // this prevents use of other windows
        window.setIconImage(Utils.getImage("reggie.png"));

        this.user = user;

        addToActiveWindows(window);
        createWindow();
    }

    public void createWindow() {
        JLabel label1 = new JLabel("Are you sure you want to delete your account?");
        JLabel label2 = new JLabel("This action is permanent and cannot be undone.");
        JLabel label3 = new JLabel("To confirm, please enter your username and select delete.");

        label2.setForeground(Color.RED);

        JTextField usernameTxtField = new HintTextBox("username");

        JLabel incorrectLabel = new JLabel("Incorrect username.");
        incorrectLabel.setVisible(false);

        JButton confirmBtn = new JButton("Delete");
        confirmBtn.addActionListener(e -> {
            String input = usernameTxtField.getText();

            if (!input.equalsIgnoreCase(user.getUsername())) {
                incorrectLabel.setVisible(true);
                return;
            }

            int result = JOptionPane.showConfirmDialog(window, "There's no going back! Are you sure?");

            if (result == 0) {
                JOptionPane.showMessageDialog(window, "Account successfully deleted.");
                Database.getInstance().deleteUserAccount(user);
                closeAllActiveWindows(); // Close all active windows
            }
        });

        label1.setAlignmentX(Component.CENTER_ALIGNMENT);
        Utils.addWhiteSpace(panel,25);
        panel.add(label1);
        label2.setAlignmentX(Component.CENTER_ALIGNMENT);
        Utils.addWhiteSpace(panel,25);
        panel.add(label2);
        label3.setAlignmentX(Component.CENTER_ALIGNMENT);
        Utils.addWhiteSpace(panel,25);
        panel.add(label3);
        usernameTxtField.setAlignmentX(Component.CENTER_ALIGNMENT);
        usernameTxtField.setColumns(15);
        usernameTxtField.setPreferredSize(new Dimension(25, 25));
        Utils.addWhiteSpace(panel,30);
        panel.add(usernameTxtField);
        confirmBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(confirmBtn);
        incorrectLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        Utils.addWhiteSpace(panel,50);
        panel.add(incorrectLabel);
        Utils.addWhiteSpace(panel,45);
        window.add(panel);
        window.setVisible(true);
    }

}