package edu.illinoisstate.utils;

import java.awt.Window;
import java.util.HashSet;
import java.util.Set;

public class WindowTracker {
    private static final Set<Window> activeWindows = new HashSet<>();

    public static void addToActiveWindows(Window w) {
        activeWindows.add(w);
    }

    public static void closeAllActiveWindows() {
        activeWindows.forEach(w -> w.setVisible(false));
    }

}
