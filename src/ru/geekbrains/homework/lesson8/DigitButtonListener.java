package ru.geekbrains.homework.lesson8;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DigitButtonListener implements ActionListener {
    private final JTextField inputField;
    private final StringBuilder sb;

    public DigitButtonListener(JTextField inputField) {
        this.inputField = inputField;
        sb = new StringBuilder();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        sb.append(inputField.getText());
        sb.append(((JButton) e.getSource()).getText());

        inputField.setText(sb.toString());
        sb.setLength(0);
    }
}
