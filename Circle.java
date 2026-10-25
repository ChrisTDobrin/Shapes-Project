public class Circle extends Shape {
    private final double radius;

    public Circle(double radius) {
        checkPositive(radius);
        this.radius = radius;
    }

    @Override
    public double area() {
        return Math.PI * radius * radius;
    }

    @Override
    public double perimeter() {
        return 2 * Math.PI * radius;
    }

    @Override
    protected double[] dimensions() {
        return new double[] { radius };
    }
}
