package edu.illinoisstate.gui;

import edu.illinoisstate.UserAccount;
import edu.illinoisstate.database.Database;
import edu.illinoisstate.utils.HintPasswordTextBox;
import edu.illinoisstate.utils.HintTextBox;
import edu.illinoisstate.utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;

public class Login extends LoginWindow {
    private final JDialog window = new JDialog();
    private final JPanel panel = new JPanel();
    private final JFrame mainProgramWindow;

    public Login(JFrame mainProgramWindow) {
        window.setSize(500, 225);
        window.setLocationRelativeTo(null); // Center the window on the screen
        window.setTitle("Welcome back!");
        window.setModal(true); // this prevents use of other windows
        window.setIconImage(Utils.getImage("reggie.png"));;

        this.mainProgramWindow = mainProgramWindow;

        createWindow();
    }

    public void createWindow() {
        JTextField username = new HintTextBox("username", 15);
        username.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(username);

        JTextField password = new HintPasswordTextBox("password", 15);
        password.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(password);

        JButton loginButton = new JButton("Login");
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(loginButton);

        window.getRootPane().setDefaultButton(loginButton); // Allows Enter key to submit

        String INCORRECT_MSG = "Incorrect username or password.";

        loginButton.addActionListener(e -> {
            Database database = Database.getInstance();

            if (!database.getUsernamesList().contains(username.getText())) {
                JOptionPane.showMessageDialog(window, INCORRECT_MSG);
                return;
            }

            UserAccount user = database.getUserAccount(username.getText());

            String storedHash = user.getPasswordHash(); // Stored hash from DB
            String generatedHash = Utils.hash(password.getText()); // Hash user input to check against DB

            if (!storedHash.equalsIgnoreCase(generatedHash)) {
                JOptionPane.showMessageDialog(window, INCORRECT_MSG);
                return;
            }

            /*
            Close this window and the main program window now that we're logged in
             */
            window.dispatchEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSING));
            mainProgramWindow.dispatchEvent(new WindowEvent(mainProgramWindow, WindowEvent.WINDOW_CLOSING));
            new UserHomePage(user);
        });

        window.add(panel);
        window.setVisible(true);
    }

}
