package main;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class InputHandler {

    /**
     * Gets user input from the console.
     * @return The entered input.
     */
    public String getInput() {
        String input;

        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println("Please enter a valid input:");
            input = scanner.nextLine();
        } while (isValid(input) == false);

        return input;
    }

    /**
     * Checks if an input is valid.
     * @param input The text input.
     * @return True if valid, false if invalid.
     */
    public boolean isValid(String input) {
        if (input == null)
            return false;

        String[] characters = input.trim().replace(" ", "").split(",");

        if (characters.length != 9) {
            error("9 characters are required.");
            return false;
        }

        if (hasFiveZeros(characters) == false) {
            error("There should be five zeros.");
            return false;
        }

        Set<String> set = new HashSet<>(Arrays.asList(characters));

        // 4 known numbers, the rest are 0, so there should be 5 unique characters
        if (set.size() != 5) {
            error("There should be 5 unique characters: 4 numbers between 1-9 and the rest 0.");
            return false;
        }

        for (String character : set) {
            if (isNumeric(character) == false) {
                error("Not all entered characters are a number");
                return false;
            }

            int number = Integer.parseInt(character);

            if (number < 0 || number > 9) {
                error("Not all characters are between 0 and 9");
                return false;
            }

        }

        return true;
    }

    /**
     * Prints an error message in the console.
     * @param msg The message to print.
     */
    private void error(String msg) {
        System.out.println("Error: " + msg);
    }

    /**
     * Checks if a given character is a number.
     * @param character The character to check.
     * @return True if the character is numeric, false if not.
     */
    private boolean isNumeric(String character) {
        try {
            Integer.parseInt(character);
            return true;
        } catch(NumberFormatException e) {
            return false;
        }
    }

    /**
     * Checks if a given array of characters contains exactly five zeros.
     * @param characters The characters to check.
     * @return True if the array contains exactly five zeros, false if not.
     */
    private boolean hasFiveZeros(String[] characters) {
        int count = 0;

        for (String character : characters) {
            if (character.equals("0")) {
                count++;
            }
        }

        return count == 5;
    }
}
