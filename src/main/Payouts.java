package main;

import java.util.Map;

import static java.util.Map.entry;

public class Payouts {

    /**
     * Key = sum of line.
     * Value = payout.
     */
    private static final Map<Integer, Integer> PAYOUTS = Map.ofEntries(
            entry(6, 10_000), entry(7, 36), entry(8, 720),
            entry(9, 360), entry(10, 80), entry(11, 252),
            entry(12, 108), entry(13, 72), entry(14, 54),
            entry(15, 180), entry(16, 72), entry(17, 180),
            entry(18, 119), entry(19, 36), entry(20, 306),
            entry(21, 1080), entry(22, 144), entry(23, 1800),
            entry(24, 3600)
    );

    /**
     * Returns the payout of this line.
     * @param line The line to calculate the payout for.
     * @return The payout in Gil.
     */
    public static int getPayout(Line line) {
        return getPayout(line.getValues());
    }

    /**
     * Returns the payout of this line.
     * This lower level version is for better performance.
     * @param line An array of 3 line values.
     * @return The payout in Gil.
     */
    public static int getPayout(int[] line) {
        int sum = 0;

        for (int value : line) {
            sum += value;
        }

        return PAYOUTS.get(sum);
    }

    /**
     * Returns the payout for a sum of numbers.
     * @param sum The sum of numbers.
     * @return The payout in Gil.
     */
    public static int getPayout(int sum) {
        if (sum < 6 || sum > 24) throw new IllegalArgumentException("A sum must >= 6 and <= 24, so not [" + sum + "]");

        return PAYOUTS.get(sum);
    }
}
