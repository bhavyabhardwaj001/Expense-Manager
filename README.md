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


📌 How to Run (Using Eclipse IDE)

Clone the repository using Git.

Import the project into Eclipse as a Java Project.

Make sure a valid JDK is set in Eclipse.

Run the application:

Right-click Main.java → Run As → Java Application

Follow the on-screen menu options in the console.

💾 How Data Is Stored

Categories are stored in:
data/categories.txt
Format per line:

id|name


Expenses are stored in:
data/expenses.txt
Format per line:

id|date|categoryId|amount|description


Data is loaded automatically when the application starts.

Files are updated automatically when a new category or expense is added.

🚀 Possible Future Enhancements

Date-range-based and monthly expense reports

Budget tracking with warnings for overspending

Export reports to CSV / PDF

GUI version using JavaFX or a Web UI


👉 **Important:**  
Change the title/wording to match what *you* want (e.g., “Smart Expense Tracker” instead of “Personal Expense Manager”), and adjust sentences so it doesn’t look copy–pasted.

After editing:

- Save the file (`Ctrl + S`)  
- Then **Team → Commit…** with message:

```text
Update README with project details

