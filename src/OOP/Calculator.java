package OOP;

import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("List of operations: add subtract multiply divide alphabetize");
        System.out.println("Enter an operation:");
        String operation = scanner.nextLine().trim().toLowerCase();

        switch (operation) {
            case "add":
                System.out.println("Enter two integers:");
                if (!scanner.hasNextInt()) {
                    System.out.println("Invalid input entered. Terminating...");
                    return;
                }
                int int1 = scanner.nextInt();
                if (!scanner.hasNextInt()) {
                    System.out.println("Invalid input entered. Terminating...");
                    return;
                }
                int int2 = scanner.nextInt();
                System.out.println("Answer: " + (int1 + int2));
                break;

            case "subtract":
                System.out.println("Enter two integers:");
                if (!scanner.hasNextInt()) {
                    System.out.println("Invalid input entered. Terminating...");
                    return;
                }
                int sub1 = scanner.nextInt();
                if (!scanner.hasNextInt()) {
                    System.out.println("Invalid input entered. Terminating...");
                    return;
                }
                int sub2 = scanner.nextInt();
                System.out.println("Answer: " + (sub1 - sub2));
                break;

            case "multiply":
                System.out.println("Enter two doubles:");
                if (!scanner.hasNextDouble()) {
                    System.out.println("Invalid input entered. Terminating...");
                    return;
                }
                double mul1 = scanner.nextDouble();
                if (!scanner.hasNextDouble()) {
                    System.out.println("Invalid input entered. Terminating...");
                    return;
                }
                double mul2 = scanner.nextDouble();
                System.out.printf("Answer: %.2f%n", mul1 * mul2);
                break;

            case "divide":
                System.out.println("Enter two doubles:");
                if (!scanner.hasNextDouble()) {
                    System.out.println("Invalid input entered. Terminating...");
                    return;
                }
                double div1 = scanner.nextDouble();
                if (!scanner.hasNextDouble()) {
                    System.out.println("Invalid input entered. Terminating...");
                    return;
                }
                double div2 = scanner.nextDouble();
                if (div2 == 0) {
                    System.out.println("Invalid input entered. Terminating...");
                    return;
                }
                System.out.printf("Answer: %.2f%n", div1 / div2);
                break;

            case "alphabetize":
                System.out.println("Enter two words:");
                if (!scanner.hasNext()) {
                    System.out.println("Invalid input entered. Terminating...");
                    return;
                }
                String word1 = scanner.next();
                if (!scanner.hasNext()) {
                    System.out.println("Invalid input entered. Terminating...");
                    return;
                }
                String word2 = scanner.next();

                int result = word1.compareToIgnoreCase(word2);
                if (result == 0) {
                    System.out.println("Answer: Chicken or Egg.");
                } else if (result < 0) {
                    System.out.println("Answer: " + word1 + " comes before " + word2 + " alphabetically.");
                } else {
                    System.out.println("Answer: " +word2 + " comes before " + word1 + " alphabetically.");
                }
                break;

            default:
                System.out.println("Invalid input entered. Terminating...");
                return;
        }

        scanner.close();
    }
}
