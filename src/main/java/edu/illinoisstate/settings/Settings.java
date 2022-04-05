package edu.illinoisstate.settings;

import edu.illinoisstate.RButton;
import edu.illinoisstate.RWindow;
import edu.illinoisstate.UserAccount;
import edu.illinoisstate.utils.Utils;

import java.awt.event.WindowEvent;

public class Settings {
    private final RWindow window = new RWindow("Settings");
    private final UserAccount user;

    public Settings(UserAccount user) {
        window.setSize(500, 300);
        window.setIconImage(Utils.getImage("settings.png"));
        window.setLocationRelativeTo(null); // Centers the window on the screen

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
