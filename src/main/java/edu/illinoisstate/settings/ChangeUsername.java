package edu.illinoisstate.settings;


import edu.illinoisstate.RWindow;
import edu.illinoisstate.UserAccount;
import edu.illinoisstate.database.DatabaseHandler;
import edu.illinoisstate.utils.HintPasswordTextBox;
import edu.illinoisstate.utils.HintTextBox;
import edu.illinoisstate.utils.Utils;

import javax.swing.*;

public class ChangeUsername {

    UserAccount user = new UserAccount();

    private final RWindow window = new RWindow("Change password");
    //private final JFrame changePassWindow;

    public ChangeUsername() {
        // this.changePassWindow = changePassWindow;

        changeUsername();

        createWindow();

    }

    public void createWindow() {
        JTextField oldPassword = new HintTextBox("Old Username", 15, .5f);
        JTextField newPass = new HintPasswordTextBox("New Username", 15, .5f);

    }

    public boolean changeUsername() {

        JOptionPane.showInputDialog(null, "Enter old Username: ", "Input", JOptionPane.QUESTION_MESSAGE);
        JOptionPane.showInputDialog(null, "Enter new Username: ", "Input", JOptionPane.QUESTION_MESSAGE);

        return true;
    }


}