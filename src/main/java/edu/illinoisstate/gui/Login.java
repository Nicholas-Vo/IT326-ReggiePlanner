package edu.illinoisstate.gui;

import edu.illinoisstate.ReggieButton;
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
        JTextField username = new HintTextBox("username", 15, Component.CENTER_ALIGNMENT);
        JTextField password = new HintPasswordTextBox("password", 15, Component.CENTER_ALIGNMENT);

        ReggieButton loginButton = new ReggieButton("Login",
                () -> verifyLogin(username.getText()), Component.CENTER_ALIGNMENT);

        window.getRootPane().setDefaultButton(loginButton); // Allows Enter key to submit
        window.addComponents(Arrays.asList(username, password, loginButton));
    }

    /*
    The logic for verifying the login
     */
    private void verifyLogin(String username) {
        Database database = Database.getInstance();

        if (!database.getUsernamesList().contains(username)) {
            JOptionPane.showMessageDialog(window, "Incorrect username or password.");
            return;
        }

        UserAccount user = database.getUserAccount(username);

        String storedHash = user.getPasswordHash(); // Stored hash from DB
        String generatedHash = Utils.hash(username); // Hash user input to check against DB

        if (!storedHash.equalsIgnoreCase(generatedHash)) {
            JOptionPane.showMessageDialog(window, "Incorrect username or password.");
            return;
        }

        // Close this window and the main program window now that we're logged in, then open up home page
        window.dispatchEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSING));
        mainProgramWindow.dispatchEvent(new WindowEvent(mainProgramWindow, WindowEvent.WINDOW_CLOSING));
        new UserHomePage(user);
    }


}
