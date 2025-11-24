package com.expensemanager;

public class Expense {

    private int id;
    private String date;        // later we can switch to LocalDate if you want
    private Category category;
    private double amount;
    private String description;

    public Expense(int id, String date, Category category, double amount, String description) {
        this.id = id;
        this.date = date;
        this.category = category;
        this.amount = amount;
        this.description = description;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public Category getCategory() {
        return category;
    }

    public double getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    // Setters (optional, if you want to allow edits)
    public void setDate(String date) {
        this.date = date;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Expense ID: " + id +
               ", Date: " + date +
               ", Category: " + category.getName() +
               ", Amount: " + amount +
               ", Description: " + description;
    }
}
