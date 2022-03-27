package edu.illinoisstate.gui;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.util.HashSet;
import java.util.Set;

/**
 * This class keeps track of every window that should be closed when
 * a user logs out; can call closeAllActiveWindows() to do so
 */
public abstract class LoginWindow {
    private final Set<JFrame> activeWindows = new HashSet<>();

    public void addToActiveWindows(JFrame window) {
        activeWindows.add(window);
    }

    public void closeAllActiveWindows() {
        activeWindows.forEach(w -> w.dispatchEvent(new WindowEvent(w, WindowEvent.WINDOW_CLOSING)));
    }
}
