package edu.illinoisstate.gui;

import edu.illinoisstate.RButton;
import edu.illinoisstate.ReggieWindow;
import edu.illinoisstate.UserAccount;
import edu.illinoisstate.database.Database;
import edu.illinoisstate.utils.Email;
import edu.illinoisstate.utils.HintTextBox;
import edu.illinoisstate.utils.Utils;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.util.Arrays;

/**
 * This window appears when the user selects the "Forgot password" button on the main window.
 */
public class ForgotPassword {
    private final ReggieWindow window = new ReggieWindow("Recover password");

    public ForgotPassword() {
        window.setSize(400, 300);
        window.setIconImage(Utils.getImage("qmark.png"));

        createWindow();
    }

    public void createWindow() {
        JTextField username = new HintTextBox("username", 15);
        RButton resetButton = new RButton("Recover", () -> {
            Database database = Database.getInstance();

            if (!database.getUsernamesList().contains(username.getText())) {
                JOptionPane.showMessageDialog(window, "Invalid username. Try again?");
                return;
            }

            UserAccount user = database.getUserAccount(username.getText());
            Email.sendForgotPassword(user.email());

            JOptionPane.showMessageDialog(window,
                    "A password recovery message has been sent to the email associated with this account: "
                            + "*******" + user.email().substring(user.email().indexOf("@")));

            window.dispatchEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSING));
        });

        window.getRootPane().setDefaultButton(resetButton); // Allows Enter key to submit
        window.addComponents(username, resetButton);
    }

}
