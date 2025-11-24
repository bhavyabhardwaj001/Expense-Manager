# Personal Expense Manager

A simple Java console-based application to help users track their daily expenses by category, view summaries, and store data persistently using text files.

## Features

- Add and list expense categories (e.g., Food, Travel, Shopping)
- Add expenses with date, category, amount, and description
- View all recorded expenses
- View total expenses across all categories
- View total expense for a specific category
- Input validation to prevent invalid numeric input
- Persistent storage using text files (`data/categories.txt` and `data/expenses.txt`)

## Technologies Used

- Java (console application)
- Collections (`ArrayList`)
- File I/O (`BufferedReader`, `BufferedWriter`)
- Basic exception handling (`try-catch`, `NumberFormatException`, `IOException`)

## Project Structure

```text
ExpenseManager/
├─ src/
│  └─ com/expensemanager/
│     ├─ Main.java
│     ├─ ExpenseManager.java
│     ├─ Expense.java
│     └─ Category.java
├─ data/
│  ├─ categories.txt
│  └─ expenses.txt
├─ screenshots/
│  ├─ menu.png
│  ├─ add-category.png
│  ├─ add-expense.png
│  └─ reports.png
├─ README.md
└─ .gitignore
