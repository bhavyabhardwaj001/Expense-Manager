package com.expensemanager;

public class Category {

    private int id;
    private String name;

    public Category(int id, String name) {
        this.id = id;
        this.name = name;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    // Setters (optional if you don't want to change id/name later)
    public void setName(String name) {
        this.name = name;
    }

    // For printing
    @Override
    public String toString() {
        return id + " - " + name;
    }
}
