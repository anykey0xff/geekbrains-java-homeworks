package ru.geekbrains.homework.lesson8;

import javax.swing.*;
import java.awt.*;

public class BottomFrame {
    private final JPanel panel;

    public BottomFrame(JTextField inputField) {
        panel = new JPanel();
        panel.setLayout(new GridLayout(5, 4));

        DigitButtonListener digitButtonListener = new DigitButtonListener(inputField);
        OperationButtonListener operationButtonListener = new OperationButtonListener(inputField);

        String[] buttons =
                {"C", "DEL", "SQRT", "/", "7", "8", "9", "*", "4", "5", "6", "-", "1", "2", "3", "+", "ScriptEngine =", "0", ".", "="};

        for (String button : buttons) {
            JButton btn = new JButton(button);
            boolean isDigit = button.matches("[-+]?\\d+");
            if (isDigit)
                btn.addActionListener(digitButtonListener);
            else
                btn.addActionListener(operationButtonListener);
            panel.add(btn);
        }

    }

    public JPanel getPanel() {
        return panel;
    }
}
