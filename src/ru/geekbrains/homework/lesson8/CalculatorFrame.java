package ru.geekbrains.homework.lesson8;

import javax.swing.*;
import java.awt.*;

public class CalculatorFrame extends JFrame {
    public CalculatorFrame() {
        setTitle("Calculator v1.0");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(100, 100, 300, 500);

        setLayout(new BorderLayout());

        TopFrame top = new TopFrame();
        add(top.getPanel(), BorderLayout.NORTH);

        BottomFrame bottom = new BottomFrame(top.getInputField());
        add(bottom.getPanel(), BorderLayout.CENTER);

        setVisible(true);
    }
}
