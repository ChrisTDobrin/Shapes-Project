import java.util.ArrayList;
import java.util.List;

public class DrawingTool {
    private final List<Shape> shapes = new ArrayList<>();

    public boolean addShape(Shape shape) {
        if (shape == null) {
            throw new IllegalArgumentException("Shape cannot be null");
        }
        if (shapes.contains(shape)) {
            System.out.println("Can't add " + shape + ": a shape like that is already in the system.");
            return false;
        }
        shapes.add(shape);
        return true;
    }

    public int getShapeCount() {
        return shapes.size();
    }

    public double getTotalArea() {
        double total = 0;
        for (Shape s : shapes) {
            total += s.area();
        }
        return total;
    }

    public double getTotalPerimeter() {
        double total = 0;
        for (Shape s : shapes) {
            total += s.perimeter();
        }
        return total;
    }
}