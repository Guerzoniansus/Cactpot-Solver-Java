package main;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        System.out.println(">>> Input instructions: Enter 9 numbers, each separated by a comma.");
        System.out.println(">>> Four numbers should be between 1-9 without duplicates. The five others should be zeros.");
        System.out.println(">>> The first 3 numbers are the top row, the next 3 the middle row, and the last 3 the bottom row.");

        InputHandler inputHandler = new InputHandler();

        String input = inputHandler.getInput();
        Board board = new Board(input);

        System.out.println("You entered:");
        print2DArray(board.getBoard());

        PayoutCalculation calculation = new CactpotSolver().getHighestAverageLine(board);

        System.out.println("The line with the highest average payout is: " + calculation.getLine().getName());
        System.out.println("Average payout: " + calculation.getAveragePayout() + " gil");
    }

    private static void print2DArray(int[][] array) {
        System.out.println(Arrays.deepToString(array).replace("], ", "]\n").replace("[[", "[").replace("]]", "]"));
    }
}
