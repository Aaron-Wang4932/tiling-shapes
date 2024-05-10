package org.example.panels.buy;

import org.example.listeners.MouseHandler;
import org.example.panels.GradientPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class BuyPanel extends GradientPanel {
    JTabbedPane tabbedPane;
    JButton backBtn;
    boolean unitPriceSet = false;
    MouseHandler mouseHandler = new MouseHandler();
    public BuyPanel(ActionListener gui) {
        super(new Color(0x3E84C2), new Color(0xB093E0), GradientPanel.DIAGONAL_FILL);
        ToolTipManager.sharedInstance().setInitialDelay(0);

        this.setLayout(new FlowLayout(FlowLayout.RIGHT, 75, 15));

        tabbedPane = new JTabbedPane();
        tabbedPane.setPreferredSize(new Dimension(750,530));
        tabbedPane.setFont(new Font("Century Gothic", Font.BOLD | Font.ITALIC, 18));
        tabbedPane.addTab("test", new JPanel());

        backBtn = new JButton("Home");
        backBtn.setActionCommand("Home");
        backBtn.setPreferredSize(new Dimension(125, 40));
        backBtn.setFocusable(false);
        backBtn.setFont(new Font("Century Gothic", Font.BOLD | Font.ITALIC, 26));
        backBtn.setForeground(new Color(0xffffff));
        backBtn.setContentAreaFilled(false);
        backBtn.setBorder(BorderFactory.createLineBorder(Color.black, 3, true));
        backBtn.addMouseListener(mouseHandler);
        backBtn.addActionListener(gui);

        this.add(tabbedPane);
        this.add(backBtn);

    }

    public void resetBorder() {
        backBtn.setBorder(BorderFactory.createLineBorder(Color.black, 3, true));
    }
}
