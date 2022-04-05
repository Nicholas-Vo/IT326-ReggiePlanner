package edu.illinoisstate;

import edu.illinoisstate.utils.Utils;
import edu.illinoisstate.utils.WindowTracker;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class RWindow extends JDialog {
    private final JPanel panel = new JPanel();

    public RWindow(String title) {
        super.setTitle(title);
        super.setResizable(false); // Don't allow user to change window size
        super.setModal(true); // Don't allow user to click off of window
        super.setIconImage(Utils.getImage("reggie.png")); // Set default icon to Reggie

        WindowTracker.addToActiveWindows(this); // Add to list of "active" windows
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
