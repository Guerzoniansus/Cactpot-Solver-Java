package test;

import main.Board;
import main.Line;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    @Test
    void constructorInitializes2DArrayCorrectly() {
        Board actualBoard = new Board("0, 0, 7, 0, 5, 0, 4, 0, 9");
        int[][] expectedBoard = new int[][] {{0, 0, 7}, {0, 5, 0}, {4, 0, 9}};

        assertArrayEquals(expectedBoard, actualBoard.getBoard());
    }

    @Test
    void constructorInitializesLinesCorrectly() {
        /*
        0 0 7
        0 5 0
        4 0 9
         */

        Board board = new Board("0, 0, 7, 0, 5, 0, 4, 0, 9");
        List<Line> lines = board.getLines();

        List<Line> expectedLines = Arrays.asList(
                new Line(new int[]{0, 0, 7}, "Top row"),
                new Line(new int[]{0, 5, 0}, "Middle row"),
                new Line(new int[]{4, 0, 9}, "Bottom row"),
                new Line(new int[]{0, 0, 4}, "Left column"),
                new Line(new int[]{0, 5, 0}, "Middle column"),
                new Line(new int[]{7, 0, 9}, "Right column"),
                new Line(new int[]{0, 5, 9}, "Top left to bottom right"),
                new Line(new int[]{4, 5, 7}, "Bottom left to top right")
        );

        for (int i = 0; i < lines.size(); i++) {
            System.out.println("Index: " + i);

            Line actualLine = lines.get(i);
            Line expectedLine = expectedLines.get(i);

            System.out.println("Expected line: " + expectedLine.getName());

            assertArrayEquals(expectedLine.getValues(), actualLine.getValues());
            assertEquals(expectedLine.getName(), actualLine.getName());
        }
    }

}