import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DrawingToolTest {
    private static final double DELTA = 0.0001;

    private DrawingTool tool;
    private final PrintStream originalOut = System.out;
    private ByteArrayOutputStream console;

    @BeforeEach
    void setUp() {
        tool = new DrawingTool();
        console = new ByteArrayOutputStream();
        System.setOut(new PrintStream(console)); // capture console output
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    void rectangleCalculations() {
        Rectangle r = new Rectangle(4, 5);
        assertEquals(20, r.area(), DELTA);
        assertEquals(18, r.perimeter(), DELTA);
    }

    @Test
    void squareCalculations() {
        Square s = new Square(3);
        assertEquals(9, s.area(), DELTA);
        assertEquals(12, s.perimeter(), DELTA);
    }

    @Test
    void circleCalculations() {
        Circle c = new Circle(2);
        assertEquals(Math.PI * 4, c.area(), DELTA);
        assertEquals(4 * Math.PI, c.perimeter(), DELTA);
    }

    @Test
    void triangleCalculations() {
        Triangle t = new Triangle(3, 4, 5);
        assertEquals(6, t.area(), DELTA);
        assertEquals(12, t.perimeter(), DELTA);
    }

    @Test
    void invalidDimensionsAreRejected() {
        assertThrows(IllegalArgumentException.class, () -> new Square(-1));
        assertThrows(IllegalArgumentException.class, () -> new Triangle(1, 2, 10));
    }

    @Test
    void addSingleShape() {
        assertTrue(tool.addShape(new Square(3)));
        assertEquals(1, tool.getShapeCount());
        assertEquals(9, tool.getTotalArea(), DELTA);
        assertEquals(12, tool.getTotalPerimeter(), DELTA);
    }

    @Test
    void emptyToolHasZeroTotals() {
        assertEquals(0, tool.getTotalArea(), DELTA);
        assertEquals(0, tool.getTotalPerimeter(), DELTA);
    }

    @Test
    void addAllTypesOfShapes() {
        tool.addShape(new Rectangle(4, 5));
        tool.addShape(new Square(3));
        tool.addShape(new Circle(2));
        tool.addShape(new Triangle(3, 4, 5));

        assertEquals(4, tool.getShapeCount());
        assertEquals(20 + 9 + Math.PI * 4 + 6, tool.getTotalArea(), DELTA);
        assertEquals(18 + 12 + 4 * Math.PI + 12, tool.getTotalPerimeter(), DELTA);
    }

    // ---------- pairs ----------
    @Test
    void addPairOfDifferentShapes() {
        tool.addShape(new Rectangle(4, 5));
        tool.addShape(new Square(3));
        assertEquals(2, tool.getShapeCount());
        assertEquals(29, tool.getTotalArea(), DELTA);
        assertEquals(30, tool.getTotalPerimeter(), DELTA);
    }

    @Test
    void addPairOfSameTypeWithDifferentSize() {
        assertTrue(tool.addShape(new Circle(1)));
        assertTrue(tool.addShape(new Circle(2)));
        assertEquals(2, tool.getShapeCount());
    }

    @Test
    void duplicateShapeIsNotAdded() {
        tool.addShape(new Square(3));
        assertFalse(tool.addShape(new Square(3)));
        assertEquals(1, tool.getShapeCount());
        assertEquals(9, tool.getTotalArea(), DELTA);
    }

    @Test
    void duplicatePrintsMessage() {
        tool.addShape(new Circle(2));
        tool.addShape(new Circle(2));
        assertTrue(console.toString().contains("A similar shape already exists"));
    }

    @Test
    void noMessageWhenShapeIsUnique() {
        tool.addShape(new Circle(2));
        assertEquals("", console.toString());
    }

    @Test
    void duplicateTriangleWithSidesInDifferentOrder() {
        tool.addShape(new Triangle(3, 4, 5));
        assertFalse(tool.addShape(new Triangle(5, 3, 4)));
        assertEquals(1, tool.getShapeCount());
    }

    @Test
    void squareAndRectangleWithSameSidesAreDifferent() {
        tool.addShape(new Square(3));
        assertTrue(tool.addShape(new Rectangle(3, 3)));
        assertEquals(2, tool.getShapeCount());
    }

    @Test
    void duplicatesDoNotChangeTotals() {
        tool.addShape(new Rectangle(2, 3));
        tool.addShape(new Rectangle(2, 3));
        assertEquals(6, tool.getTotalArea(), DELTA);
        assertEquals(10, tool.getTotalPerimeter(), DELTA);
    }

    @Test
    void addingNullThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> tool.addShape(null));
    }
}