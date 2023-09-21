package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A board represents a 9x9 board with four numbers from 1-9 and five numbers that are 0.
 * Example:
 * <pre>
 * 0 0 7
 * 0 5 0
 * 4 0 9 </pre>
 * There are 8 lines in the following order: top row, middle row, bottom row,
 * left column, middle column, right column, top left to bottom right, and bottom left to top right.
 */
public class Board {

    private final int[][] board;
    private final List<Line> lines;

    /**
     * Creates a new Board object.
     * @param input A comma-separated value of numbers from left-to-right, top-to-bottom, representing a 9x9 grid.
     */
    public Board(String input) {
        lines = new ArrayList(8);
        board = new int[3][3];

        String[] chars = input.replaceAll(" ", "").split(",");

        // Create 2D array
        int n = 0;

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                board[row][col] = Integer.parseInt(chars[n]);
                n++;
            }
        }

        // Create lines
        lines.add(createLine(chars, 0, 1, 2, "Top row"));
        lines.add(createLine(chars, 3, 4, 5, "Middle row"));
        lines.add(createLine(chars, 6, 7, 8, "Bottom row"));
        lines.add(createLine(chars, 0, 3, 6, "Left column"));
        lines.add(createLine(chars, 1, 4, 7, "Middle column"));
        lines.add(createLine(chars, 2, 5, 8, "Right column"));
        lines.add(createLine(chars, 0, 4, 8, "Top left to bottom right"));
        lines.add(createLine(chars, 6, 4, 2, "Bottom left to top right"));
    }

    /**
     * Returns the board.
     * @return A 2D array representation of the board.
     */
    public int[][] getBoard() {
        return board;
    }

    /**
     * Get all 8 lines from this board. 3 rows, 3 columns, and 2 diagonal lines.
     * @return The lines in this board.
     */
    public List<Line> getLines() {
        return lines;
    }

    /**
     * Creates a new 3-value Line from the given Board input and indexes.
     * @param boardCharacters The characters that represents the board (top to bottom, left to right, diagonal).
     * @param index0 The position of the first number of the line in the board.
     * @param index1 The position of the second number of the line in the board.
     * @param index2 The position of the third number of the line in the board.
     * @param name The name of the line.
     * @return The new Line object.
     */
    private Line createLine(String[] boardCharacters, int index0, int index1, int index2, String name) {
        int[] values = new int[3];

        values[0] = Integer.parseInt(boardCharacters[index0]);
        values[1] = Integer.parseInt(boardCharacters[index1]);
        values[2] = Integer.parseInt(boardCharacters[index2]);

        return new Line(values, name);
    }
}
