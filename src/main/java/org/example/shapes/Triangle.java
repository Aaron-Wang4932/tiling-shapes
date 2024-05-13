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

    @Override
    public String getShapeName() {
        return "triangle";
    }

    public String toString() {
        String output =
            "Triangle × " + quantity +
            "\n   - Base: " + base + " m" +
            "\n   - Height: " + height + " m" +
            "\nTotal area: " + getArea() * quantity + " m²" +
            "\nTotal price: $" + String.format("%,.2f", getArea() * quantity * Shape.getUnitPrice());

        return output;
    }
}
