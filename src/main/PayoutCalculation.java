package main;

public class PayoutCalculation {
    private final Line line;
    private final int expectedPayout;

    /**
     * Creates an object that stores a line and its average expected payout.
     * @param line The line to store.
     * @param expectedPayout The average expected payout of this line.
     */
    public PayoutCalculation(Line line, int expectedPayout) {
        this.line = line;
        this.expectedPayout = expectedPayout;
    }

    /**
     * Returns the line of this calculation.
     * @return The line stored in this object.
     */
    public Line getLine() {
        return line;
    }

    /**
     * Returns the average expected payout of the line stored in this object.
     * @return The average expected payout.
     */
    public int getAveragePayout() {
        return expectedPayout;
    }
}
