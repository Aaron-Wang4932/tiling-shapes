package org.example.shapes;

public class Arrow extends Shape {
    private final double baseLength;
    private final double baseWidth;
    private final double pointerLength;
    private final double pointerWidth;

    public Arrow(double baseLength, double baseWidth, double pointerLength, double pointerWidth, int quantity) {
        this.baseLength = baseLength;
        this.baseWidth = baseWidth;
        this.pointerLength = pointerLength;
        this.pointerWidth = pointerWidth;
        this.quantity = quantity;
    }
    @Override
    public double getArea() {
        double unroundedArea = baseLength * baseWidth + 0.5 * pointerLength * pointerWidth;
        return Math.round(unroundedArea * 100) / 100.0;
    }

    @Override
    public String getShapeName() {
        return "arrow";
    }

    public String toString() {
        String output =
            "Arrow × " + quantity +
            "\n   - Base length: " + baseLength + " m" +
            "\n   - Base width: " + baseWidth + " m" +
            "\n   - Pointer length: " + pointerLength + " m" +
            "\n   - Pointer width: " + pointerWidth + " m" +
            "\nTotal area: " + getArea() * quantity + " m²" +
            "\nTotal price: $" + String.format("%,.2f", getArea() * quantity * Shape.getUnitPrice());

        return output;
    }
}
