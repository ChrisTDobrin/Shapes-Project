import java.util.Arrays;

public class Triangle extends Shape {
    private final double a;
    private final double b;
    private final double c;

    public Triangle(double a, double b, double c) {
        checkPositive(a, b, c);
        if (a + b <= c || a + c <= b || b + c <= a) {
            throw new IllegalArgumentException("Sorry, these sides can't make up a triangle");
        }
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    public double area() {
        // Heron's formula
        double s = perimeter() / 2;
        return Math.sqrt(s * (s - a) * (s - b) * (s - c));
    }

    @Override
    public double perimeter() {
        return a + b + c;
    }

    @Override
    protected double[] dimensions() {
        // sorted so Triangle(3,4,5) equals Triangle(5,3,4)
        double[] sides = { a, b, c };
        Arrays.sort(sides);
        return sides;
    }
}
