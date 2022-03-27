package edu.illinoisstate.gui;

import edu.illinoisstate.UserAccount;
import edu.illinoisstate.database.Database;
import edu.illinoisstate.utils.HintTextBox;

import javax.swing.*;
import java.awt.event.WindowEvent;

import static edu.illinoisstate.utils.Utils.hash;

public class Login {
    protected final JFrame window = new JFrame();
    protected final JPanel panel = new JPanel();
    private final JFrame mainProgramWindow;

    public Login(JFrame mainProgramWindow) {
        window.setSize(500, 300);
        window.setTitle("Login");

        this.mainProgramWindow = mainProgramWindow;

        createWindow();
    }

    public void createWindow() {
        JTextField username = new HintTextBox("username", 15);
        panel.add(username);
        JTextField password = new JPasswordField("", 15);
        panel.add(password);

        JButton loginButton = new JButton("Login");
        window.getRootPane().setDefaultButton(loginButton); // Allows Enter key to submit

        String INCORRECT_MSG = "Incorrect username or password.";

        loginButton.addActionListener(e -> {
            Database database = Database.getInstance();

            if (!database.getUsernamesList().contains(username.getText())) {
                JOptionPane.showMessageDialog(window, INCORRECT_MSG);
                return;
            }

            UserAccount user = database.getUserAccount(username.getText());

            String storedHash = user.getPasswordHash();
            String generatedHash = hash(password.getText());

            if (!storedHash.equalsIgnoreCase(generatedHash)) {
                JOptionPane.showMessageDialog(window, INCORRECT_MSG);
                return;
            }

            /*
            Close this window and the main program window now that we're logged in
             */
            window.dispatchEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSING));
            mainProgramWindow.dispatchEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSING));
            new UserHomePage(user);
        });

        panel.add(username);
        panel.add(password);
        panel.add(loginButton);

        window.add(panel);
        window.setVisible(true);
    }

}
