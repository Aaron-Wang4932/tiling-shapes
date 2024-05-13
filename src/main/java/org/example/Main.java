package org.example;

import org.example.panels.*;
import org.example.panels.BuyPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame implements ActionListener {

    HomePanel homePanel = new HomePanel(this);
    BuyPanel buyPanel = new BuyPanel(this);
    CartPanel cartPanel = new CartPanel(this);
    SettingsPanel settingsPanel = new SettingsPanel(this);

    public Main() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(900, 650));
        this.setResizable(false);
        this.setTitle("eeeee");

        this.add(homePanel);

        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
    public static void main(String[] args) {
        new Main();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Buy Tiles")) swapPanels(homePanel, buyPanel);
        else if(e.getActionCommand().equals("Home")) swapPanels(buyPanel, homePanel);
        else if(e.getActionCommand().equals("home cart")) swapPanels(cartPanel, homePanel);
        else if(e.getActionCommand().equals("home setting")) swapPanels(settingsPanel, homePanel);
        else if(e.getActionCommand().equals("Settings")) swapPanels(homePanel, settingsPanel);
        else if(e.getActionCommand().equals("view cart from buy panel")) {
            swapPanels(buyPanel, cartPanel);
            cartPanel.updateTable();
        }
        else if(e.getActionCommand().equals("View Cart")) {
            swapPanels(homePanel, cartPanel);
            cartPanel.updateTable();
        }

        homePanel.resetBorder();
        buyPanel.resetBorder();
        cartPanel.resetBorder();
        settingsPanel.resetBorder();
    }

    private void swapPanels(JPanel oldPanel, JPanel newPanel) {
        this.remove(oldPanel);
        this.add(newPanel);
        this.repaint();
        this.pack();
//        this.setLocationRelativeTo(null);
    }
}