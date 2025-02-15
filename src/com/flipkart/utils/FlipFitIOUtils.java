package com.flipkart.utils;

import java.util.Scanner;

public class FlipFitIOUtils {

    public static int getIntInput(String prompt, Scanner scanner) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a valid number.");
            scanner.next();
        }
        return scanner.nextInt();
    }

    public static String getStringInput(String prompt, Scanner scanner) {
        System.out.print(prompt);
        scanner.nextLine(); // Consume the newline if needed
        return scanner.nextLine(); // Reads the full input line
    }

}
