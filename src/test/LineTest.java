package test;

import main.Line;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LineTest {

    private String name = "Top row";

    @Test
    void constructorThrowsExceptionIfNot3Numbers() {
        assertThrows(IllegalArgumentException.class, () -> new Line(new int[]{5}, name));
    }

    @Test
    void getValue() {
        Line line = new Line(new int[]{0, 5, 7}, name);

        assertEquals(5, line.getValue(1));
    }

    @Test
    void contains() {
        Line line = new Line(new int[]{0, 5, 7}, name);

        assertTrue(line.contains(5));
        assertFalse(line.contains(3));
    }

    @Test
    void getName() {
        Line line = new Line(new int[]{0, 5, 7}, "hoi");

        assertEquals("hoi", line.getName());
    }

    @Test
    void getValues() {
        Line line = new Line(new int[]{0, 5, 7}, "hoi");
        int[] values = line.getValues();

        assertArrayEquals(values, new int[]{0, 5, 7});
    }
}