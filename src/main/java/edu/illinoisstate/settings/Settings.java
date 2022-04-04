package edu.illinoisstate.settings;

import edu.illinoisstate.ReggieButton;
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
        var editProfileBtn = new ReggieButton("Edit user profile", () -> new EditUserProfile(user));
        var contactBtn = new ReggieButton("Contact the developers", () -> new ContactDevelopers(user));
        var deleteBtn = new ReggieButton("Delete account", () -> new DeleteAccount(user));
        var closeBtn = new ReggieButton("Close", () -> window.dispatchEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSING)));

        window.addComponents(Arrays.asList(editProfileBtn, contactBtn, deleteBtn, closeBtn));
    }

}
