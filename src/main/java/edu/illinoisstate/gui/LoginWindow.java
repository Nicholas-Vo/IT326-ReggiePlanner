package edu.illinoisstate.gui;


import java.awt.*;
import java.util.HashSet;
import java.util.Set;

public abstract class LoginWindow {
    private final Set<Window> activeWindows = new HashSet<>();

    public void addToActiveWindows(Window window) {
        activeWindows.add(window);
        System.out.println("Added " + window.getName());
    }

    public void closeAllActiveWindows() {
        activeWindows.forEach(w -> {
            w.dispose();
            System.out.println("Removed " + w.getName());
        });
    }

}
