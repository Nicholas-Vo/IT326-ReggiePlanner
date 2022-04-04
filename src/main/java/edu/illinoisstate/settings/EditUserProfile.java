package edu.illinoisstate.settings;

import edu.illinoisstate.ReggieWindow;
import edu.illinoisstate.UserAccount;
import edu.illinoisstate.utils.Utils;
import edu.illinoisstate.utils.WindowTracker;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class EditUserProfile {
    private final ReggieWindow window = new ReggieWindow("Edit profile");
    private final UserAccount user;

    public EditUserProfile(UserAccount user) {
        window.setSize(600, 400);

        this.user = user; // set local UserAccount to passed in user
        createWindow();
    }

    public void createWindow() {
    }

}
