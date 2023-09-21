package test;

import main.Line;
import main.Payouts;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static java.util.Map.entry;
import static org.junit.jupiter.api.Assertions.*;

class PayoutsTest {

    private static Map<Integer, Integer> PAYOUTS = Map.ofEntries(
            entry(6, 10_000), entry(7, 36), entry(8, 720),
            entry(9, 360), entry(10, 80), entry(11, 252),
            entry(12, 108), entry(13, 72), entry(14, 54),
            entry(15, 180), entry(16, 72), entry(17, 180),
            entry(18, 119), entry(19, 36), entry(20, 306),
            entry(21, 1080), entry(22, 144), entry(23, 1800),
            entry(24, 3600)
    );

    @Test
    void getPayoutWithLineObject() {
        PAYOUTS.keySet().forEach(key -> {
            Line line = new Line(new int[]{key, 0, 0}, "Top row");
            assertEquals(PAYOUTS.get(key), Payouts.getPayout(line));
        });
    }

    @Test
    void getPayoutWithArray() {
        PAYOUTS.keySet().forEach(key -> {
            Line line = new Line(new int[]{key, 0, 0}, "Top row");
            assertEquals(PAYOUTS.get(key), Payouts.getPayout(line.getValues()));
        });
    }
}