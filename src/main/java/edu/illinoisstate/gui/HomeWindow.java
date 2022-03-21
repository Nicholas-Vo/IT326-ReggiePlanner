package edu.illinoisstate.gui;

import edu.illinoisstate.ReggiePlanner;

/**
 * This class is executed when a user successfully signs in
 */
public class HomeWindow extends ProgramWindow {

    public HomeWindow(ReggiePlanner program) {
        super(program, 600, 600, "ReggiePlanner v" + program.VERSION);
    }

    @Override
    public void execute() {

    }

}
