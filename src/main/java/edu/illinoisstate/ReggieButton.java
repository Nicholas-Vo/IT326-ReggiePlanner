package edu.illinoisstate;

import javax.swing.*;

public class ReggieButton extends JButton {

    public ReggieButton(String text, Runnable runnable) {
        super.setText(text);

        super.addActionListener(e -> runnable.run());
    }

    public ReggieButton(String text, Runnable runnable, float alignment) {
        super.setText(text);
        super.setAlignmentX(alignment);

        super.addActionListener(e -> runnable.run());
    }

    /*
     * Overloaded constructor to allow button w/o runnable
     */
    public ReggieButton(String text) {
        super.setText(text);
    }

}
