package com.flipkart.client;

import java.util.Scanner;

public class IOUtils {
    private static final Scanner scanner = new Scanner(System.in);

    public static int getIntInput(String message) {
        System.out.print(message);
        while (!scanner.hasNextInt()) {
            System.out.print("Please enter a valid integer: ");
            scanner.next(); // Consume the invalid input
        }
        int input = scanner.nextInt();
        scanner.nextLine(); // Consume the leftover newline
        return input;
    }

    public static String getStringInput(String message) {
        System.out.print(message);
        return scanner.nextLine();
    }
}
