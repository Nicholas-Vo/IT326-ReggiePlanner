package edu.illinoisstate.gui;

import edu.illinoisstate.ReggiePlanner;

import javax.swing.*;

/**
 * This class is executed when a user successfully signs in
 */
public class Home extends ProgramWindow {
    private final ReggiePlanner program = ReggiePlanner.getProgram();
    protected final JFrame window = new JFrame();
    protected final JPanel panel = new JPanel();

    public Home() {
        window.setSize(600, 600);
        window.setTitle("ReggiePlanner v" + program.VERSION);
    }

    @Override
    public void execute() {

    }

}
