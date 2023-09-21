package test;

import main.Board;
import main.CactpotSolver;
import main.PayoutCalculation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CactpotSolverTest {

    CactpotSolver solver;

    @BeforeEach
    void setup() {
        solver = new CactpotSolver();
    }

    @Test
    void getHighestAveragePayout1() {
        /*
        0 0 3
        9 0 2
        0 0 1
        Guaranteed average profit of 10k in right column
         */

        Board board = new Board("0, 0, 3, 9, 0, 2, 0, 0, 1");

        PayoutCalculation calc = solver.getHighestAverageLine(board);

        assertEquals("Right column", calc.getLine().getName());
        assertArrayEquals(new int[]{3, 2, 1}, calc.getLine().getValues());
        assertEquals(10000, calc.getAveragePayout());
    }

    @Test
    void getHighestAveragePayout2() {
        /*
        7 0 0
        0 8 1
        0 0 9
        Guaranteed 7-8-9 high payout while 1-2-3 is not possible
         */

        Board board = new Board("7, 0, 0, 0, 8, 1, 0, 0, 9");

        PayoutCalculation calc = solver.getHighestAverageLine(board);

        assertEquals("Top left to bottom right", calc.getLine().getName());
        assertArrayEquals(new int[]{7, 8, 9}, calc.getLine().getValues());
        assertEquals(3600, calc.getAveragePayout());
    }

    @Test
    void getHighestAverageWithNoFullyKnownLine1a() {
        /*
        [9, 0, 3]
        [0, 8, 0]
        [0, 0, 1]
        Combinations and payouts:
        3+1+2 = 6 -> 10000
        3+1+4 = 8 -> 720
        3+1+5 = 9 -> 360
        3+1+6 = 10 -> 80
        3+1+7 = 11 -> 252
        (10000 + 720 + 360 + 80 + 252) / 5 = 2282
        Highest average should be 3-0-1 with 2282
         */
        Board board = new Board("9, 0, 3, 0, 8, 0, 0, 0, 1");

        PayoutCalculation calc = solver.getHighestAverageLine(board);

        assertEquals("Right column", calc.getLine().getName());
        assertArrayEquals(new int[]{3, 0, 1}, calc.getLine().getValues());
        assertEquals(2282, calc.getAveragePayout());
    }

    @Test
    void getHighestAverageWithNoFullyKnownLine1b() {
        /*
        [8, 0, 0]
        [0, 9, 1]
        [0, 0, 3]
        Same numbers as above but slightly shuffled
         */
        Board board = new Board("8, 0, 0, 0, 9, 1, 0, 0, 3");

        PayoutCalculation calc = solver.getHighestAverageLine(board);

        assertEquals("Right column", calc.getLine().getName());
        assertArrayEquals(new int[]{0, 1, 3}, calc.getLine().getValues());
        assertEquals(2282, calc.getAveragePayout());
    }

    @Test
    void getHighestAverageWithNoFullyKnownLine2() {
        /*
        [8, 0, 3]
        [0, 0, 0]
        [2, 9, 0]
        Best line should be diagonal bottom-left to top-right 3-0-2
        Combinations and payouts:
        2+3+1 = 6 -> 10000
        2+3+4 = 9 -> 360
        2+3+5 = 10 -> 80
        2+3+6 = 11 -> 252
        2+3+7 = 12 -> 108
        (10000 + 360 + 80 + 252 + 108) / 5 = 2160
        Highest average should be 2-0-3 with 2160
         */
        Board board = new Board("8, 0, 3, 0, 0, 0, 2, 9, 0");

        PayoutCalculation calc = solver.getHighestAverageLine(board);

        assertEquals("Bottom left to top right", calc.getLine().getName());
        assertArrayEquals(new int[]{2, 0, 3}, calc.getLine().getValues());
        assertEquals(2160, calc.getAveragePayout());
    }

    @Test
    void getHighestAverageWithOnlyOneKownNumber() {
        /*
        [8, 0, 0]
        [0, 1, 0]
        [0, 5, 9]
        Best line should be middle row 0-1-0 because of 1-2-3 = 10000
        and 7+8+9 (3600) cannot be in the same line
         */
        Board board = new Board("8, 0, 0, 0, 1, 0, 0, 5, 9");

        PayoutCalculation calc = solver.getHighestAverageLine(board);

        assertEquals("Middle row", calc.getLine().getName());
        assertArrayEquals(new int[]{0, 1, 0}, calc.getLine().getValues());
    }

    @Test
    void getHighestAverageWithOnlyOneKownNumber2() {
        /*
        [0, 3, 0]
        [0, 0, 7]
        [1, 0, 2]
        Best line should be middle row 0-0-7 because it can have 3 of the 4 highest sums:
        21 (1080), 23 (1800), 24 (3600)
        Meanwhile 1-2-3 (10000) is not possible anywhere
         */
        Board board = new Board("0, 3, 0, 0, 0, 7, 1, 0, 2");

        PayoutCalculation calc = solver.getHighestAverageLine(board);

        assertEquals("Middle row", calc.getLine().getName());
        assertArrayEquals(new int[]{0, 0, 7}, calc.getLine().getValues());
    }

}