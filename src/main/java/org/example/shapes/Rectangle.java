package org.example.shapes;

public class Rectangle extends Shape {
    private final double length, width;
    public Rectangle(double length, double width, int quantity) {
        this.length = length;
        this.width = width;
        this.quantity = quantity;
    }
    @Override
    public double getArea() {
        return Math.round(length * width * 100) / 100.0;
    }
}
