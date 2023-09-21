package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CactpotSolver {

    /**
     * Determines which line on the board has the highest average payout.
     * @param board The board to check.
     * @return An object that contains the best line and the average payout from that line.
     */
    public PayoutCalculation getHighestAverageLine(Board board) {
        List<Integer> knownNumbers = getKnownNumbers(board.getBoard());

        Line bestLine = null;
        int highestAveragePayout = 0;

        for (Line line : board.getLines()) {
            int averagePayout = calculateAveragePayout(line, board, knownNumbers);

            if (averagePayout > highestAveragePayout) {
                bestLine = line;
                highestAveragePayout = averagePayout;
            }
        }

        return new PayoutCalculation(bestLine, highestAveragePayout);
    }

    /**
     * Calculates the average payout of a line.
     * @param line The line to check.
     * @param board The Cactpot board.
     * @param knownNumbers A list of known non-zero numbers.
     * @return The average payout for this line.
     */
    private int calculateAveragePayout(Line line, Board board, List<Integer> knownNumbers) {
        // The numbers that could be at the spot where "0" is
        List<Integer> possibleNumbers = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9));

        // Possible numbers cannot include known numbers outside of this line
        List<Integer> numbersInOtherLines = new ArrayList<>(knownNumbers);
        numbersInOtherLines.removeAll(Arrays.asList(line.getValues()));
        possibleNumbers.removeAll(numbersInOtherLines);

        final boolean firstWasKnown = line.getValue(0) > 0;
        final boolean secondWasKnown = line.getValue(1) > 0;
        final boolean thirdWasKnown = line.getValue(2) > 0;

        // Example: [0, 0, 0] will go from [1,2,3] until [9, 8, 7]
        // Another example: [0, 5, 0] will go from [1, 5, 2] until [9, 5, 8]

        int totalPayouts = 0;
        int n = 0;

        for (int first : possibleNumbers) {
            if (firstWasKnown) first = line.getValue(0); // If number was not zero, don't change the number

            for (int second : possibleNumbers) {
                if (secondWasKnown) second = line.getValue(1); // If number was not zero, don't change the number
                if (second == first) continue;

                for (int third : possibleNumbers) {
                    if (thirdWasKnown) third = line.getValue(2); // If number was not zero, don't change the number
                    if (third == second || third == first) continue;

                    n++;
                    totalPayouts += Payouts.getPayout(first + second + third);

                    if (thirdWasKnown) break; // Don't simulate more numbers if this number wasn't originally  zero
                }

                if (secondWasKnown) break; // Don't simulate more numbers if this number wasn't originally zero
            }

            if (firstWasKnown) break; // Don't simulate more numbers if this number wasn't originally zero
        }

        return totalPayouts / n;
    }

    /**
     * Makes a list of all the known NOT-ZERO numbers.
     * @param board The board to check.
     * @return A list of the non-zero numbers.
     */
    private List<Integer> getKnownNumbers(int[][] board) {
        List<Integer> knownNumbers = new ArrayList();

        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                int value = board[row][col];

                if (value > 0) {
                    knownNumbers.add(value);
                }
            }
        }

        return knownNumbers;
    }
}
