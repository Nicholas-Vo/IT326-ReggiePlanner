package edu.illinoisstate.settings;

import edu.illinoisstate.RButton;
import edu.illinoisstate.RWindow;
import edu.illinoisstate.UserAccount;
import edu.illinoisstate.utils.Utils;

import java.awt.event.WindowEvent;

public class SettingsUI {
    private final RWindow window = new RWindow("Settings");
    private final UserAccount user;

    public SettingsUI(UserAccount user) {
        window.setSize(500, 300);
        window.setIconImage(Utils.getImage("settings.png"));
        window.setLocationRelativeTo(null); // Centers the window on the screen
        Utils.allowEscapeToClose(window, window.getPanel());

        this.user = user;
        createWindow();
    }

    public void createWindow() {
        var editProfileBtn = new RButton("Edit user profile", () -> new EditUserProfileUI(user));
        var contactBtn = new RButton("Contact the developers", () -> new ContactDevelopersUI(user));
        var deleteBtn = new RButton("Delete account", () -> new DeleteAccountUI(user));
        var closeBtn = new RButton("Close", () -> window.dispatchEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSING)));

        window.addComponents(editProfileBtn, contactBtn, deleteBtn, closeBtn);
    }

}
