import java.util.ArrayList;
import java.util.List;

class ExpenseTracker extends GUI {
    private List<Expense> expenses;

    // Constructor
    public ExpenseTracker() {
        expenses = new ArrayList<>();
    }

    // Setter
    public void addExpense(String category, double amount, String description) {
        Expense newExpense = new Expense(category, amount, description);
        expenses.add(newExpense);
    }

    // Getter
    public List<Expense> getExpenses() {
        return new ArrayList<>(expenses);
    }

    // Method to calculate total expenses
    private String printAllExpenses() {
        StringBuilder result = new StringBuilder();
        for (Expense expense : expenses) {
            result.append(expense.getCategory()).append(": ").append(expense.getDescription())
                    .append(" ——————————————————— ₱").append(expense.getAmount()).append("<br>");
        }
        return result.toString();
    }
    
    

    public String allExpensesGetter(String value) {
        value = printAllExpenses();
        return value;
    }
    
    
}