package com.flipkart.io;

import java.util.Scanner;
import java.util.InputMismatchException;

/**
 * Utility class for handling user input.
 * Provides methods to safely retrieve integer and string inputs from the user.
 */
public class FlipFitIO {
    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Prompts the user to enter an integer and returns the valid input.
     * The method ensures that the input is a valid integer by handling invalid inputs gracefully.
     *
     * @param prompt The message to display to the user.
     * @return The valid integer input entered by the user.
     */
    public static int getIntInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                int value = scanner.nextInt();
                scanner.nextLine(); // Consume leftover newline character after input
                return value;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine(); // Clear invalid input from the scanner buffer
            }
        }
    }

    /**
     * Prompts the user to enter a string and returns the valid input.
     *
     * @param prompt The message to display to the user.
     * @return The string input entered by the user.
     */
    public static String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine(); // Read and return the string input
    }
}
