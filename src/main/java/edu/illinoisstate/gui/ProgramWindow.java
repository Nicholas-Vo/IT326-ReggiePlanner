package edu.illinoisstate.gui;

import edu.illinoisstate.ReggiePlanner;

import javax.swing.*;

/**
 * This class represents any window within the program.
 */
public abstract class ProgramWindow {
    protected final ReggiePlanner program;
    protected final JFrame window = new JFrame();
    protected final JPanel panel = new JPanel();

    protected ProgramWindow(ReggiePlanner program, int width, int height, String title) {
        this.program = program;
        window.setSize(width, height);
        window.setTitle(title);
    }

    public abstract void execute();
}
