package org.example.panels.buy;

import org.example.listeners.MouseHandler;
import org.example.panels.GradientPanel;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class BuyPanel extends GradientPanel {
    JTabbedPane tabbedPane;
    JButton backBtn;
    boolean unitPriceSet = false;
    JPanel chooseTilePanel, dimensionsPanel, reviewPanel;
    JLabel label = new JLabel(), title, panel1_1, panel1_2;
    JComboBox<String> shapeChooser = new JComboBox<>(new String[]{"arrow", "circle", "donut", "parallelogram", "rectangle", "triangle", "triangular donut"});
    JSpinner quantity = new JSpinner(new SpinnerNumberModel(0, 0, 999, 1));
    MouseHandler mouseHandler = new MouseHandler();
    public BuyPanel(ActionListener gui) {
        super(new Color(0x3E84C2), new Color(0xB093E0), GradientPanel.DIAGONAL_FILL);
        ToolTipManager.sharedInstance().setInitialDelay(0);

        this.setLayout(null);

        chooseTilePanel = new JPanel();
        chooseTilePanel.setLayout(null);
        chooseTilePanel.setBackground(new Color(0xFFFFFF));

        panel1_1 = new JLabel("choose shape");
        panel1_1.setFont(new Font("Century Gothic", Font.BOLD | Font.ITALIC, 26));

        panel1_2 = new JLabel("choose quantity");
        panel1_2.setFont(new Font("Century Gothic", Font.BOLD | Font.ITALIC, 26));

        chooseTilePanel.add(label);
        label.setBounds(300, 250, 100, 100);

        shapeChooser.setFont(new Font("Century Gothic", Font.BOLD | Font.ITALIC, 26));
        shapeChooser.addActionListener(e -> {
            System.out.print(shapeChooser.getSelectedItem());
            ImageIcon img = new ImageIcon("src/main/resources/" + shapeChooser.getSelectedItem() + ".png");
            img = new ImageIcon(img.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH));
            label.setIcon(img);
        });

        quantity.setFont(new Font("Century Gothic", Font.BOLD | Font.ITALIC, 26));
        ((JSpinner.DefaultEditor) quantity.getEditor()).getTextField().setEditable(false);


        chooseTilePanel.add(panel1_1);
        panel1_1.setBounds(50, 75, 200, 50);
        chooseTilePanel.add(panel1_2);
        panel1_2.setBounds(50, 175, 275, 50);
        chooseTilePanel.add(shapeChooser);
        shapeChooser.setBounds(400, 77, 250, 45);
        chooseTilePanel.add(quantity);
        quantity.setBounds(400, 175, 250, 45);


        title = new JLabel("best tile store yipeee", JLabel.CENTER);
        title.setFont(new Font("Century Gothic", Font.BOLD | Font.ITALIC, 24));
        title.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(0xB093E0), 4, true),
                "welcome to",
                TitledBorder.LEFT,
                TitledBorder.TOP,
                new Font("Century Gothic", Font.BOLD | Font.ITALIC, 12),
                new Color(0xebddff)));
        title.setForeground(Color.white);

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

        tabbedPane = new JTabbedPane();
        tabbedPane.setPreferredSize(new Dimension(750,400));
        tabbedPane.setFont(new Font("Century Gothic", Font.BOLD | Font.ITALIC, 18));
        tabbedPane.addTab("choose Tile", chooseTilePanel);

        this.add(title);
        title.setBounds(300, 20, 300, 65);

        this.add(tabbedPane);
        tabbedPane.setBounds(100, 80, 700, 450);

        this.add(backBtn);
        backBtn.setBounds(650, 550, 150, 50);
    }

    public void resetBorder() {
        backBtn.setBorder(BorderFactory.createLineBorder(Color.black, 3, true));
    }
}
