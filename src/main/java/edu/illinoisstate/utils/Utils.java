package edu.illinoisstate.utils;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Window;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.net.URL;
import java.util.Objects;

/**
 * Helper class
 */
public class Utils {
    /**
     * add white space to GUI
     *
     * @param panel the panel
     * @param size  amount of space to add
     */
    public static void addWhiteSpace(JPanel panel, int size) {
        panel.add(new JToolBar.Separator(new Dimension(20, size)));
    }

    /**
     * Set a window to close when the Esc key is pressed
     *
     * @param window: The window to close
     */
    public static void allowEscapeToClose(Window window, JPanel panel) {
        panel.registerKeyboardAction(e -> {
            window.dispatchEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSING));
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    /**
     * Get an image from file
     *
     * @param name name of the image
     * @return the Image object
     */
    public static Image getImage(String name) {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        URL url = classloader.getResource("images/" + name);
        return new ImageIcon(Objects.requireNonNull(url)).getImage();
    }

}
