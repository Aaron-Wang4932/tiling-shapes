package org.example.shapes;

public class Circle extends Shape {
    private final double radius;
    public Circle(double radius, int quantity) {
        this.radius = radius;
        this.quantity = quantity;
    }
    @Override
    public double getArea() {
        return Math.round(Math.PI*Math.pow(radius, 2) * 100) / 100.0;
    }

    @Override
    public String getShapeName() {
        return "circle";
    }
}
