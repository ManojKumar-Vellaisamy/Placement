package com.rest.CLI;

import java.util.Scanner;

/** One shared Scanner + safe input helpers (avoids nextInt/nextLine bugs). */
public class Input {

    private static final Scanner SC = new Scanner(System.in);

    public static String text(String prompt) {
        System.out.print(prompt);
        return SC.hasNextLine() ? SC.nextLine().trim() : "";
    }

    public static int integer(String prompt) {
        while (true) {
            try {
                return Integer.parseInt(text(prompt));
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    public static double decimal(String prompt) {
        while (true) {
            try {
                return Double.parseDouble(text(prompt));
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid amount.");
            }
        }
    }
}
