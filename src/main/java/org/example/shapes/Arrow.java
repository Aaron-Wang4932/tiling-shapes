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
}
