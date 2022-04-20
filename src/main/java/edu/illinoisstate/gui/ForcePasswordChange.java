package edu.illinoisstate.gui;

import edu.illinoisstate.RButton;
import edu.illinoisstate.RWindow;
import edu.illinoisstate.UserAccount;
import edu.illinoisstate.utils.HintPasswordTextBox;
import edu.illinoisstate.utils.Security;
import edu.illinoisstate.utils.Utils;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.WindowEvent;

public class ForcePasswordChange {
    private final RWindow window = new RWindow("Please set a new password");
    private final UserAccount user;
    private final RWindow previousWindow;

    public ForcePasswordChange(UserAccount user, RWindow previousWindow) {
        window.setSize(350, 300);
        window.setIconImage(Utils.getImage("settings.png"));
        window.setLocationRelativeTo(null); // Centers the window on the screen
        Utils.allowEscapeToClose(window, window.getPanel());

        this.user = user;
        this.previousWindow = previousWindow;
        createWindow();
    }

    public void createWindow() {
        JTextField password = new HintPasswordTextBox("new password", 15);
        var submit = new RButton("Submit", () -> {
            if (!Security.isValidPassword(user.getUsername(), password.getText())) {
                JOptionPane.showMessageDialog(window, "Sorry, that's an invalid password.");
                return;
            }

            user.setPassword(password.getText());
            user.setTempPassword(null); // Remove temp password now that it's been used
            user.setForcePasswordChangeValue(false); // Update user force PW state
            JOptionPane.showMessageDialog(window, "Password changed. You may now sign in.");

            /*
            Close this window and the previous one
             */
            window.dispatchEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSING));
            previousWindow.dispatchEvent(new WindowEvent(previousWindow, WindowEvent.WINDOW_CLOSING));
        });

        window.getRootPane().setDefaultButton(submit); // Allows Enter key to submit
        window.addComponents(password, submit);
    }

}
