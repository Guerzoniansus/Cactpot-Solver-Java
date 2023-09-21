package main;

public class Line {
    private final int[] values;
    private final String name;

    public Line(int[] values, String name) {
        if (values.length != 3 ) {
            throw new IllegalArgumentException("A line must have 3 values");
        }

        this.values = values;
        this.name = name;
    }

    public int getValue(int index) {
        return values[index];
    }

    public int[] getValues() {
        return values;
    }

    public String getName() {
        return name;
    }

    public boolean contains(int value) {
        for (int i : values) {
            if (i == value) {
                return true;
            }
        }

        return false;
    }
}
