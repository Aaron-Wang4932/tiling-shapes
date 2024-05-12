package org.example.shapes;

public class Triangle extends Shape {
    private final double base, height;

    public Triangle(double base, double height, int quantity) {
        this.base = base;
        this.height = height;
        this.quantity = quantity;
    }

    @Override
    public double getArea() {
        return Math.round(base * height * 0.5 * 100) / 100.0;
    }
}
