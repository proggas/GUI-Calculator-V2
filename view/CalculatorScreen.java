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

    //Calculator visual components
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
    private JButton negButton;
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
        negButton = new JButton("+/-");
        equalButton = new JButton("=");
        clearButton = new JButton("Clear");
        digitButtons = new JButton[10];
    }

    //Builds calculator visual
    //Calculator has 2 main parts:
    //  - Display (located north)
    //  - Buttons (located south)
    public void init() {
        Container cp = window.getContentPane();

        //Set up display
        JPanel northPanel = new JPanel();
        northPanel.setPreferredSize(new Dimension(300, 100));
        northPanel.add(display);
        northPanel.setBackground(new java.awt.Color(26, 26, 26));

        display.setEditable(false);
        display.setPreferredSize(new Dimension(275, 85));
        display.setText(calculator.getFirst().getNumberText());
        display.setBackground(new java.awt.Color(240, 240, 240));
        cp.add(northPanel, BorderLayout.NORTH);

        //Set up buttons
        JPanel southPanel = new JPanel();
        cp.add(southPanel, BorderLayout.SOUTH);
        southPanel.setPreferredSize(new Dimension(300, 225));
        southPanel.setLayout(new GridLayout(5, 1));
        
        ButtonListener listener = new ButtonListener(this);

        //Make digit buttons, add listeners to each digit button, and change the color
        //of each digit button (doing it all at once prevents the use of multiple loops)
        for(int i = 0; i < 10; i++) {
            digitButtons[i] = new JButton("" + i);
            digitButtons[i].addActionListener(listener);
            digitButtons[i].setBackground(Color.LIGHT_GRAY);
            digitButtons[i].setPreferredSize(new Dimension(50, 30));
        }

        //Add the listener to the other buttons
        addButton.addActionListener(listener);
        subButton.addActionListener(listener);
        divButton.addActionListener(listener);
        mulButton.addActionListener(listener);
        modButton.addActionListener(listener);
        equalButton.addActionListener(listener);
        clearButton.addActionListener(listener);
        negButton.addActionListener(listener);

        //Add buttons manually to each row
        //Then add each row to the south panel
        JPanel row0 = new JPanel();
        row0.setBackground(new java.awt.Color(26, 26, 26));
        row0.add(digitButtons[1]);
        row0.add(digitButtons[2]);
        row0.add(digitButtons[3]);
        row0.add(addButton);
        southPanel.add(row0);

        JPanel row1 = new JPanel();
        row1.setBackground(new java.awt.Color(26, 26, 26));
        row1.add(digitButtons[4]);
        row1.add(digitButtons[5]);
        row1.add(digitButtons[6]);
        row1.add(subButton);
        southPanel.add(row1);

        JPanel row2 = new JPanel();
        row2.setBackground(new java.awt.Color(26, 26, 26));
        row2.add(digitButtons[7]);
        row2.add(digitButtons[8]);
        row2.add(digitButtons[9]);
        row2.add(mulButton);
        southPanel.add(row2);

        JPanel row3 = new JPanel();
        row3.setBackground(new java.awt.Color(26, 26, 26));
        row3.add(negButton);
        row3.add(digitButtons[0]);
        row3.add(modButton);
        row3.add(divButton);
        southPanel.add(row3);

        JPanel row4 = new JPanel();
        row4.setBackground(new java.awt.Color(26, 26, 26));
        row4.add(equalButton);
        row4.add(clearButton);
        southPanel.add(row4);

        //Change the rest of the button colors
        clearButton.setBackground(new java.awt.Color(191, 0, 0));
        clearButton.setForeground(Color.WHITE);
        addButton.setBackground(Color.GRAY);
        addButton.setForeground(Color.WHITE);
        subButton.setBackground(Color.GRAY);
        subButton.setForeground(Color.WHITE);
        mulButton.setBackground(Color.GRAY);
        mulButton.setForeground(Color.WHITE);
        divButton.setBackground(Color.GRAY);
        divButton.setForeground(Color.WHITE);
        modButton.setBackground(Color.GRAY);
        modButton.setForeground(Color.WHITE);
        negButton.setBackground(Color.GRAY);
        negButton.setForeground(Color.WHITE);
        equalButton.setBackground(new java.awt.Color(191, 0, 0));
        equalButton.setForeground(Color.WHITE);

        //Change rest of the button sizes
        clearButton.setPreferredSize(new Dimension(105, 25));
        equalButton.setPreferredSize(new Dimension(105, 25));
        addButton.setPreferredSize(new Dimension(50, 30));
        subButton.setPreferredSize(new Dimension(50, 30));
        mulButton.setPreferredSize(new Dimension(50, 30));
        divButton.setPreferredSize(new Dimension(50, 30));
        negButton.setPreferredSize(new Dimension(50, 30));
        modButton.setPreferredSize(new Dimension(50, 30));
    }

    //Getters
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

    public JButton getNegButton() {
        return negButton;
    }
}
