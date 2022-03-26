package edu.illinoisstate.gui;

import edu.illinoisstate.Email;
import edu.illinoisstate.SecurityHandler;
import edu.illinoisstate.UserAccount;
import edu.illinoisstate.database.Database;

import javax.swing.*;
import java.awt.event.WindowEvent;

/**
 * This window appears when the user selects the "Forgot password" button on the main window.
 */
public class ForgotPassword extends ProgramWindow {
    protected final JFrame window = new JFrame();
    protected final JPanel panel = new JPanel();

    public ForgotPassword() {
        window.setSize(600, 600);
        window.setTitle("Recover password");

        createWindow();
    }

    public void createWindow() {
        JTextField username = new JTextField("username", 15);
        panel.add(username);

        JButton resetButton = new JButton("Recover");

        resetButton.addActionListener(e -> {
            if (Database.getInstance().getUser(username.getText()) == null) {
                JOptionPane.showMessageDialog(window, "Invalid username. Try again?");
                return;
            }

            UserAccount user = Database.getInstance().getUser(username.getText());

            if (!SecurityHandler.getInstanace().isValidEmail(user.email())) {
                JOptionPane.showMessageDialog(window, "No email associated with that account.");
                return;
            }

            /*
            todo: placeholder make this email class better
             */
            Email.sendForgotPassword(user.email());
            JOptionPane.showMessageDialog(window,
                    "A password recovery message has been sent to the email associated with this account.");

            /*
            Close this window after success
             */
            window.dispatchEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSING));
        });

        panel.add(username);
        panel.add(resetButton);

        window.add(panel);
        window.setVisible(true);
    }

}