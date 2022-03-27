package edu.illinoisstate.utils;

import javax.swing.*;
import java.awt.*;

/**
 * Similar to HintTextBox, but for passwords
 */
public class HintPasswordTextBox extends JPasswordField {
    private final String hintMessage;

    public HintPasswordTextBox(String hint, int columns) {
        hintMessage = hint;
        super.setColumns(columns);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        if (!(getText().length() == 0)) {
            return;
        }

        Graphics2D graphics = (Graphics2D) g;

        graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        FontMetrics fm = g.getFontMetrics();
        int c0 = getBackground().getRGB();
        int c1 = getForeground().getRGB();
        int m = 0xfefefefe;
        int c2 = ((c0 & m) >>> 1) + ((c1 & m) >>> 1);
        g.setColor(new Color(c2, true));
        g.drawString(hintMessage, getInsets().left, getHeight() / 2 + fm.getAscent() / 2 - 2);
    }

}