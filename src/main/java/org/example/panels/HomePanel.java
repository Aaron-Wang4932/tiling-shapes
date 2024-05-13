package org.example.panels;

import org.example.listeners.MouseHandler;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class HomePanel extends GradientPanel {
    JLabel title;
    JButton buyBtn, cartBtn, settingsBtn;
    MouseHandler mouseHandler = new MouseHandler();
    public HomePanel(ActionListener gui) {
        super(new Color(0x3E84C2), new Color(0xB093E0), GradientPanel.DIAGONAL_FILL);
        this.setLayout(null);

        title = new JLabel("best tile store yipeee", JLabel.CENTER);
        title.setFont(new Font("Century Gothic", Font.BOLD | Font.ITALIC, 32));
        title.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(0xB093E0), 4, true),
                "welcome to",
                TitledBorder.LEFT,
                TitledBorder.TOP,
                new Font("Century Gothic", Font.BOLD | Font.ITALIC, 16),
                new Color(0xebddff)));
        title.setForeground(Color.white);

        buyBtn = new JButton("buy tiles");
        buyBtn.setActionCommand("Buy Tiles");
        buyBtn.setFocusable(false);
        buyBtn.setFont(new Font("Century Gothic", Font.BOLD | Font.ITALIC, 32));
        buyBtn.setForeground(new Color(0xffffff));
        buyBtn.setContentAreaFilled(false);
        buyBtn.setBorder(BorderFactory.createLineBorder(Color.black, 3, true));
        buyBtn.addMouseListener(mouseHandler);
        buyBtn.addActionListener(gui);

        cartBtn = new JButton("view cart");
        cartBtn.setActionCommand("View Cart");
        cartBtn.setFocusable(false);
        cartBtn.setFont(new Font("Century Gothic", Font.BOLD | Font.ITALIC, 32));
        cartBtn.setForeground(new Color(0xffffff));
        cartBtn.setContentAreaFilled(false);
        cartBtn.setBorder(BorderFactory.createLineBorder(Color.black, 3, true));
        cartBtn.addMouseListener(mouseHandler);
        cartBtn.addActionListener(gui);

        settingsBtn = new JButton("settings");
        settingsBtn.setActionCommand("Settings");
        settingsBtn.setFocusable(false);
        settingsBtn.setFont(new Font("Century Gothic", Font.BOLD | Font.ITALIC, 32));
        settingsBtn.setForeground(new Color(0xffffff));
        settingsBtn.setContentAreaFilled(false);
        settingsBtn.setBorder(BorderFactory.createLineBorder(Color.black, 3, true));
        settingsBtn.addMouseListener(mouseHandler);
        settingsBtn.addActionListener(gui);

        this.add(title);
        title.setBounds(250, 20, 400, 75);

        this.add(buyBtn);
        buyBtn.setBounds(250, 200, 400, 50);

        this.add(cartBtn);
        cartBtn.setBounds(250, 300, 400, 50);

        this.add(settingsBtn);
        settingsBtn.setBounds(250, 400, 400, 50);
    }
    public void resetBorder() {
        buyBtn.setBorder(BorderFactory.createLineBorder(Color.black, 3, true));
        cartBtn.setBorder(BorderFactory.createLineBorder(Color.black, 3, true));
        settingsBtn.setBorder(BorderFactory.createLineBorder(Color.black, 3, true));
    }
}
