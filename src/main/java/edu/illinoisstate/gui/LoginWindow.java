package edu.illinoisstate.gui;


import java.awt.*;
import java.awt.event.WindowEvent;
import java.util.HashSet;
import java.util.Set;

public abstract class LoginWindow {
    private final Set<Window> activeWindows = new HashSet<>();

    public void addToActiveWindows(Window window) {
        activeWindows.add(window);
    }

    public void closeAllActiveWindows() {
        activeWindows.forEach(w -> w.dispatchEvent(new WindowEvent(w, WindowEvent.WINDOW_CLOSING)));
    }

}
