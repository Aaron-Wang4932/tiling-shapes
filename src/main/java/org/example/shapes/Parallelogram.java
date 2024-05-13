package org.example.shapes;

public class Parallelogram extends Shape {
    private final double length, width;
    public Parallelogram(double length, double width, int quantity) {
        this.length = length;
        this.width = width;
        this.quantity = quantity;
    }

    @Override
    public double getArea() {
        return Math.round(length * width * 100) / 100.0;
    }

    @Override
    public String getShapeName() {
        return "parallelogram";
    }

    public String toString() {
        String output =
            "Parallelogram × " + quantity +
            "\n   - Length: " + length + " m" +
            "\n   - Width: " + width + " m" +
            "\nTotal area: " + getArea() * quantity + " m²" +
            "\nTotal price: $" + String.format("%,.2f", getArea() * quantity * Shape.getUnitPrice());

        return output;
    }
}
