package edu.illinoisstate;

import javax.swing.JMenu;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class RMenu extends JMenu {
    private int timesClicked = 0;

    public RMenu(String text) {
        super.setText(text);
    }

    public int timesClicked() {
        return timesClicked;
    }

    public void addButtonClickAction(Runnable callable) {
        timesClicked++;
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    callable.run();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }

}
