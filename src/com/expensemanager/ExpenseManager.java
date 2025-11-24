package com.expensemanager;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExpenseManager {

    private static final String DATA_DIR = "data";
    private static final String CATEGORIES_FILE = DATA_DIR + "/categories.txt";
    private static final String EXPENSES_FILE = DATA_DIR + "/expenses.txt";

    private List<Category> categories;
    private List<Expense> expenses;

    private int nextCategoryId = 1;
    private int nextExpenseId = 1;

    public ExpenseManager() {
        categories = new ArrayList<>();
        expenses = new ArrayList<>();

        ensureDataDirExists();
        loadCategoriesFromFile();
        loadExpensesFromFile();
    }

    // --------- Helper: ensure data directory exists ---------

    private void ensureDataDirExists() {
        File dir = new File(DATA_DIR);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    // --------- Category methods ---------

    public void addCategory(String name) {
        Category category = new Category(nextCategoryId, name);
        categories.add(category);
        nextCategoryId++;
        System.out.println("Category added: " + category);

        saveCategoriesToFile();
    }

    public void listCategories() {
        if (categories.isEmpty()) {
            System.out.println("No categories found.");
            return;
        }

        System.out.println("---- Categories ----");
        for (Category c : categories) {
            System.out.println(c);
        }
    }

    public Category findCategoryById(int id) {
        for (Category c : categories) {
            if (c.getId() == id) {
                return c;
            }
        }
        return null; // not found
    }

    public boolean hasCategories() {
        return !categories.isEmpty();
    }

    // --------- Expense methods ---------

    public void addExpense(String date, int categoryId, double amount, String description) {
        Category category = findCategoryById(categoryId);
        if (category == null) {
            System.out.println("Invalid category ID. Expense not added.");
            return;
        }

        if (amount <= 0) {
            System.out.println("Amount must be positive. Expense not added.");
            return;
        }

        Expense expense = new Expense(nextExpenseId, date, category, amount, description);
        expenses.add(expense);
        nextExpenseId++;
        System.out.println("Expense added: " + expense);

        saveExpensesToFile();
    }

    public void listExpenses() {
        if (expenses.isEmpty()) {
            System.out.println("No expenses found.");
            return;
        }

        System.out.println("---- All Expenses ----");
        for (Expense e : expenses) {
            System.out.println(e);
        }
    }

    // --------- Report methods ---------

    public double getTotalExpense() {
        double total = 0.0;
        for (Expense e : expenses) {
            total += e.getAmount();
        }
        return total;
    }

    public void printTotalExpense() {
        double total = getTotalExpense();
        System.out.println("Total expense so far: " + total);
    }

    public double getTotalExpenseByCategory(int categoryId) {
        double total = 0.0;
        for (Expense e : expenses) {
            if (e.getCategory().getId() == categoryId) {
                total += e.getAmount();
            }
        }
        return total;
    }

    public void printTotalExpenseByCategory(int categoryId) {
        Category category = findCategoryById(categoryId);
        if (category == null) {
            System.out.println("Invalid category ID.");
            return;
        }

        double total = getTotalExpenseByCategory(categoryId);
        System.out.println("Total expense in category '" + category.getName() + "': " + total);
    }

    // --------- File saving methods ---------

    private void saveCategoriesToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CATEGORIES_FILE))) {
            for (Category c : categories) {
                // format: id|name
                writer.write(c.getId() + "|" + c.getName());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving categories: " + e.getMessage());
        }
    }

    private void saveExpensesToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(EXPENSES_FILE))) {
            for (Expense e : expenses) {
                // format: id|date|categoryId|amount|description
                writer.write(
                    e.getId() + "|" +
                    e.getDate() + "|" +
                    e.getCategory().getId() + "|" +
                    e.getAmount() + "|" +
                    e.getDescription()
                );
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving expenses: " + e.getMessage());
        }
    }

    // --------- File loading methods ---------

    private void loadCategoriesFromFile() {
        File file = new File(CATEGORIES_FILE);
        if (!file.exists()) {
            return; // nothing to load
        }

        int maxId = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = reader.readLine()) != null) {
                // expected: id|name
                String[] parts = line.split("\\|");
                if (parts.length >= 2) {
                    try {
                        int id = Integer.parseInt(parts[0]);
                        String name = parts[1];

                        Category category = new Category(id, name);
                        categories.add(category);

                        if (id > maxId) {
                            maxId = id;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Skipping invalid category line: " + line);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading categories: " + e.getMessage());
        }

        nextCategoryId = maxId + 1;
    }

    private void loadExpensesFromFile() {
        File file = new File(EXPENSES_FILE);
        if (!file.exists()) {
            return; // nothing to load
        }

        int maxId = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = reader.readLine()) != null) {
                // expected: id|date|categoryId|amount|description
                String[] parts = line.split("\\|");
                if (parts.length >= 5) {
                    try {
                        int id = Integer.parseInt(parts[0]);
                        String date = parts[1];
                        int categoryId = Integer.parseInt(parts[2]);
                        double amount = Double.parseDouble(parts[3]);
                        String description = parts[4];

                        Category category = findCategoryById(categoryId);
                        if (category == null) {
                            System.out.println("Skipping expense with unknown category ID: " + categoryId);
                            continue;
                        }

                        Expense expense = new Expense(id, date, category, amount, description);
                        expenses.add(expense);

                        if (id > maxId) {
                            maxId = id;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Skipping invalid expense line: " + line);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading expenses: " + e.getMessage());
        }

        nextExpenseId = maxId + 1;
    }
}
