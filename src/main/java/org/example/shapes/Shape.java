package org.example.shapes;

import java.util.ArrayList;

public abstract class Shape {
    protected static double unitPrice = 1;
    protected int quantity;
    public static ArrayList<Shape> shapeList = new ArrayList<>();
    public static double getUnitPrice() {
        return unitPrice;
    }

    public static void setUnitPrice(double unitPrice) {
        Shape.unitPrice = unitPrice;
    }

    public abstract double getArea();
    public abstract String getShapeName();

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return Math.round(getArea() * quantity * unitPrice * 100) / 100.0;
    }
}
