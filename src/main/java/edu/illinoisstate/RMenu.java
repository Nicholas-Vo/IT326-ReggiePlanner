package edu.illinoisstate;

import javax.swing.JMenu;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class RMenu extends JMenu {

    public RMenu(String text) {
        super.setText(text);
    }

    public void addButtonClickAction(Runnable callable) {
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
