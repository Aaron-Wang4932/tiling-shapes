package org.example.panels;

import org.example.listeners.MouseHandler;
import org.example.shapes.Shape;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class CartPanel extends GradientPanel {
    JFrame gui;
    DefaultTableModel tableModel;
    JTable table;
    JScrollPane tableScroller;
    JButton home;
    public CartPanel(ActionListener gui) {
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

        tableModel = new DefaultTableModel(new String[]{"shape", "quantity", "total area (m²)", "total price ($)"}, 0);
        updateTable();

        table = new JTable(tableModel) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table.getTableHeader().setFont(new Font("Century Gothic", Font.BOLD, 17));
        table.getTableHeader().setReorderingAllowed(false);

        table.setFont(new Font("Century Gothic", Font.PLAIN, 16));
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        for(int i = 0; i < table.getColumnCount(); i++) table.getColumnModel().getColumn(i).setResizable(false);
        table.setRowHeight(24);

        tableScroller = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        JTextArea output = new JTextArea("make changes or checkout!");
        output.setWrapStyleWord(true);
        output.setForeground(Color.black);
        output.setLineWrap(true);
        output.setEditable(false);
        output.setOpaque(false);
        output.setMargin(new Insets(5, 5, 5, 5));
        output.setFont(new Font("Century Gothic", Font.PLAIN, 16));
        output.setCaretColor(new Color(0, 0, 0, 0));

        JScrollPane outputScroller = new JScrollPane(output, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        outputScroller.setOpaque(false);
        outputScroller.getViewport().setOpaque(false);
        outputScroller.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.black, 3, true),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));

        JTextField removeField = new JTextField();
        removeField.setFont(new Font("Century Gothic", Font.BOLD, 18));
        removeField.setForeground(Color.black);
        removeField.setOpaque(false);
        removeField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.black, 3, true),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        removeField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                if(!Character.isDigit(e.getKeyChar()) && e.getKeyChar() != KeyEvent.VK_BACK_SPACE) {
                    e.consume();
                    Toolkit.getDefaultToolkit().beep();
                }
                try {
                    Integer.parseInt(((JTextField)e.getSource()).getText());
                } catch (NumberFormatException ignored) {
                    ((JTextField)e.getSource()).setText("");
                }
            }
            public void keyPressed(KeyEvent e) {}
            public void keyReleased(KeyEvent e) {}
        });

        JButton remove = new JButton("remove");
        remove.setForeground(Color.white);
        remove.setFocusable(false);
        remove.setFont(new Font("Century Gothic", Font.BOLD, 26));
        remove.setContentAreaFilled(false);
        remove.setBorder(BorderFactory.createLineBorder(Color.black, 3, true));
        remove.addMouseListener(new MouseHandler());
        remove.addActionListener(e -> {
            int selRow = table.getSelectedRow();
            Shape selShape;
            if(selRow == -1) {
                output.setText("select a row to modify!");
                return;
            }

            if(removeField.getText().isEmpty()) {
                output.setText("specify how many items to remove!");
                return;
            }

            int numToRemove = Integer.parseInt(removeField.getText());
            if(numToRemove <= 0) {
                output.setText("you can't remove 0 items lmao");
                return;
            }

            selShape = Shape.shapeList.get(selRow);

            if(selShape.getQuantity() <= numToRemove) {
                Shape.shapeList.remove(selRow);
            } else {
                selShape.setQuantity(selShape.getQuantity() - numToRemove);
            }

            output.setText("your specified tile and amount has been removed.");
            updateTable();
            removeField.setText("");
        });

        JButton removeAll = new JButton("im broke (remove all tiles)");
        removeAll.setForeground(Color.white);
        removeAll.setFocusable(false);
        removeAll.setFont(new Font("Century Gothic", Font.BOLD, 18));
        removeAll.setContentAreaFilled(false);
        removeAll.setBorder(BorderFactory.createLineBorder(Color.black, 3, true));
        removeAll.addMouseListener(new MouseHandler());
        removeAll.addActionListener(e -> {
            Shape.shapeList.clear();
            updateTable();
            output.setText("cleared cart L broke");
        });

        JButton checkout = new JButton("checkout");
        checkout.setForeground(Color.white);
        checkout.setFocusable(false);
        checkout.setFont(new Font("Century Gothic", Font.BOLD, 26));
        checkout.setContentAreaFilled(false);
        checkout.setBorder(BorderFactory.createLineBorder(Color.black, 3, true));
        checkout.addMouseListener(new MouseHandler());
        checkout.addActionListener(e -> {
            if(Shape.shapeList.isEmpty()) {
                output.setText("you can't buy 0 items lol");
                return;
            }

            double subtotal = 0;
            for(Shape s : Shape.shapeList) subtotal += s.getTotalPrice();
            output.setText("mr ellenbogen, e-transfer me money pls :)");
            JOptionPane.showMessageDialog(null,
                    "subtotal: $" + String.format("%.2f", subtotal) +
                            ", total (13% tax): $" + String.format("%.2f", Math.round(subtotal * 1.13 * 100) / 100.0),
                    "money woo",
                    JOptionPane.INFORMATION_MESSAGE);
        });

        home = new JButton("home");
        home.setActionCommand("home cart");
        home.setForeground(new Color(0x7E5EFF));
        home.setFocusable(false);
        home.setFont(new Font("Century Gothic", Font.BOLD, 26));
        home.setContentAreaFilled(false);
        home.setBorder(BorderFactory.createLineBorder(Color.black, 3, true));
        home.addMouseListener(new MouseHandler());
        home.addActionListener(gui);

        this.add(title);
        title.setBounds(300, 10, 300, 65);

        this.add(outputScroller);
        outputScroller.setBounds(585, 100, 270, 160);

        this.add(tableScroller);
        tableScroller.setBounds(40, 100, 515, 480);

        this.add(remove);
        remove.setBounds(585, 290, 125, 50);

        this.add(removeField);
        removeField.setBounds(730, 290, 125, 50);

        this.add(removeAll);
        removeAll.setBounds(585, 370, 270, 50);

        this.add(checkout);
        checkout.setBounds(585, 450, 270, 50);

        this.add(home);
        home.setBounds(585, 530, 270, 50);
    }

    public void resetBorder() {
        home.setBorder(BorderFactory.createLineBorder(Color.black, 3, true));
    }

    public void updateTable() {
        tableModel.setRowCount(0);
        for(Shape shape : Shape.shapeList) {
            tableModel.addRow(new String[] {
                    shape.getShapeName(),
                    String.valueOf(shape.getQuantity()),
                    String.valueOf(Math.round(shape.getArea() * shape.getQuantity() * 100) / 100.0),
                    String.valueOf(String.format("%.2f", shape.getTotalPrice()))
            });
        }
    }
}
