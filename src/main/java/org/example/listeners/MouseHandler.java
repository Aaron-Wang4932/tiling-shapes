package org.example.listeners;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseHandler implements MouseListener {
    @Override
    public void mouseEntered(MouseEvent e) {
        JButton btn = (JButton) e.getSource();
        btn.setBorder(BorderFactory.createLineBorder(Color.pink, 3, true));
    }

    @Override
    public void mouseExited(MouseEvent e) {
        JButton btn = (JButton) e.getSource();
        btn.setBorder(BorderFactory.createLineBorder(Color.black, 3, true));
    }
    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {
        JButton btn = (JButton) e.getSource();
        btn.setBorder(BorderFactory.createLineBorder(new Color(0xff6868), 3, true));
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        JButton btn = (JButton) e.getSource();
        btn.setBorder(BorderFactory.createLineBorder(Color.pink, 3, true));
    }
}
