package org.example.shapes;

public class Donut extends Shape {
    private final double radius1, radius2;
    public Donut(double radius1, double radius2, int quantity) {
        this.radius1 = radius1;
        this.radius2 = radius2;
        this.quantity = quantity;
    }
    @Override
    public double getArea() {
        double circle1 = Math.PI * Math.pow(radius1, 2);
        double circle2 = Math.PI * Math.pow(radius2,  2);
        double unroundedArea = Math.abs(circle1 - circle2);

        return Math.round(unroundedArea * 100) / 100.0;
    }
}
