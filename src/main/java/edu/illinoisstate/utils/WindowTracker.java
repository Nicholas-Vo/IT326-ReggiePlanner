package edu.illinoisstate.utils;

import java.awt.Window;
import java.util.HashSet;
import java.util.Set;

/**
 * allows us to keep track of any "active" windows (i.e. windows that should
 * be closed when the user logs off) so we can close them all at once
 */
public class WindowTracker {
    private static final Set<Window> activeWindows = new HashSet<>();

    public static void addToActiveWindows(Window w) {
        activeWindows.add(w);
    }

    public static void closeAllActiveWindows() {
        activeWindows.forEach(w -> w.setVisible(false));
    }

}
