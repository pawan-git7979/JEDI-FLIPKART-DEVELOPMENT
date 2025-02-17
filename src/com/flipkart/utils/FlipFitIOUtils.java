package com.flipkart.utils;

import java.util.List;
import java.util.Scanner;

/**
 * Utility class providing methods for obtaining user input in a safe and consistent manner.
 * It offers methods for getting integer and string inputs, as well as allowing the user to select
 * an option from a list.
 */
public class FlipFitIOUtils {

    /**
     * Prompts the user for an integer input and ensures that the input is a valid number.
     * If the input is invalid, it will prompt the user again until a valid integer is provided.
     *
     * @param prompt The message to display before requesting input.
     * @param scanner The Scanner object used to read user input.
     * @return The integer value input by the user.
     */
    public static int getIntInput(String prompt, Scanner scanner) {
        System.out.println(prompt);
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a valid number.");
            scanner.next(); // Consume the invalid input
        }
        int input = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character after the integer input
        return input;
    }

    /**
     * Prompts the user for a string input.
     *
     * @param prompt The message to display before requesting input.
     * @param scanner The Scanner object used to read user input.
     * @return The string input provided by the user.
     */
    public static String getStringInput(String prompt, Scanner scanner) {
        System.out.println(prompt);
        String input = scanner.nextLine(); // Always use nextLine() to capture full input
        return input;
    }

    /**
     * Displays a list of options to the user and prompts them to select one.
     * Ensures that the user selects a valid option from the list.
     *
     * @param <T> The type of the options in the list.
     * @param scanner The Scanner object used to read user input.
     * @param title The title displayed before the options.
     * @param options The list of options to be displayed.
     * @return The index of the selected option (1-based).
     */
    public static <T> int getChoice(Scanner scanner, String title, List<T> options) {
        System.out.println("\n=== " + title + " ===");
        for (int i = 0; i < options.size(); i++) {
            System.out.println((i + 1) + ". " + options.get(i));
        }
        System.out.print("Enter your choice (1-" + options.size() + "): ");

        int choice;
        while (true) {
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                if (choice >= 1 && choice <= options.size()) {
                    return choice; // Valid choice
                }
            }
            System.out.println("Invalid choice. Please enter a number between 1 and " + options.size() + ".");
            scanner.next(); // Discard invalid input
        }
    }
}
