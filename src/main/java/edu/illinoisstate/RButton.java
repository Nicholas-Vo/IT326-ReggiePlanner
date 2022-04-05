package edu.illinoisstate;

import javax.swing.*;

public class RButton extends JButton {

    public RButton(String text, Runnable runnable) {
        super.setText(text);

        super.addActionListener(e -> runnable.run());
    }

    public RButton(String text, Runnable runnable, float alignment) {
        super.setText(text);
        super.setAlignmentX(alignment);

        super.addActionListener(e -> runnable.run());
    }

    /*
     * Overloaded constructor to allow button w/o runnable
     */
    public RButton(String text) {
        super.setText(text);
    }

}
