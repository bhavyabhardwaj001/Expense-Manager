package com.expensemanager;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        ExpenseManager manager = new ExpenseManager();
        Scanner scanner = new Scanner(System.in);

        int choice = -1;

        while (choice != 0) {
            System.out.println("\n==== Personal Expense Manager ====");
            System.out.println("1. Add Category");
            System.out.println("2. List Categories");
            System.out.println("3. Add Expense");
            System.out.println("4. List Expenses");
            System.out.println("5. Show Total Expense");
            System.out.println("6. Show Total Expense by Category");
            System.out.println("0. Exit");

            choice = readInt(scanner, "Enter your choice: ");

            switch (choice) {
                case 1:
                    System.out.print("Enter category name: ");
                    String categoryName = scanner.nextLine();
                    manager.addCategory(categoryName);
                    break;

                case 2:
                    manager.listCategories();
                    break;

                case 3:
                    if (!manager.hasCategories()) {
                        System.out.println("Please add at least one category before adding expenses.");
                        break;
                    }

                    System.out.print("Enter date (e.g. 2025-11-24): ");
                    String date = scanner.nextLine();

                    manager.listCategories();
                    int catId = readInt(scanner, "Enter category ID: ");

                    double amount = readDouble(scanner, "Enter amount: ");

                    System.out.print("Enter description: ");
                    String desc = scanner.nextLine();

                    manager.addExpense(date, catId, amount, desc);
                    break;

                case 4:
                    manager.listExpenses();
                    break;

                case 5:
                    manager.printTotalExpense();
                    break;

                case 6:
                    if (!manager.hasCategories()) {
                        System.out.println("No categories available.");
                        break;
                    }
                    manager.listCategories();
                    int catIdForTotal = readInt(scanner, "Enter category ID: ");
                    manager.printTotalExpenseByCategory(catIdForTotal);
                    break;

                case 0:
                    System.out.println("Exiting... Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }

    // --------- Helper methods for safe input ---------

    private static int readInt(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine();
            try {
                return Integer.parseInt(line.trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid integer.");
            }
        }
    }

    private static double readDouble(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine();
            try {
                return Double.parseDouble(line.trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number (e.g. 250 or 250.50).");
            }
        }
    }
}
