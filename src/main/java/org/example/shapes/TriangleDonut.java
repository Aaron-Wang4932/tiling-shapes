package org.example.shapes;

public class TriangleDonut extends Shape {
    private final double base1, height1, base2, height2;

    public TriangleDonut(double base1, double height1, double base2, double height2, int quantity) {
        this.base1 = base1;
        this.height1 = height1;
        this.base2 = base2;
        this.height2 = height2;
        this.quantity = quantity;
    }

    @Override
    public double getArea() {
        double triangle1 = base1 * height1 * 0.5;
        double triangle2 = base2 * height2 * 0.5;
        double unroundedArea = Math.abs(triangle1 - triangle2);

        return Math.round(unroundedArea * 100) / 100.0;
    }

    @Override
    public String getShapeName() {
        return "triangular donut";
    }

    public String toString() {
        String output =
            "Parallelogram × " + quantity +
            "\n   - Base 1: " + base1 + " m" +
            "\n   - Height 1: " + height1 + " m" +
            "\n   - Base 2: " + base2 + " m" +
            "\n   - Height 2: " + height2 + " m" +
            "\nTotal area: " + getArea() * quantity + " m²" +
            "\nTotal price: $" + String.format("%,.2f", getArea() * quantity * Shape.getUnitPrice());

        return output;
    }
}
