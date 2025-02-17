package com.flipkart.utils;

import java.util.List;
import java.util.Scanner;
import java.util.*;

public class FlipFitIOUtils {

    public static int getIntInput(String prompt, Scanner scanner) {
        System.out.println(prompt);
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a valid number.");
            scanner.next(); // Consume the invalid input
        }
        int input = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        return input;
    }

    public static String getStringInput(String prompt, Scanner scanner) {
        System.out.println(prompt);
        String input = scanner.nextLine(); // Always use nextLine() inside the utility function.
        return input;
    }



    // ✅ Add this method to allow selection from a list
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
                    return choice; // ✅ Valid choice
                }
            }
            System.out.println("Invalid choice. Please enter a number between 1 and " + options.size() + ".");
            scanner.next(); // Discard invalid input
        }
    }
}
