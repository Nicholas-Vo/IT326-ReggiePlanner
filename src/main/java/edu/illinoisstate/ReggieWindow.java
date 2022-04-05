package edu.illinoisstate;

import edu.illinoisstate.utils.Utils;
import edu.illinoisstate.utils.WindowTracker;

import javax.swing.*;
import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.util.List;

public class ReggieWindow extends JDialog {
    private final JPanel panel = new JPanel();

    public ReggieWindow(String title) {
        super.setTitle(title);
        super.setLocationRelativeTo(null); // Set window to center of screen
        super.setResizable(false); // Don't allow user to change window size
        super.setModal(true); // Don't allow user to click off of window
        super.setIconImage(Utils.getImage("reggie.png")); // Set default icon to Reggie

        WindowTracker.addToActiveWindows(this); // Add to list of "active" windows

        /*
        register keyboard action for Esc key; Sets it to close window
         */
        this.panel.registerKeyboardAction(e -> {
            dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    /**
     * Takes in a list of components to add to the panel
     * @param component 0-* components
     */
    public void addComponents(Component ... component) {
        List.of(component).forEach(panel::add);
        this.add(panel); // add panel to window
        this.setVisible(true);
    }

    public JPanel getPanel() {
        return panel;
    }
}
