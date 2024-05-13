package org.example.panels;

import org.example.listeners.MouseHandler;
import org.example.shapes.Shape;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class SettingsPanel extends GradientPanel {
    JFrame gui;
    JButton home;
    public SettingsPanel(ActionListener gui) {
        super(new Color(0x3E84C2), new Color(0xB093E0), GradientPanel.DIAGONAL_FILL);
        this.gui = (JFrame) gui;
        this.setLayout(null);

        JLabel title = new JLabel("best tile store yipeee", JLabel.CENTER);
        title.setFont(new Font("Century Gothic", Font.BOLD, 24));
        title.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(0xB093E0), 4, true),
                "welcome to",
                TitledBorder.LEFT,
                TitledBorder.TOP,
                new Font("Century Gothic", Font.BOLD, 12),
                new Color(0xebddff)));
        title.setForeground(Color.white);

        JLabel prompt = new JLabel("enter unit price ($/m²): ");
        prompt.setFont(new Font("Century Gothic", Font.BOLD, 26));
        prompt.setForeground(Color.white);

        JTextField promptField = new JTextField();
        promptField.setFont(new Font("Century Gothic", Font.BOLD, 18));
        promptField.setMargin(new Insets(5, 5, 5, 5));
        promptField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                if(!Character.isDigit(e.getKeyChar()) && e.getKeyChar() != '.' && e.getKeyChar() != KeyEvent.VK_BACK_SPACE) {
                    e.consume();
                    Toolkit.getDefaultToolkit().beep();
                }
                try {
                    Double.parseDouble(((JTextField)e.getSource()).getText());
                } catch (NumberFormatException ignored) {
                    ((JTextField)e.getSource()).setText("");
                }
            }
            @Override
            public void keyPressed(KeyEvent e) {}
            @Override
            public void keyReleased(KeyEvent e) {}
        });

        JTextField output = new JTextField();
        output.setFont(new Font("Century Gothic", Font.BOLD, 18));
        output.setMargin(new Insets(5, 5, 5, 5));
        output.setEditable(false);

        JButton submit = new JButton("submit");
        submit.setForeground(new Color(0x7E5EFF));
        submit.setFocusable(false);
        submit.setFont(new Font("Century Gothic", Font.BOLD, 26));
        submit.setContentAreaFilled(false);
        submit.setBorder(BorderFactory.createLineBorder(Color.black, 3, true));
        submit.addMouseListener(new MouseHandler());
        submit.addActionListener(e -> {
            if(promptField.getText().isEmpty()) {
                output.setText("enter a value!");
                return;
            }
            double value = Math.round(Double.parseDouble(promptField.getText()) * 100) / 100.0;
            if(value <= 0) {
                output.setText("nothing in this world is free.");
                return;
            }
            Shape.setUnitPrice(value);
            output.setText("unit price set to " + value + " $/m².");
            promptField.setText("");
        });

        home = new JButton("home");
        home.setActionCommand("home setting");
        home.setForeground(new Color(0x7E5EFF));
        home.setFocusable(false);
        home.setFont(new Font("Century Gothic", Font.BOLD, 26));
        home.setContentAreaFilled(false);
        home.setBorder(BorderFactory.createLineBorder(Color.black, 3, true));
        home.addMouseListener(new MouseHandler());
        home.addActionListener(gui);



        this.add(title);
        title.setBounds(300, 10, 300, 65);

        this.add(prompt);
        prompt.setBounds(120, 100, 350, 50);

        this.add(promptField);
        promptField.setBounds(550, 100, 200, 50);

        this.add(output);
        output.setBounds(120, 200, 630, 75);

        this.add(submit);
        submit.setBounds(120, 325, 630, 50);

        this.add(home);
        home.setBounds(585, 530, 270, 50);
    }

    public void resetBorder() {
        home.setBorder(BorderFactory.createLineBorder(Color.black, 3, true));
    }
}
