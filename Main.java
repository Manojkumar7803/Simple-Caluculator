package manoj;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main extends JFrame implements ActionListener {

    // Encapsulation: private data members
    private JTextField display;
    private double firstNumber;
    private String operator = "";
    private boolean startNewNumber = true;

    // Constructor
    public Main() {
        setTitle("Simple Calculator");
        setSize(350, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(5, 5));

        // Display field
        display = new JTextField("0");
        display.setFont(new Font("Arial", Font.BOLD, 28));
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setEditable(false);
        add(display, BorderLayout.NORTH);

        // Button panel
        JPanel panel = new JPanel(new GridLayout(4, 4, 5, 5));

        String[] buttons = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "C", "0", "=", "+"
        };

        for (String text : buttons) {
            JButton button = new JButton(text);
            button.setFont(new Font("Arial", Font.BOLD, 20));

            // Event-driven programming
            button.addActionListener(this);
            panel.add(button);
        }

        add(panel, BorderLayout.CENTER);
        setVisible(true);
    }

    // Handles button click events
    @Override
    public void actionPerformed(ActionEvent e) {
        String input = e.getActionCommand();

        // Clear the calculator
        if (input.equals("C")) {
            display.setText("0");
            firstNumber = 0;
            operator = "";
            startNewNumber = true;
            return;
        }

        // Number button
        if (input.matches("[0-9]")) {
            if (startNewNumber || display.getText().equals("0")) {
                display.setText(input);
                startNewNumber = false;
            } else {
                display.setText(display.getText() + input);
            }
            return;
        }

        // Arithmetic operator
        if (input.matches("[+\\-*/]")) {
            firstNumber = Double.parseDouble(display.getText());
            operator = input;
            startNewNumber = true;
            return;
        }

        // Calculate result
        if (input.equals("=") && !operator.isEmpty()) {
            double secondNumber =
                Double.parseDouble(display.getText());

            double result;

            switch (operator) {
                case "+":
                    result = firstNumber + secondNumber;
                    break;
                case "-":
                    result = firstNumber - secondNumber;
                    break;
                case "*":
                    result = firstNumber * secondNumber;
                    break;
                case "/":
                    if (secondNumber == 0) {
                        display.setText("Cannot divide by zero");
                        operator = "";
                        startNewNumber = true;
                        return;
                    }
                    result = firstNumber / secondNumber;
                    break;
                default:
                    return;
            }

            display.setText(String.valueOf(result));
            operator = "";
            startNewNumber = true;
        }
    }

    // Main method: program entry point
    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::new);
    }
}

