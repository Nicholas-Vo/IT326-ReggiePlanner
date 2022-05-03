package edu.illinoisstate.settings;


import edu.illinoisstate.RWindow;
import edu.illinoisstate.UserAccount;
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
        window.setSize(400, 300);
        window.setLocationRelativeTo(null);
        window.setIconImage(Utils.getImage("qmark.png"));
        window.setVisible(true);

        changePassword();

        createWindow();

    }


    public void createWindow() {
        JTextField oldPassword = new HintTextBox("Old Password", 15, .5f);
        JTextField newPass = new HintPasswordTextBox("New Password", 15, .5f);
        JTextField confirmPass = new HintPasswordTextBox("New Password", 15, .5f);

    }

    public boolean changePassword() {

        String pass = JOptionPane.showInputDialog(null, "Enter old password: ", "Input", JOptionPane.QUESTION_MESSAGE);
        String newPass = JOptionPane.showInputDialog(null, "Enter new password: ", "Input", JOptionPane.QUESTION_MESSAGE);
        String confirmPass = JOptionPane.showInputDialog(null, "passwordChanged: ", "Input", JOptionPane.QUESTION_MESSAGE);


        int n = 3;
        while (n-- > 0) {
            if (pass.equals(user.getPasswordHash())) {

                if (newPass.equals(confirmPass)) {
                    pass = newPass; //could be wrong
                    return true;
                } else {
                    return false;
                }
            } else {
                pass = "";
            }
        }
        new ChangePassword();
        return false;
    }


}
