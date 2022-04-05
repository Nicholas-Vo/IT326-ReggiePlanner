package edu.illinoisstate.gui;

import edu.illinoisstate.RButton;
import edu.illinoisstate.ReggieWindow;
import edu.illinoisstate.UserAccount;
import edu.illinoisstate.database.Database;
import edu.illinoisstate.utils.HintPasswordTextBox;
import edu.illinoisstate.utils.HintTextBox;
import edu.illinoisstate.utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.util.Arrays;

public class Login {
    private final ReggieWindow window = new ReggieWindow("Welcome back!");
    private final JFrame mainProgramWindow;

    public Login(JFrame mainProgramWindow) {
        window.setSize(400, 225);
        this.mainProgramWindow = mainProgramWindow;

        createWindow();
    }

    public void createWindow() {
        JTextField username = new HintTextBox("username", 15, .5f);
        JTextField password = new HintPasswordTextBox("password", 15, .5f);

        RButton loginButton = new RButton("Login", () -> {
            Database database = Database.getInstance();

            if (!database.getUsernamesList().contains(username.getText())) {
                JOptionPane.showMessageDialog(window, "Incorrect username or password.");
                return;
            }

            UserAccount user = database.getUserAccount(username.getText());

            String storedHash = user.getPasswordHash(); // Stored hash from DB
            String generatedHash = Utils.hash(username.getText()); // Hash user input to check against DB

            if (!storedHash.equalsIgnoreCase(generatedHash)) {
                JOptionPane.showMessageDialog(window, "Incorrect username or password.");
                return;
            }

            // Close this window and the main program window now that we're logged in, then open up home page
            window.dispatchEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSING));
            mainProgramWindow.dispatchEvent(new WindowEvent(mainProgramWindow, WindowEvent.WINDOW_CLOSING));
            new UserHomePage(user);
        }, .5f);

        window.getRootPane().setDefaultButton(loginButton); // Allows Enter key to submit
        window.addComponents(username, password, loginButton);
    }

}
