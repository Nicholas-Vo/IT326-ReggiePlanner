package edu.illinoisstate.gui;

import edu.illinoisstate.RButton;
import edu.illinoisstate.RWindow;
import edu.illinoisstate.UserAccount;
import edu.illinoisstate.database.Database;
import edu.illinoisstate.database.DatabaseHandler;
import edu.illinoisstate.email.EmailHandler;
import edu.illinoisstate.utils.HintTextBox;
import edu.illinoisstate.utils.Utils;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.WindowEvent;

/**
 * This window appears when the user selects the "Forgot password" button on the main window.
 */
public class ForgotPassword {
    private final RWindow window = new RWindow("Recover password");

    public ForgotPassword() {
        window.setSize(400, 300);
        window.setLocationRelativeTo(null);
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
            EmailHandler emailHandler = new EmailHandler();

            emailHandler.sendPasswordReset(user);

            JOptionPane.showMessageDialog(window,
                    "A password recovery message has been sent to the email associated with this account: "
                            + "*******" + user.email().substring(user.email().indexOf("@")));

            window.dispatchEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSING));
        });

        window.getRootPane().setDefaultButton(resetButton); // Allows Enter key to submit
        window.addComponents(username, resetButton);
    }

}
