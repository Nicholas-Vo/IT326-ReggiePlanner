package edu.illinoisstate.gui;

import edu.illinoisstate.UserAccount;
import edu.illinoisstate.database.Database;
import edu.illinoisstate.utils.Email;
import edu.illinoisstate.utils.HintTextBox;
import edu.illinoisstate.utils.Utils;

import javax.swing.*;
import java.awt.event.WindowEvent;

/**
 * This window appears when the user selects the "Forgot password" button on the main window.
 */
public class ForgotPassword {
    private final JDialog window = new JDialog();
    private final JPanel panel = new JPanel();

    public ForgotPassword() {
        window.setSize(400, 300);
        window.setLocationRelativeTo(null); // Center the window on the screen
        window.setTitle("Recover password");
        window.setModal(true); // this prevents use of other windows
        window.setResizable(false);
        window.setIconImage(Utils.getImage("qmark.png"));
        Utils.allowEscapeToClose(window, panel);

        createWindow();
    }

    public void createWindow() {
        JTextField username = new HintTextBox("username", 15);
        JButton resetButton = new JButton("Recover");
        window.getRootPane().setDefaultButton(resetButton); // Allows Enter key to submit

        resetButton.addActionListener(e -> {
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

            window.dispatchEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSING)); // Close this window after success
        });

        panel.add(username);
        panel.add(resetButton);

        window.add(panel);
        window.setVisible(true);
    }

}
