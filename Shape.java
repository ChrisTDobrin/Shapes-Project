import java.util.Arrays;
import java.util.Objects;

public abstract class Shape {

    public abstract double area();

    public abstract double perimeter();

    protected abstract double[] dimensions();

    protected static void checkPositive(double... values) {
        for (double v : values) {
            if (v <= 0) {
                throw new IllegalArgumentException("Sorry, the dimensions can only be positive numbers");
            }
        }
    }

    private double[] roundedDimensions() {
        double[] d = dimensions().clone();
        for (int i = 0; i < d.length; i++) {
            d[i] = Math.round(d[i] * 1_000_000) / 1_000_000.0;
        }
        return d;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Shape other = (Shape) o;
        return Arrays.equals(roundedDimensions(), other.roundedDimensions());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getClass().getSimpleName(), Arrays.hashCode(roundedDimensions()));
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + Arrays.toString(dimensions());
    }
}