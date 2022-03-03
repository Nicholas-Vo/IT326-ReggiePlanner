package edu.illinoisstate;

import edu.illinoisstate.gui.MainProgramWindow;

import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final List<UserAccount> list = new ArrayList<>();

    public static void main(String[] args) {
        var gui = new MainProgramWindow();
        gui.execute();
    }
}
