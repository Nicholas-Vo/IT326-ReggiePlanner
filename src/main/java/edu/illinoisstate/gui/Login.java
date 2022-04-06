package edu.illinoisstate.gui;

import edu.illinoisstate.RButton;
import edu.illinoisstate.RWindow;
import edu.illinoisstate.UserAccount;
import edu.illinoisstate.database.Database;
import edu.illinoisstate.utils.HintPasswordTextBox;
import edu.illinoisstate.utils.HintTextBox;
import edu.illinoisstate.utils.Security;
import edu.illinoisstate.utils.Utils;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.WindowEvent;

public class Login {
    private final RWindow window = new RWindow("Welcome back!");
    private final JFrame mainProgramWindow;

    public Login(JFrame mainProgramWindow) {
        window.setSize(400, 225);
        window.setLocationRelativeTo(null);
        Utils.allowEscapeToClose(window, window.getPanel());
        this.mainProgramWindow = mainProgramWindow;

        createWindow();
    }

    public void createWindow() {
        JTextField username = new HintTextBox("username", 15, .5f);
        JTextField password = new HintPasswordTextBox("password", 15, .5f);

        RButton loginButton = new RButton("Login");
        loginButton.addActionListener(e -> {
            Database database = Database.getInstance();

            if (!database.getUsernamesList().contains(username.getText())) {
                JOptionPane.showMessageDialog(window, "Incorrect username or password.");
                return;
            }

            UserAccount user = database.getUserAccount(username.getText());

            if (user.getTemporaryPasswordHash() != null) {
                if (user.getTemporaryPasswordHash().equalsIgnoreCase(Security.hash(password.getText()))) {
                    new ForcePasswordChange(user, window);
                    return;
                }
            }

            if (!user.authenticate(password.getText())) {
                JOptionPane.showMessageDialog(window, "Incorrect username or password.");
                return;
            }

//            if (user.mustChangePassword()) {
//                new ForcePasswordChange(user, window);
//                return;
//            }

            // Close this window and the main program window now that we're logged in, then open up home page
            window.dispatchEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSING));
            mainProgramWindow.dispatchEvent(new WindowEvent(mainProgramWindow, WindowEvent.WINDOW_CLOSING));
            new UserHomePage(user);
        });

        window.getRootPane().setDefaultButton(loginButton); // Allows Enter key to submit
        window.addComponents(username, password, loginButton);
    }

}
