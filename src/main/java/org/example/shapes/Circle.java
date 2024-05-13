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

    public String toString() {
        String output =
            "Circle × " + quantity +
            "\n   - Radius: " + radius + " m" +
            "\nTotal area: " + Math.round(getArea() * quantity * 100) / 100.0 + " m²" +
            "\nTotal price: $" + String.format("%,.2f", getArea() * quantity * Shape.getUnitPrice());

        return output;
    }
}
