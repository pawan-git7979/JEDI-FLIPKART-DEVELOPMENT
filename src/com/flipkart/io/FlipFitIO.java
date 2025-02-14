package com.flipkart.io;

import java.util.Scanner;
import java.util.InputMismatchException;

public class FlipFitIO {
    private static final Scanner scanner = new Scanner(System.in);

    public static int getIntInput(String prompt) {
        while(true) {
            System.out.print(prompt);
            try {
                int value = scanner.nextInt();
                scanner.nextLine(); // consume leftover newline
                return value;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine(); // clear invalid input
            }
        }
    }

    public static String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }
}
