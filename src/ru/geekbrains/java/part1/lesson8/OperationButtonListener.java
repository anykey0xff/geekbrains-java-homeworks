package ru.geekbrains.java.part1.lesson8;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

public class OperationButtonListener implements ActionListener {
    private final JTextField inputField;
    private final StringBuilder sb;

    public OperationButtonListener(JTextField inputField) {
        this.inputField = inputField;
        sb = new StringBuilder();
    }

    private boolean isDouble(String s) throws NumberFormatException {
        try {
            Double.parseDouble(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isInteger(String s) throws NumberFormatException {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String operation = ((JButton) e.getSource()).getText();

        if (inputField.getText().length() == 0) {
            inputField.setText("");
            return;
        }
        sb.append(inputField.getText());

        switch (operation) {
            case "C" -> doClearOperation();
            case "DEL" -> doDeleteOperation(sb);
            case "SQRT" -> doSQRTCalculation(sb);
            case "=" -> doCalculation(sb);
            case "ScriptEngine =" -> {
                try {
                    doCalculationScriptEngine(sb);
                } catch (ScriptException scriptException) {
                    scriptException.printStackTrace();
                }
            }
            case "." -> doAddDotSymbol(sb, operation);
            default -> doAddOperationSymbol(sb, operation);
        }

        inputField.setText(inputField.getText().replace(',', '.'));
        sb.setLength(0);

    }

    private void doCalculationScriptEngine(StringBuilder sb) throws ScriptException {
        ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
        ScriptEngine scriptEngine = scriptEngineManager.getEngineByName("JavaScript");
        Object expResult = scriptEngine.eval(sb.toString());
        inputField.setText(expResult.toString());
    }

    private void doCalculation(StringBuilder sb) {

        String lastChar = findNumber(sb, sb.length());
        if (lastChar.equals("")) {
            doDeleteOperation(sb);
        }

        char firstChar = sb.charAt(0);
        if (firstChar == '-')
            sb.insert(0, '0');

        double[] numbers = new double[0];
        char[] operations = new char[0];
        int index = sb.length();
        while (index > 0) {
            String lastNumber = findNumber(sb, index);
            if (lastNumber.equals("")) {
                operations = addToArray(operations, sb.charAt(index - 1));
                index--;
            } else {
                numbers = addToArray(numbers, Double.parseDouble(lastNumber));
                index -= lastNumber.length();
            }
        }

//        for (int i = operations.length - 1; i >= 0; i--) {
//
//            if (operations.length == 0)
//                break;
//            if (numbers.length == 1)
//                break;
//
//            double leftNumber = numbers[i + 1];
//            double rightNumber = numbers[i];
//            char operation = operations[i];
//            double result = 0;
//
//            switch (operation) {
//                case '+' -> result = leftNumber + rightNumber;
//                case '-' -> result = leftNumber - rightNumber;
//                case '*' -> result = leftNumber * rightNumber;
//                case '/' -> {
//                    if (rightNumber == 0) {
//                        numbers[0] = 0;
//                        break;
//                    }
//                    result = leftNumber / rightNumber;
//                }
//                default -> {
//                }
//            }
//
//            numbers[i] = result;
//        }

        double result = doCalculationWithPriorities(operations, numbers);

        DecimalFormat decimalFormat = new DecimalFormat("#.####");
        inputField.setText(decimalFormat.format(result));
        sb.setLength(0);

    }

    private double doCalculationWithPriorities(char[] operations, double[] numbers) {
        for (int priority = 1; priority < 3; priority++) {
            int i = operations.length - 1;
            while (i >= 0) {

                if (operations.length == 0)
                    break;
                if (numbers.length == 1)
                    break;

                if (priority == 1 && !(operations[i] == '*' || operations[i] == '/')) {
                    i--;
                    continue;
                } else if (priority == 2 && !(operations[i] == '+' || operations[i] == '-')) {
                    i--;
                    continue;
                }

                double leftNumber = numbers[i + 1];
                double rightNumber = numbers[i];
                char operation = operations[i];
                double result = 0;

                switch (operation) {
                    case '+' -> result = leftNumber + rightNumber;
                    case '-' -> result = leftNumber - rightNumber;
                    case '*' -> result = leftNumber * rightNumber;
                    case '/' -> {
                        if (rightNumber == 0) {
                            numbers[0] = 0;
                            break;
                        }
                        result = leftNumber / rightNumber;
                    }
                    default -> {
                    }
                }

                operations = removeFromArray(operations, i);
                numbers = removeFromArray(numbers, i + 1);
                numbers[i] = result;

                i--;
            }
        }
        return numbers[0];
    }

    private double[] addToArray(double[] array, double value) {

        double[] newArray = new double[array.length + 1];
        System.arraycopy(array, 0, newArray, 0, array.length);
        newArray[array.length] = value;

        return newArray;
    }

    private char[] addToArray(char[] array, char value) {

        char[] newArray = new char[array.length + 1];
        System.arraycopy(array, 0, newArray, 0, array.length);
        newArray[array.length] = value;

        return newArray;
    }

    private double[] removeFromArray(double[] array, int index) {

        double[] newArray = new double[array.length - 1];
        if (index - 1 >= 0)
            System.arraycopy(array, 0, newArray, 0, index);
        if (index + 1 < array.length)
            System.arraycopy(array, index + 1, newArray, index, array.length - 1 - index);

        return newArray;
    }

    private char[] removeFromArray(char[] array, int index) {

        char[] newArray = new char[array.length - 1];
        if (index - 1 >= 0)
            System.arraycopy(array, 0, newArray, 0, index);
        if (index + 1 < array.length)
            System.arraycopy(array, index + 1, newArray, index, array.length - 1 - index);

        return newArray;
    }

    private void doSQRTCalculation(StringBuilder sb) {
        doCalculation(sb);
        sb.append(inputField.getText());
        DecimalFormat decimalFormat = new DecimalFormat("#.####");
        String lastNumber = findNumber(sb, sb.length());
        inputField.setText(decimalFormat.format(Math.sqrt(Double.parseDouble(lastNumber))));
        sb.setLength(0);
    }

    private void doClearOperation() {
        inputField.setText("");
    }

    private void doDeleteOperation(StringBuilder sb) {
        sb.setLength(sb.length() - 1);
        inputField.setText(sb.toString());
    }

    private void doAddOperationSymbol(StringBuilder sb, String operation) {
        int lastSymbolIndex = sb.length() - 1;
        if (!checkStringIsNumber(sb, lastSymbolIndex)) {
            doDeleteOperation(sb);
        }
        inputField.setText(sb.toString() + operation);
    }

    private void doAddDotSymbol(StringBuilder sb, String operation) {
        String lastNumber = findNumber(sb, sb.length());
        if (!isInteger(lastNumber)) {
            return;
        }
        inputField.setText(sb.toString() + operation);
    }

    private String findNumber(StringBuilder sb, int endIndex) {
        int index = endIndex;
        do {
            index--;
        } while (index >= 0 && checkStringIsNumber(sb, index, endIndex) && sb.charAt(index) != '+' && sb.charAt(index) != '-');
        return sb.substring(index + 1, endIndex);
    }

    private boolean checkStringIsNumber(StringBuilder sb, int startIndex, int endIndex) {
        String str = sb.substring(startIndex, endIndex);
        return isDouble(str);
    }

    private boolean checkStringIsNumber(StringBuilder sb, int startIndex) {
        String str = sb.substring(startIndex);
        return isDouble(str);
    }

}
