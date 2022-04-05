package edu.illinoisstate.settings;

import edu.illinoisstate.RButton;
import edu.illinoisstate.UserAccount;
import edu.illinoisstate.ReggieWindow;
import edu.illinoisstate.utils.Utils;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.util.Arrays;

public class Settings {
    private final ReggieWindow window = new ReggieWindow("Settings");
    private final UserAccount user;

    public Settings(UserAccount user) {
        window.setSize(500, 300);
        window.setIconImage(Utils.getImage("settings.png"));

        this.user = user;
        createWindow();
    }

    public void createWindow() {
        var editProfileBtn = new RButton("Edit user profile", () -> new EditUserProfile(user));
        var contactBtn = new RButton("Contact the developers", () -> new ContactDevelopers(user));
        var deleteBtn = new RButton("Delete account", () -> new DeleteAccount(user));
        var closeBtn = new RButton("Close", () -> window.dispatchEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSING)));

        window.addComponents(editProfileBtn, contactBtn, deleteBtn, closeBtn);
    }

}
