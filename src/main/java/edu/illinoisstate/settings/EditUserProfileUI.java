package edu.illinoisstate.settings;
import edu.illinoisstate.UserAccount;
import edu.illinoisstate.database.DatabaseHandler;
import edu.illinoisstate.RButton;
import edu.illinoisstate.RWindow;
import edu.illinoisstate.utils.Utils;

import java.awt.event.WindowEvent;


public class EditUserProfileUI {
    private final RWindow window = new RWindow("Edit Profile");


    public EditUserProfileUI() {
        window.setSize(600, 400);
        window.setLocationRelativeTo(null);
        Utils.allowEscapeToClose(window, window.getPanel());

        createWindow();
    }

    public void createWindow() {
        RButton changePassword = new RButton("Change Password");
        changePassword.addActionListener(e -> new ChangePassword());

        RButton changeUserName = new RButton("Change Username");
        changeUserName.addActionListener(e -> new ChangeUsername());
            // Close this window and the main program window now that we're logged in, then open up home page
            window.dispatchEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSING));

        window.getRootPane().setDefaultButton(changePassword); // Allows Enter key to submit
        window.getRootPane().setDefaultButton(changeUserName);
        window.addComponents(changePassword, changeUserName);
    }
}
