package view;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import controller.ButtonListener;
import model.Calculator;

public class CalculatorScreen {

    private JFrame window;
    private Calculator calculator;
    private JTextArea display;
    private JButton addButton;
    private JButton subButton;
    private JButton mulButton;
    private JButton divButton;
    private JButton modButton;
    private JButton equalButton;
    private JButton clearButton;
    private JButton[] digitButtons;

    public CalculatorScreen(JFrame window) {
        this.window = window;
        calculator = new Calculator();
        display = new JTextArea();
        addButton = new JButton("+");
        subButton = new JButton("-");
        mulButton = new JButton("x");
        divButton = new JButton("/");
        modButton = new JButton("%");
        equalButton = new JButton("=");
        clearButton = new JButton("Clear");
        digitButtons = new JButton[10];
    }

    public void init() {
        Container cp = window.getContentPane();

        //Sets up display
        JPanel northPanel = new JPanel();
        northPanel.setPreferredSize(new Dimension(300, 100));
        northPanel.add(display);
        northPanel.setBackground(Color.BLACK);
        display.setEditable(false);
        display.setPreferredSize(new Dimension(275, 85));
        display.setText(calculator.getFirst().getNumberText());
        cp.add(northPanel, BorderLayout.NORTH);

        //Sets up buttons
        JPanel southPanel = new JPanel();
        cp.add(southPanel, BorderLayout.SOUTH);
        southPanel.setPreferredSize(new Dimension(300, 185));
        southPanel.setBackground(Color.DARK_GRAY);
        southPanel.setLayout(new GridLayout(5, 1));

        ButtonListener listener = new ButtonListener(this);
        for(int i = 0; i < 10; i++) {
            digitButtons[i] = new JButton("" + i);
            digitButtons[i].addActionListener(listener);
        }

        //Add Listeners to Buttons
        addButton.addActionListener(listener);
        subButton.addActionListener(listener);
        divButton.addActionListener(listener);
        mulButton.addActionListener(listener);
        modButton.addActionListener(listener);
        equalButton.addActionListener(listener);
        clearButton.addActionListener(listener);

        //Add buttons manually to each row
        //Then add each row to the south panel
        JPanel row0 = new JPanel();
        row0.setBackground(Color.DARK_GRAY);
        row0.add(digitButtons[1]);
        row0.add(digitButtons[2]);
        row0.add(digitButtons[3]);
        row0.add(addButton);
        southPanel.add(row0);

        JPanel row1 = new JPanel();
        row1.setBackground(Color.DARK_GRAY);
        row1.add(digitButtons[4]);
        row1.add(digitButtons[5]);
        row1.add(digitButtons[6]);
        row1.add(subButton);
        southPanel.add(row1);

        JPanel row2 = new JPanel();
        row2.setBackground(Color.DARK_GRAY);
        row2.add(digitButtons[7]);
        row2.add(digitButtons[8]);
        row2.add(digitButtons[9]);
        row2.add(mulButton);
        southPanel.add(row2);

        JPanel row3 = new JPanel();
        row3.setBackground(Color.DARK_GRAY);
        row3.add(equalButton);
        row3.add(digitButtons[0]);
        row3.add(modButton);
        row3.add(divButton);
        southPanel.add(row3);

        JPanel row4 = new JPanel();
        row4.setBackground(Color.DARK_GRAY);
        row4.add(clearButton);
        southPanel.add(row4);

    }

    public JButton[] getDigitButtons() {
        return digitButtons;
    }

    public JButton getAddButton() {
        return addButton;
    }

    public Calculator getCalculator() {
        return calculator;
    }

    public JButton getClearButton() {
        return clearButton;
    }

    public JTextArea getDisplay() {
        return display;
    }

    public JButton getDivButton() {
        return divButton;
    }

    public JButton getEqualButton() {
        return equalButton;
    }

    public JButton getModButton() {
        return modButton;
    }

    public JButton getMulButton() {
        return mulButton;
    }

    public JButton getSubButton() {
        return subButton;
    }

    public JFrame getWindow() {
        return window;
    }
}
