package org.example.panels;

import org.example.listeners.MouseHandler;
import org.example.shapes.Arrow;
import org.example.shapes.Circle;
import org.example.shapes.Donut;
import org.example.shapes.Parallelogram;
import org.example.shapes.Rectangle;
import org.example.shapes.Shape;
import org.example.shapes.Triangle;
import org.example.shapes.TriangleDonut;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class BuyPanel extends GradientPanel {

    JTabbedPane tabbedPane;
    JButton backBtn, addToCart;
    JPanel dimensionArrow, dimensionCircle, dimensionDonut, dimensionQuadrilateral, dimensionTriangle, dimensionTriangleDonut, chooseTilePanel, reviewPanel;
    JTextArea reviewArea;
    String desiredShape;
    int desiredQuantity;
    Shape tempShape;
    MouseHandler mouseHandler = new MouseHandler();
    public BuyPanel(ActionListener gui) {
        super(new Color(0x3E84C2), new Color(0xB093E0), GradientPanel.DIAGONAL_FILL);
        ToolTipManager.sharedInstance().setInitialDelay(0);

        this.setLayout(null);

//------------------- Main Panel ----------------------

        // Main title:
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

        // Back button:
        backBtn = new JButton("home");
        backBtn.setActionCommand("Home");
        backBtn.setForeground(new Color(0x7E5EFF));
        backBtn.setPreferredSize(new Dimension(125, 40));
        backBtn.setFocusable(false);
        backBtn.setFont(new Font("Century Gothic", Font.BOLD, 26));
        backBtn.setContentAreaFilled(false);
        backBtn.setBorder(BorderFactory.createLineBorder(Color.black, 3, true));
        backBtn.addMouseListener(mouseHandler);
        backBtn.addActionListener(gui);

        // Tabs:
        tabbedPane = new JTabbedPane();
        tabbedPane.setPreferredSize(new Dimension(750,400));
        tabbedPane.setFont(new Font("Century Gothic", Font.BOLD, 18));
        tabbedPane.addChangeListener(e -> {
            addToCart.setText("add to cart");
            if(tabbedPane.getSelectedIndex() == 0 && tabbedPane.getTabCount() > 1) {
                tabbedPane.remove(1);
                if(tabbedPane.getTabCount() == 2) tabbedPane.remove(1);
            }
        });

        this.add(title);
        title.setBounds(300, 10, 300, 65);
        this.add(tabbedPane);
        tabbedPane.setBounds(100, 80, 700, 450);
        this.add(backBtn);
        backBtn.setBounds(600, 550, 150, 50);

//------------------------ Tab 1: -----------------------------
        chooseTilePanel = new JPanel();
        chooseTilePanel.setLayout(null);
        chooseTilePanel.setBackground(new Color(0xFFFFFF));

        JLabel label1_1 = new JLabel("choose shape");
        label1_1.setFont(new Font("Century Gothic", Font.BOLD, 26));

        JLabel label1_2 = new JLabel("choose quantity");
        label1_2.setFont(new Font("Century Gothic", Font.BOLD, 26));

        JLabel imgLabel = new JLabel();

        JSpinner quantity = new JSpinner(new SpinnerNumberModel(0, 0, 999, 1));
        quantity.setFont(new Font("Century Gothic", Font.BOLD, 26));
        quantity.setFocusable(false);
        quantity.setBackground(Color.white);
        ((JSpinner.DefaultEditor) quantity.getEditor()).getTextField().setEditable(false);
        ((JSpinner.DefaultEditor) quantity.getEditor()).getTextField().setCaretColor(new Color(0xeeeeee));
        ((JSpinner.DefaultEditor) quantity.getEditor()).getTextField().setHighlighter(null);

        // Arrow: 1, Circle: 2, Donut: 3, Parallelogram/Rectangle: 4, Triangle: 5, Triangular Donut: 6
        JComboBox<String> shapeChooser = new JComboBox<>(new String[]{
                "select an item",
                "arrow",
                "circle",
                "donut",
                "parallelogram",
                "rectangle",
                "triangle",
                "triangular donut"});
        shapeChooser.setFont(new Font("Century Gothic", Font.BOLD, 26));
        shapeChooser.addActionListener(e -> {
            if((Integer) quantity.getValue() == 0) quantity.setValue(1);
            ImageIcon img = new ImageIcon("src/main/resources/" + shapeChooser.getSelectedItem() + ".png");
            img = new ImageIcon(img.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH));
            imgLabel.setIcon(img);
        });

        JTextField output1 = new JTextField();
        output1.setFont(new Font("Century Gothic", Font.BOLD, 24));
        output1.setMargin(new Insets(5, 5, 5, 5));
        output1.setFocusable(false);
        output1.setBorder(BorderFactory.createLineBorder(Color.black, 3, true));
        output1.setHorizontalAlignment(JTextField.CENTER);

        JButton next1 = new JButton("next");
        next1.setFocusable(false);
        next1.setFont(new Font("Century Gothic", Font.BOLD, 32));
        next1.setForeground(new Color(0x000000));
        next1.setContentAreaFilled(false);
        next1.setBorder(BorderFactory.createLineBorder(Color.black, 3, true));
        next1.addMouseListener(mouseHandler);
        next1.addActionListener(e -> {
            if(shapeChooser.getSelectedIndex() == 0) {
                output1.setText("pick a tile!");
                return;
            }
            if(((JSpinner.DefaultEditor) quantity.getEditor()).getTextField().getText().equals("0")) {
                output1.setText("select a quantity to buy!");
                return;
            }

            desiredQuantity = Integer.parseInt(((JSpinner.DefaultEditor) quantity.getEditor()).getTextField().getText());
            switch(shapeChooser.getSelectedIndex()) {
                case 1:
                    tabbedPane.addTab("enter arrow info", dimensionArrow);
                    break;
                case 2:
                    tabbedPane.addTab("enter circle info", dimensionCircle);
                    break;
                case 3:
                    tabbedPane.addTab("enter donut info", dimensionDonut);
                    break;
                case 4:
                case 5:
                    tabbedPane.addTab("enter quadrilateral info", dimensionQuadrilateral);
                    break;
                case 6:
                    tabbedPane.addTab("enter triangle info", dimensionTriangle);
                    break;
                case 7:
                    tabbedPane.addTab("enter triangular donut info", dimensionTriangleDonut);
                    break;

            }
            tabbedPane.setSelectedIndex(1);
            desiredShape = (String) shapeChooser.getSelectedItem();
        });

        chooseTilePanel.add(label1_1);
        label1_1.setBounds(50, 75, 200, 50);
        chooseTilePanel.add(label1_2);
        label1_2.setBounds(50, 175, 275, 50);
        chooseTilePanel.add(shapeChooser);
        shapeChooser.setBounds(400, 77, 250, 45);
        chooseTilePanel.add(quantity);
        quantity.setBounds(400, 177, 250, 45);
        chooseTilePanel.add(next1);
        next1.setBounds(500, 355, 150, 45);
        chooseTilePanel.add(output1);
        output1.setBounds(50, 355, 425, 45);
        chooseTilePanel.add(imgLabel);
        imgLabel.setBounds(300, 250, 100, 100);
//----------------- Tab 2.1 (arrow) ----------------------------------------------
        dimensionArrow = new JPanel();
        dimensionArrow.setLayout(null);
        dimensionArrow.setBackground(Color.white);

        // Naming scheme: tab 2, arrow (2.1), #1
        JLabel label2_1_1 = new JLabel("enter base length:");
        label2_1_1.setFont(new Font("Century Gothic", Font.BOLD, 22));

        JLabel label2_1_2 = new JLabel("enter base width:");
        label2_1_2.setFont(new Font("Century Gothic", Font.BOLD, 22));

        JLabel label2_1_3 = new JLabel("enter pointer length:");
        label2_1_3.setFont(new Font("Century Gothic", Font.BOLD, 22));

        JLabel label2_1_4 = new JLabel("enter pointer width:");
        label2_1_4.setFont(new Font("Century Gothic", Font.BOLD, 22));

        JTextField field2_1_1 = new JTextField();
        field2_1_1.setFont(new Font("Century Gothic", Font.BOLD, 18));
        field2_1_1.setMargin(new Insets(5, 5, 5, 5));
        field2_1_1.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                validateDouble(e);
            }
            public void keyPressed(KeyEvent e) {}
            public void keyReleased(KeyEvent e) {}
        });

        JTextField field2_1_2 = new JTextField();
        field2_1_2.setFont(new Font("Century Gothic", Font.BOLD, 18));
        field2_1_2.setMargin(new Insets(5, 5, 5, 5));
        field2_1_2.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                validateDouble(e);
            }
            public void keyPressed(KeyEvent e) {}
            public void keyReleased(KeyEvent e) {}
        });

        JTextField field2_1_3 = new JTextField();
        field2_1_3.setFont(new Font("Century Gothic", Font.BOLD, 18));
        field2_1_3.setMargin(new Insets(5, 5, 5, 5));
        field2_1_3.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                validateDouble(e);
            }
            public void keyPressed(KeyEvent e) {}
            public void keyReleased(KeyEvent e) {}
        });

        JTextField field2_1_4 = new JTextField();
        field2_1_4.setFont(new Font("Century Gothic", Font.BOLD, 18));
        field2_1_4.setMargin(new Insets(5, 5, 5, 5));
        field2_1_4.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                validateDouble(e);
            }
            public void keyPressed(KeyEvent e) {}
            public void keyReleased(KeyEvent e) {}
        });

        JLabel arrowLabel = new JLabel(new ImageIcon(
                new ImageIcon("src/main/resources/dimensionGuide/arrowGuide.png")
                        .getImage()
                        .getScaledInstance(200, 200, Image.SCALE_SMOOTH)
        ));

        JTextField output2_1 = new JTextField();
        output2_1.setFont(new Font("Century Gothic", Font.BOLD, 24));
        output2_1.setMargin(new Insets(5, 5, 5, 5));
        output2_1.setFocusable(false);
        output2_1.setBorder(BorderFactory.createLineBorder(Color.black, 3, true));
        output2_1.setHorizontalAlignment(JTextField.CENTER);

        JButton next2_1 = new JButton("next");
        next2_1.setFocusable(false);
        next2_1.setFont(new Font("Century Gothic", Font.BOLD, 32));
        next2_1.setForeground(new Color(0x000000));
        next2_1.setContentAreaFilled(false);
        next2_1.setBorder(BorderFactory.createLineBorder(Color.black, 3, true));
        next2_1.addMouseListener(mouseHandler);
        next2_1.addActionListener(e -> {
            if(field2_1_1.getText().isEmpty() || field2_1_2.getText().isEmpty() || field2_1_3.getText().isEmpty() || field2_1_4.getText().isEmpty()) {
                output2_1.setText("fill out the fields!");
                return;
            }
            double dim1 = Double.parseDouble(field2_1_1.getText()),
                   dim2 = Double.parseDouble(field2_1_2.getText()),
                   dim3 = Double.parseDouble(field2_1_3.getText()),
                   dim4 = Double.parseDouble(field2_1_4.getText());

            if(dim1 <= 0 || dim2 <= 0 || dim3 <= 0 || dim4 <= 0) {
                output2_1.setText("enter valid dimensions!");
                return;
            }
            output2_1.setText("dimensions logged!");
            tempShape = new Arrow(dim1, dim2, dim3, dim4, desiredQuantity);
            reviewShape();
        });


        dimensionArrow.add(label2_1_1);
        label2_1_1.setBounds(50, 25, 200, 25);
        dimensionArrow.add(field2_1_1);
        field2_1_1.setBounds(450, 25, 200, 35);
        dimensionArrow.add(label2_1_2);
        label2_1_2.setBounds(50, 70, 200, 25);
        dimensionArrow.add(field2_1_2);
        field2_1_2.setBounds(450, 70, 200, 35);
        dimensionArrow.add(label2_1_3);
        label2_1_3.setBounds(50, 115, 250, 25);
        dimensionArrow.add(field2_1_3);
        field2_1_3.setBounds(450, 115, 200, 35);
        dimensionArrow.add(label2_1_4);
        label2_1_4.setBounds(50, 160, 250, 25);
        dimensionArrow.add(field2_1_4);
        field2_1_4.setBounds(450, 160, 200, 35);
        dimensionArrow.add(arrowLabel);
        arrowLabel.setBounds(50,190, 200, 200);
        dimensionArrow.add(output2_1);
        output2_1.setBounds(275, 265, 375, 45);
        dimensionArrow.add(next2_1);
        next2_1.setBounds(500, 355, 150, 45);

        //----------------- Tab 2.2 (circle) ----------------------------------------------
        dimensionCircle = new JPanel();
        dimensionCircle.setLayout(null);
        dimensionCircle.setBackground(Color.white);

        // Naming scheme: tab 2, circle (2.2), #1
        JLabel label2_2_1 = new JLabel("enter radius:");
        label2_2_1.setFont(new Font("Century Gothic", Font.BOLD, 22));

        JTextField field2_2_1 = new JTextField();
        field2_2_1.setFont(new Font("Century Gothic", Font.BOLD, 18));
        field2_2_1.setMargin(new Insets(5, 5, 5, 5));
        field2_2_1.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                validateDouble(e);
            }
            public void keyPressed(KeyEvent e) {}
            public void keyReleased(KeyEvent e) {}
        });

        JLabel circleLabel = new JLabel(new ImageIcon(
                new ImageIcon("src/main/resources/dimensionGuide/circleGuide.png")
                        .getImage()
                        .getScaledInstance(200, 200, Image.SCALE_SMOOTH)
        ));

        JTextField output2_2 = new JTextField();
        output2_2.setFont(new Font("Century Gothic", Font.BOLD, 24));
        output2_2.setMargin(new Insets(5, 5, 5, 5));
        output2_2.setFocusable(false);
        output2_2.setBorder(BorderFactory.createLineBorder(Color.black, 3, true));
        output2_2.setHorizontalAlignment(JTextField.CENTER);

        JButton next2_2 = new JButton("next");
        next2_2.setFocusable(false);
        next2_2.setFont(new Font("Century Gothic", Font.BOLD, 32));
        next2_2.setForeground(new Color(0x000000));
        next2_2.setContentAreaFilled(false);
        next2_2.setBorder(BorderFactory.createLineBorder(Color.black, 3, true));
        next2_2.addMouseListener(mouseHandler);
        next2_2.addActionListener(e -> {
            if(field2_2_1.getText().isEmpty()) {
                output2_2.setText("fill out the radius!");
                return;
            }
            double dim1 = Double.parseDouble(field2_2_1.getText());

            if(dim1 <= 0) {
                output2_2.setText("enter valid radius!");
                return;
            }
            output2_2.setText("dimension logged!");
            tempShape = new Circle(dim1, desiredQuantity);
            reviewShape();
        });

        dimensionCircle.add(label2_2_1);
        label2_2_1.setBounds(50, 25, 200, 25);
        dimensionCircle.add(field2_2_1);
        field2_2_1.setBounds(450, 25, 200, 35);
        dimensionCircle.add(circleLabel);
        circleLabel.setBounds(50,190, 200, 200);
        dimensionCircle.add(output2_2);
        output2_2.setBounds(275, 265, 375, 45);
        dimensionCircle.add(next2_2);
        next2_2.setBounds(500, 355, 150, 45);

        //----------------- Tab 2.3 (donut) ----------------------------------------------
        dimensionDonut = new JPanel();
        dimensionDonut.setLayout(null);
        dimensionDonut.setBackground(Color.white);

        // Naming scheme: tab 2, donut (2.3), #1
        JLabel label2_3_1 = new JLabel("enter radius 1:");
        label2_3_1.setFont(new Font("Century Gothic", Font.BOLD, 22));

        JLabel label2_3_2 = new JLabel("enter radius 2:");
        label2_3_2.setFont(new Font("Century Gothic", Font.BOLD, 22));

        JTextField field2_3_1 = new JTextField();
        field2_3_1.setFont(new Font("Century Gothic", Font.BOLD, 18));
        field2_3_1.setMargin(new Insets(5, 5, 5, 5));
        field2_3_1.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                validateDouble(e);
            }
            public void keyPressed(KeyEvent e) {}
            public void keyReleased(KeyEvent e) {}
        });

        JTextField field2_3_2 = new JTextField();
        field2_3_2.setFont(new Font("Century Gothic", Font.BOLD, 18));
        field2_3_2.setMargin(new Insets(5, 5, 5, 5));
        field2_3_2.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                validateDouble(e);
            }
            public void keyPressed(KeyEvent e) {}
            public void keyReleased(KeyEvent e) {}
        });

        JLabel donutLabel = new JLabel(new ImageIcon(
                new ImageIcon("src/main/resources/dimensionGuide/donutGuide.png")
                        .getImage()
                        .getScaledInstance(200, 200, Image.SCALE_SMOOTH)
        ));

        JTextField output2_3 = new JTextField();
        output2_3.setFont(new Font("Century Gothic", Font.BOLD, 24));
        output2_3.setMargin(new Insets(5, 5, 5, 5));
        output2_3.setFocusable(false);
        output2_3.setBorder(BorderFactory.createLineBorder(Color.black, 3, true));
        output2_3.setHorizontalAlignment(JTextField.CENTER);

        JButton next2_3 = new JButton("next");
        next2_3.setFocusable(false);
        next2_3.setFont(new Font("Century Gothic", Font.BOLD, 32));
        next2_3.setForeground(new Color(0x000000));
        next2_3.setContentAreaFilled(false);
        next2_3.setBorder(BorderFactory.createLineBorder(Color.black, 3, true));
        next2_3.addMouseListener(mouseHandler);
        next2_3.addActionListener(e -> {
            if(field2_3_1.getText().isEmpty() || field2_3_2.getText().isEmpty()) {
                output2_3.setText("fill out the fields!");
                return;
            }
            double dim1 = Double.parseDouble(field2_3_1.getText()),
                    dim2 = Double.parseDouble(field2_3_2.getText());

            if(dim1 <= 0 || dim2 <= 0) {
                output2_3.setText("enter valid dimensions!");
                return;
            }

            if(dim1 <= dim2) {
                output2_3.setText("radius 1 must be larger!");
                return;
            }

            output2_3.setText("dimensions logged!");
            tempShape = new Donut(dim1, dim2, desiredQuantity);
            reviewShape();
        });


        dimensionDonut.add(label2_3_1);
        label2_3_1.setBounds(50, 25, 200, 25);
        dimensionDonut.add(field2_3_1);
        field2_3_1.setBounds(450, 25, 200, 35);
        dimensionDonut.add(label2_3_2);
        label2_3_2.setBounds(50, 70, 200, 25);
        dimensionDonut.add(field2_3_2);
        field2_3_2.setBounds(450, 70, 200, 35);
        dimensionDonut.add(donutLabel);
        donutLabel.setBounds(50,190, 200, 200);
        dimensionDonut.add(output2_3);
        output2_3.setBounds(275, 265, 375, 45);
        dimensionDonut.add(next2_3);
        next2_3.setBounds(500, 355, 150, 45);

        //----------------- Tab 2.4 (quadrilateral) ----------------------------------------------
        dimensionQuadrilateral = new JPanel();
        dimensionQuadrilateral.setLayout(null);
        dimensionQuadrilateral.setBackground(Color.white);

        // Naming scheme: tab 2, quadrilateral (2.4), #1
        JLabel label2_4_1 = new JLabel("enter length:");
        label2_4_1.setFont(new Font("Century Gothic", Font.BOLD, 22));

        JLabel label2_4_2 = new JLabel("enter width");
        label2_4_2.setFont(new Font("Century Gothic", Font.BOLD, 22));

        JTextField field2_4_1 = new JTextField();
        field2_4_1.setFont(new Font("Century Gothic", Font.BOLD, 18));
        field2_4_1.setMargin(new Insets(5, 5, 5, 5));
        field2_4_1.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                validateDouble(e);
            }
            public void keyPressed(KeyEvent e) {}
            public void keyReleased(KeyEvent e) {}
        });

        JTextField field2_4_2 = new JTextField();
        field2_4_2.setFont(new Font("Century Gothic", Font.BOLD, 18));
        field2_4_2.setMargin(new Insets(5, 5, 5, 5));
        field2_4_2.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                validateDouble(e);
            }
            public void keyPressed(KeyEvent e) {}
            public void keyReleased(KeyEvent e) {}
        });

        JLabel quadrilateralLabel = new JLabel(new ImageIcon(
                new ImageIcon("src/main/resources/dimensionGuide/quadrilateralGuide.png")
                        .getImage()
                        .getScaledInstance(200, 200, Image.SCALE_SMOOTH)
        ));

        JTextField output2_4 = new JTextField();
        output2_4.setFont(new Font("Century Gothic", Font.BOLD, 24));
        output2_4.setMargin(new Insets(5, 5, 5, 5));
        output2_4.setFocusable(false);
        output2_4.setBorder(BorderFactory.createLineBorder(Color.black, 3, true));
        output2_4.setHorizontalAlignment(JTextField.CENTER);

        JButton next2_4 = new JButton("next");
        next2_4.setFocusable(false);
        next2_4.setFont(new Font("Century Gothic", Font.BOLD, 32));
        next2_4.setForeground(new Color(0x000000));
        next2_4.setContentAreaFilled(false);
        next2_4.setBorder(BorderFactory.createLineBorder(Color.black, 3, true));
        next2_4.addMouseListener(mouseHandler);
        next2_4.addActionListener(e -> {
            if(field2_4_1.getText().isEmpty() || field2_4_2.getText().isEmpty()) {
                output2_4.setText("fill out the fields!");
                return;
            }
            double dim1 = Double.parseDouble(field2_4_1.getText()),
                    dim2 = Double.parseDouble(field2_4_2.getText());

            if(dim1 <= 0 || dim2 <= 0) {
                output2_4.setText("enter valid dimensions!");
                return;
            }

            output2_4.setText("dimensions logged!");
            tempShape = desiredShape.equalsIgnoreCase("rectangle") ? new Rectangle(dim1, dim2, desiredQuantity) : new Parallelogram(dim1, dim2, desiredQuantity);
            reviewShape();
        });


        dimensionQuadrilateral.add(label2_4_1);
        label2_4_1.setBounds(50, 25, 200, 25);
        dimensionQuadrilateral.add(field2_4_1);
        field2_4_1.setBounds(450, 25, 200, 35);
        dimensionQuadrilateral.add(label2_4_2);
        label2_4_2.setBounds(50, 70, 200, 25);
        dimensionQuadrilateral.add(field2_4_2);
        field2_4_2.setBounds(450, 70, 200, 35);
        dimensionQuadrilateral.add(quadrilateralLabel);
        quadrilateralLabel.setBounds(50,190, 200, 200);
        dimensionQuadrilateral.add(output2_4);
        output2_4.setBounds(275, 265, 375, 45);
        dimensionQuadrilateral.add(next2_4);
        next2_4.setBounds(500, 355, 150, 45);

        //----------------- Tab 2.5 (triangle) ----------------------------------------------
        dimensionTriangle = new JPanel();
        dimensionTriangle.setLayout(null);
        dimensionTriangle.setBackground(Color.white);

        // Naming scheme: tab 2, triangle (2.5), #1
        JLabel label2_5_1 = new JLabel("enter base:");
        label2_5_1.setFont(new Font("Century Gothic", Font.BOLD, 22));

        JLabel label2_5_2 = new JLabel("enter height");
        label2_5_2.setFont(new Font("Century Gothic", Font.BOLD, 22));

        JTextField field2_5_1 = new JTextField();
        field2_5_1.setFont(new Font("Century Gothic", Font.BOLD, 18));
        field2_5_1.setMargin(new Insets(5, 5, 5, 5));
        field2_5_1.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                validateDouble(e);
            }
            public void keyPressed(KeyEvent e) {}
            public void keyReleased(KeyEvent e) {}
        });

        JTextField field2_5_2 = new JTextField();
        field2_5_2.setFont(new Font("Century Gothic", Font.BOLD, 18));
        field2_5_2.setMargin(new Insets(5, 5, 5, 5));
        field2_5_2.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                validateDouble(e);
            }
            public void keyPressed(KeyEvent e) {}
            public void keyReleased(KeyEvent e) {}
        });

        JLabel triangleLabel = new JLabel(new ImageIcon(
                new ImageIcon("src/main/resources/dimensionGuide/triangleGuide.png")
                        .getImage()
                        .getScaledInstance(200, 200, Image.SCALE_SMOOTH)
        ));

        JTextField output2_5 = new JTextField();
        output2_5.setFont(new Font("Century Gothic", Font.BOLD, 24));
        output2_5.setMargin(new Insets(5, 5, 5, 5));
        output2_5.setFocusable(false);
        output2_5.setBorder(BorderFactory.createLineBorder(Color.black, 3, true));
        output2_5.setHorizontalAlignment(JTextField.CENTER);

        JButton next2_5 = new JButton("next");
        next2_5.setFocusable(false);
        next2_5.setFont(new Font("Century Gothic", Font.BOLD, 32));
        next2_5.setForeground(new Color(0x000000));
        next2_5.setContentAreaFilled(false);
        next2_5.setBorder(BorderFactory.createLineBorder(Color.black, 3, true));
        next2_5.addMouseListener(mouseHandler);
        next2_5.addActionListener(e -> {
            if(field2_5_1.getText().isEmpty() || field2_5_2.getText().isEmpty()) {
                output2_5.setText("fill out the fields!");
                return;
            }
            double dim1 = Double.parseDouble(field2_5_1.getText()),
                    dim2 = Double.parseDouble(field2_5_2.getText());

            if(dim1 <= 0 || dim2 <= 0) {
                output2_5.setText("enter valid dimensions!");
                return;
            }

            output2_5.setText("dimensions logged!");
            tempShape = new Triangle(dim1, dim2, desiredQuantity);
            reviewShape();
        });


        dimensionTriangle.add(label2_5_1);
        label2_5_1.setBounds(50, 25, 200, 25);
        dimensionTriangle.add(field2_5_1);
        field2_5_1.setBounds(450, 25, 200, 35);
        dimensionTriangle.add(label2_5_2);
        label2_5_2.setBounds(50, 70, 200, 25);
        dimensionTriangle.add(field2_5_2);
        field2_5_2.setBounds(450, 70, 200, 35);
        dimensionTriangle.add(triangleLabel);
        triangleLabel.setBounds(50,190, 200, 200);
        dimensionTriangle.add(output2_5);
        output2_5.setBounds(275, 265, 375, 45);
        dimensionTriangle.add(next2_5);
        next2_5.setBounds(500, 355, 150, 45);





        //----------------- Tab 2.6 (triangular donut) ----------------------------------------------
        dimensionTriangleDonut = new JPanel();
        dimensionTriangleDonut.setLayout(null);
        dimensionTriangleDonut.setBackground(Color.white);

        // Naming scheme: tab 2, triangular donut (2.6), #1
        JLabel label2_6_1 = new JLabel("enter base 1:");
        label2_6_1.setFont(new Font("Century Gothic", Font.BOLD, 22));

        JLabel label2_6_2 = new JLabel("enter height 1:");
        label2_6_2.setFont(new Font("Century Gothic", Font.BOLD, 22));

        JLabel label2_6_3 = new JLabel("enter base 2:");
        label2_6_3.setFont(new Font("Century Gothic", Font.BOLD, 22));

        JLabel label2_6_4 = new JLabel("enter height 2:");
        label2_6_4.setFont(new Font("Century Gothic", Font.BOLD, 22));

        JTextField field2_6_1 = new JTextField();
        field2_6_1.setFont(new Font("Century Gothic", Font.BOLD, 18));
        field2_6_1.setMargin(new Insets(5, 5, 5, 5));
        field2_6_1.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                validateDouble(e);
            }
            public void keyPressed(KeyEvent e) {}
            public void keyReleased(KeyEvent e) {}
        });

        JTextField field2_6_2 = new JTextField();
        field2_6_2.setFont(new Font("Century Gothic", Font.BOLD, 18));
        field2_6_2.setMargin(new Insets(5, 5, 5, 5));
        field2_6_2.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                validateDouble(e);
            }
            public void keyPressed(KeyEvent e) {}
            public void keyReleased(KeyEvent e) {}
        });

        JTextField field2_6_3 = new JTextField();
        field2_6_3.setFont(new Font("Century Gothic", Font.BOLD, 18));
        field2_6_3.setMargin(new Insets(5, 5, 5, 5));
        field2_6_3.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                validateDouble(e);
            }
            public void keyPressed(KeyEvent e) {}
            public void keyReleased(KeyEvent e) {}
        });

        JTextField field2_6_4 = new JTextField();
        field2_6_4.setFont(new Font("Century Gothic", Font.BOLD, 18));
        field2_6_4.setMargin(new Insets(5, 5, 5, 5));
        field2_6_4.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                validateDouble(e);
            }
            public void keyPressed(KeyEvent e) {}
            public void keyReleased(KeyEvent e) {}
        });

        JLabel triangularDonutLabel = new JLabel(new ImageIcon(
                new ImageIcon("src/main/resources/dimensionGuide/triangleDonutGuide.png")
                        .getImage()
                        .getScaledInstance(200, 200, Image.SCALE_SMOOTH)
        ));

        JTextField output2_6 = new JTextField();
        output2_6.setFont(new Font("Century Gothic", Font.BOLD, 14));
        output2_6.setMargin(new Insets(5, 5, 5, 5));
        output2_6.setFocusable(false);
        output2_6.setBorder(BorderFactory.createLineBorder(Color.black, 3, true));
        output2_6.setHorizontalAlignment(JTextField.CENTER);

        JButton next2_6 = new JButton("next");
        next2_6.setFocusable(false);
        next2_6.setFont(new Font("Century Gothic", Font.BOLD, 32));
        next2_6.setForeground(new Color(0x000000));
        next2_6.setContentAreaFilled(false);
        next2_6.setBorder(BorderFactory.createLineBorder(Color.black, 3, true));
        next2_6.addMouseListener(mouseHandler);
        next2_6.addActionListener(e -> {
            if(field2_6_1.getText().isEmpty() || field2_6_2.getText().isEmpty() || field2_6_3.getText().isEmpty() || field2_6_4.getText().isEmpty()) {
                output2_6.setText("fill out the fields!");
                return;
            }
            double dim1 = Double.parseDouble(field2_6_1.getText()),
                    dim2 = Double.parseDouble(field2_6_2.getText()),
                    dim3 = Double.parseDouble(field2_6_3.getText()),
                    dim4 = Double.parseDouble(field2_6_4.getText());

            if(dim1 <= 0 || dim2 <= 0 || dim3 <= 0 || dim4 <= 0) {
                output2_6.setText("enter valid dimensions!");
                return;
            }

            if(dim1 * dim2 * 0.5 <= dim3 * dim4 * 0.5) {
                output2_6.setText("inner triangle must be smaller than outer triangle!");
                return;
            }

            output2_6.setText("dimensions logged!");
            tempShape = new TriangleDonut(dim1, dim2, dim3, dim4, desiredQuantity);
            reviewShape();

        });


        dimensionTriangleDonut.add(label2_6_1);
        label2_6_1.setBounds(50, 25, 200, 25);
        dimensionTriangleDonut.add(field2_6_1);
        field2_6_1.setBounds(450, 25, 200, 35);
        dimensionTriangleDonut.add(label2_6_2);
        label2_6_2.setBounds(50, 70, 200, 25);
        dimensionTriangleDonut.add(field2_6_2);
        field2_6_2.setBounds(450, 70, 200, 35);
        dimensionTriangleDonut.add(label2_6_3);
        label2_6_3.setBounds(50, 115, 250, 25);
        dimensionTriangleDonut.add(field2_6_3);
        field2_6_3.setBounds(450, 115, 200, 35);
        dimensionTriangleDonut.add(label2_6_4);
        label2_6_4.setBounds(50, 160, 250, 25);
        dimensionTriangleDonut.add(field2_6_4);
        field2_6_4.setBounds(450, 160, 200, 35);
        dimensionTriangleDonut.add(triangularDonutLabel);
        triangularDonutLabel.setBounds(50,190, 200, 200);
        dimensionTriangleDonut.add(output2_6);
        output2_6.setBounds(275, 265, 375, 45);
        dimensionTriangleDonut.add(next2_6);
        next2_6.setBounds(500, 355, 150, 45);

        // Tab 3: Review
        reviewPanel = new JPanel();
        reviewPanel.setLayout(null);
        reviewPanel.setBackground(new Color(0xFFFFFF));

        JLabel check = new JLabel("Check your item:");
        check.setFont(new Font("Century Gothic", Font.BOLD, 22));

        reviewArea = new JTextArea();
        reviewArea.setMargin(new Insets(5, 5, 5, 5));
        reviewArea.setEditable(false);
        reviewArea.setLineWrap(true);
        reviewArea.setWrapStyleWord(true);
        reviewArea.setFont(new Font("Century Gothic", Font.PLAIN, 18));
        JScrollPane reviewScroller = new JScrollPane(reviewArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        addToCart = new JButton("add to cart");
        addToCart.setFocusable(false);
        addToCart.setFont(new Font("Century Gothic", Font.BOLD, 26));
        addToCart.setForeground(new Color(0x000000));
        addToCart.setContentAreaFilled(false);
        addToCart.setBorder(BorderFactory.createLineBorder(Color.black, 3, true));
        addToCart.addMouseListener(mouseHandler);
        addToCart.addActionListener(e -> {
            if(addToCart.getText().equals("view cart")) {
                gui.actionPerformed(new ActionEvent(addToCart, 69420, "view cart from buy panel"));
                return;
            }
            reviewArea.setText("successfully added items(s) to cart!\n\nto buy more tiles, select the first tab.");

            tabbedPane.setEnabledAt(1, false);
            Shape.shapeList.add(tempShape);
            addToCart.setText("view cart");
        });

        reviewPanel.add(check);
        check.setBounds(250, 20, 200, 40);
        reviewPanel.add(reviewScroller);
        reviewScroller.setBounds(150, 75, 400, 200);
        reviewPanel.add(addToCart);
        addToCart.setBounds(200, 295, 300, 50);



        tabbedPane.addTab("choose tile", chooseTilePanel);
    }

    public void resetBorder() {
        backBtn.setBorder(BorderFactory.createLineBorder(Color.black, 3, true));
        tabbedPane.setSelectedIndex(0);
    }

    private void validateDouble(KeyEvent e) {
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

    private void reviewShape() {
        tabbedPane.addTab("review order", reviewPanel);
        tabbedPane.setSelectedIndex(2);

        reviewArea.setText("");
        reviewArea.append(desiredShape.toLowerCase() + " × " + desiredQuantity + " item");
        if(desiredQuantity > 1) reviewArea.append("s");
        reviewArea.append(":\n");
        reviewArea.append("   - area: " + tempShape.getArea() + " m²\n");
        if(desiredQuantity != 1) reviewArea.append("   - total area: " + (Math.round(tempShape.getArea() * desiredQuantity * 100) / 100.0) + " m²\n");
        reviewArea.append("   - unit price: " + String.format("%,.2f", Shape.getUnitPrice()) + " $/m²\n");
        reviewArea.append("   - price per tile: $" + String.format("%,.2f", Math.round(tempShape.getArea() * Shape.getUnitPrice() * 100) / 100.0) + "\n");
        reviewArea.append("   - total price: $" + String.format("%,.2f", Math.round(tempShape.getArea() * Shape.getUnitPrice() * desiredQuantity * 100) / 100.0) + "\n");

    }
}
