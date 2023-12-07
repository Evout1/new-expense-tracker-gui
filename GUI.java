import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GUI {

    private static ExpenseTracker expenseTracker = new ExpenseTracker();

    private static JFrame frame = new JFrame();
    private static JPanel panel = new JPanel();
    private static JLabel appName;
    private static JLabel categoryLabel;
    private static JLabel descriptionLabel;
    private static JLabel amountLabel;
    private static JComboBox<String> category;
    static String[] categ = {"", "Health", "Entertainment", "Bills", "Miscellaneous"};
    private static JTextField amountTextField = new JTextField();
    private static JTextField descriptionTextField = new JTextField();
    private static JTextArea expenseHistory = new JTextArea();
    private static JScrollPane scrollPane;

    private static String categoryInput = "";
    private static String descriptionInput = "";
    private static double amountInput = 0;

    private static void baseGUI() {
        frame.setTitle("Expense Tracker");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(500, 500);

        panel.setLayout(null);

        appName = new JLabel("Expense Tracker");
        appName.setBounds(150, 10, 200, 30);
        appName.setFont(new Font("Perpetua Titling MT", Font.BOLD, 18));

        categoryLabel = new JLabel("Category:");
        categoryLabel.setBounds(50, 60, 100, 20);

        category = new JComboBox<>(categ);
        category.setBounds(150, 60, 200, 25);

        descriptionLabel = new JLabel("Expense Description:");
        descriptionLabel.setBounds(50, 100, 150, 20);

        amountLabel = new JLabel("Amount:");
        amountLabel.setBounds(50, 140, 100, 20);

        descriptionTextField.setBounds(200, 100, 150, 25);
        amountTextField.setBounds(150, 140, 200, 25);

        expenseHistory.setEditable(false);
        scrollPane = new JScrollPane(expenseHistory);
        scrollPane.setBounds(50, 180, 400, 200);

        panel.add(appName);
        panel.add(categoryLabel);
        panel.add(category);
        panel.add(descriptionLabel);
        panel.add(descriptionTextField);
        panel.add(amountLabel);
        panel.add(amountTextField);
        panel.add(scrollPane);

        frame.add(panel);
        frame.setVisible(true);
    }

    private static void expenses() {
        expenseTracker.addExpense(categoryInput, amountInput, descriptionInput);
        updateExpenseHistory();
    }

    private static void updateExpenseHistory() {
        StringBuilder historyText = new StringBuilder();
        for (Expense expense : expenseTracker.getExpenses()) {
            historyText.append(String.format("%s: %s - ₱%.2f%n",
                    expense.getCategory(), expense.getDescription(), expense.getAmount()));
        }
        expenseHistory.setText(historyText.toString());
    }

    public static void getInput(String descriptionTextField, String amountTextField) {
        descriptionInput = descriptionTextField;
        amountInput = Double.parseDouble(amountTextField);
    }

    public static void getCategory() {
        categoryInput = (String) category.getSelectedItem();
    }

    public static void main(String[] args) {
        baseGUI();

        category.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getCategory();
            }
        });

        descriptionTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER && validateInput()) {
                    getInput(descriptionTextField.getText(), amountTextField.getText());
                    descriptionTextField.setText("");
                    amountTextField.setText("");
                    expenses();
                }
            }
        });

        amountTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER && validateInput()) {
                    getInput(descriptionTextField.getText(), amountTextField.getText());
                    descriptionTextField.setText("");
                    amountTextField.setText("");
                    expenses();
                }
            }
        });
    }

    private static boolean validateInput() {
        return !descriptionTextField.getText().isEmpty() &&
                !amountTextField.getText().isEmpty() &&
                amountTextField.getText().matches("\\d+(\\.\\d*)?");
    }
}
