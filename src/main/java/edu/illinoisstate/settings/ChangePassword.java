package edu.illinoisstate.settings;


import edu.illinoisstate.RWindow;
import edu.illinoisstate.UserAccount;
import edu.illinoisstate.database.DatabaseHandler;
import edu.illinoisstate.utils.HintPasswordTextBox;
import edu.illinoisstate.utils.HintTextBox;
import edu.illinoisstate.utils.Utils;

import javax.swing.*;

public class ChangePassword {

    UserAccount user = new UserAccount();

    private final RWindow window = new RWindow("Change password");
    //private final JFrame changePassWindow;

    public ChangePassword() {
        // this.changePassWindow = changePassWindow;

        changePassword();

        createWindow();

    }

    public void createWindow() {
        JTextField oldPassword = new HintTextBox("Old Password", 15, .5f);
        JTextField newPass = new HintPasswordTextBox("New Password", 15, .5f);

    }

    public boolean changePassword() {

        JOptionPane.showInputDialog(null, "Enter old password: ", "Input", JOptionPane.QUESTION_MESSAGE);
        JOptionPane.showInputDialog(null, "Enter new password: ", "Input", JOptionPane.QUESTION_MESSAGE);

        return true;
    }


}
