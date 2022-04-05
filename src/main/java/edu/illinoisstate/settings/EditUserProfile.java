package edu.illinoisstate.settings;

import edu.illinoisstate.RWindow;
import edu.illinoisstate.UserAccount;
import edu.illinoisstate.utils.Utils;
import edu.illinoisstate.utils.WindowTracker;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class EditUserProfile {
    private final RWindow window = new RWindow("Edit profile");
    private final UserAccount user;

    public EditUserProfile(UserAccount user) {
        window.setSize(600, 400);
        window.setLocationRelativeTo(null);

        this.user = user; // set local UserAccount to passed in user
        createWindow();
    }

    public void createWindow() {
    }

}