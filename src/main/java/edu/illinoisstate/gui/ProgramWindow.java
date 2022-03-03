package edu.illinoisstate.gui;

import javax.swing.*;

public abstract class ProgramWindow {
    protected final JFrame window = new JFrame();
    protected final JPanel panel = new JPanel();

    public abstract void execute();
}
