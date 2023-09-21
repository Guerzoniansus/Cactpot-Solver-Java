package test;

import main.InputHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InputHandlerTest {

    InputHandler inputHandler;

    @BeforeEach
    void setup() {
        inputHandler = new InputHandler();
    }

    @Test
    void returnsTrueOnValidInput() {
        String input = "4,0,1,0,3,9,0,0,0";
        boolean result = inputHandler.isValid(input);
        assertTrue(result);
    }

    @Test
    void worksWithSpaces() {
        String input = "4, 0, 1, 0, 3, 9, 0, 0, 0";
        boolean result = inputHandler.isValid(input);
        assertTrue(result);
    }

    @Test
    void returnsTrueOnValidInput2() {
        String input = "0, 0, 3, 9, 0, 2, 0, 0, 1";
        boolean result = inputHandler.isValid(input);
        assertTrue(result);
    }

    @Test
    void returnsFalseOnNotExactlyNineNumbers() {
        String input = "4,0,1,0,3,9,0,0,0,7"; // 10 numbers
        boolean result = inputHandler.isValid(input);
        assertFalse(result);
    }

    @Test
    void returnsFalseOnNotExactlyNineNumbers2() {
        String input = "4,0,1,0,3,0,0,0"; // 8 numbers
        boolean result = inputHandler.isValid(input);
        assertFalse(result);
    }

    @Test
    void returnsFalseOnMoreThanFiveZeros() {
        String input = "0,0,0,0,0,0,3,7,5"; // 6 zeros
        boolean result = inputHandler.isValid(input);
        assertFalse(result);
    }

    @Test
    void returnsFalseOnLessThanFiveZeros() {
        String input = "0,0,0,0,1,1,3,7,5"; // 4 zeros
        boolean result = inputHandler.isValid(input);
        assertFalse(result);
    }

    @Test
    void returnsFalseOnLetters() {
        String input = "4,0,1,0,B,9,0,0,0";
        boolean result = inputHandler.isValid(input);
        assertFalse(result);
    }

    @Test
    void returnsFalseOnNumbersGreaterThan9() {
        String input = "4,0,1,0,3,999999,0,0,0";
        boolean result = inputHandler.isValid(input);
        assertFalse(result);
    }

    @Test
    void returnsFalseOnNegativeNumbers() {
        String input = "4,0,1,0,3,-999,0,0,0";
        boolean result = inputHandler.isValid(input);
        assertFalse(result);
    }

    @Test
    void returnsFalseOnDuplicateNumbers() {
        String input = "4,0,4,0,3,9,0,0,0";
        boolean result = inputHandler.isValid(input);
        assertFalse(result);
    }

    @Test
    void returnsFalseOnEmptyInput() {
        String input = "";
        boolean result = inputHandler.isValid(input);

        assertFalse(result);
    }

    @Test
    void returnsFalseOnNullInput() {
        String input = null;
        boolean result = inputHandler.isValid(input);

        assertFalse(result);
    }
}