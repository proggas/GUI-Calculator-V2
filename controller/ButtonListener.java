package controller;

import view.CalculatorScreen;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import model.Calculator;
import model.UnsignedNumber;

import javax.swing.JDialog;

public class ButtonListener implements ActionListener {
    
    private CalculatorScreen panel;

    public ButtonListener(CalculatorScreen panel) {
        this.panel = panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        var button = (JButton)e.getSource();
        Calculator calculator = panel.getCalculator();
        boolean operatorExists = calculator.isOperator();

        
        if(button == panel.getAddButton()) {
            boolean success;

            // This takes care of 2 cases
            // 1.) (A + B = C and then C + D = E) (Equals + Add)
            // 2.) (A * B + C) (Multiple Operations)
            if(operatorExists || calculator.isResultExists()) {
                if(operatorExists) {
                    success = doOperation();

                    if(!success) {
                        clear();
                        JOptionPane optionPane = new JOptionPane();
                        optionPane.setMessage("Error with computation.");
                        JDialog dialog = optionPane.createDialog(null, "Error");
                        dialog.setVisible(true);
                    }
                }

                UnsignedNumber result = calculator.getResult();
                calculator.setFirst(result);
                calculator.setSecond(new UnsignedNumber());
                calculator.setResult(new UnsignedNumber());

                //Change operator and make sure resultExists = FALSE
                calculator.setOperatorType('+'); 
                calculator.setOperator(true);  
                calculator.setResultExists(false);
                panel.getDisplay().setText(calculator.getFirst().getNumberText() + "\n" + '+');
                
                

            } else {    //If adding only 2 numbers (A + B), this code executes
                calculator.setOperatorType('+');
                calculator.setOperator(true);
                panel.getDisplay().setText(calculator.getFirst().getNumberText() + "\n" + '+');
            }

        } else if(button == panel.getSubButton()) {


        } else if(button == panel.getMulButton()) {


        } else if(button == panel.getDivButton()) {


        } else if(button == panel.getModButton()) {


        } else if(button == panel.getClearButton()) {
            clear();

        } else if(button == panel.getEqualButton()) {
            //Only do something if there is an operator, else keep the display as is
            if (operatorExists) {
                
                boolean success = doOperation();

                if(success) {
                    // Set operator to FALSE and result to TRUE
                    calculator.setOperator(false);
                    calculator.setResultExists(true);
                    panel.getDisplay().setText(calculator.getFirst().getNumberText() + "\n" + calculator.getOperatorType() +
                                                "\n" + calculator.getSecond().getNumberText() + "\n" + '=' + "\n" +
                                                calculator.getResult().getNumberText());
                } else {
                    clear();
                    JOptionPane optionPane = new JOptionPane();
                    optionPane.setMessage("Error with computation.");
                    JDialog dialog = optionPane.createDialog(null, "Error");
                    dialog.setVisible(true);
                }
                

            }

        } else {
            //Else is for digit buttons


            if(calculator.isResultExists()) {
                clear();
            }

            boolean success;

            //First Number
            if (!operatorExists) {
                UnsignedNumber first = calculator.getFirst();
                success = first.insert(button.getText().charAt(0) - '0');

                if(success) {
                    first.int_to_text();
                    panel.getDisplay().setText(first.getNumberText());
                } else {
                    JOptionPane optionPane = new JOptionPane();
                    optionPane.setMessage("Max unsigned integer limit of 30 achieved. Cannot make number any larger.");
                    JDialog dialog = optionPane.createDialog(null, "Error");
                    dialog.setVisible(true);
                }

                
            } else{ //Second Number
                UnsignedNumber second = calculator.getSecond();
                success = second.insert(button.getText().charAt(0) - '0');

                if(success) {
                    second.int_to_text();
                    panel.getDisplay().setText(calculator.getFirst().getNumberText() + "\n" + calculator.getOperatorType() +
                                                "\n" + second.getNumberText());
                } else {
                    JOptionPane optionPane = new JOptionPane();
                    optionPane.setMessage("Max unsigned integer limit of 30 achieved. Cannot make number any larger.");
                    JDialog dialog = optionPane.createDialog(null, "Error");
                    dialog.setVisible(true);
                }
            }
        }

        
    }

    public boolean doOperation() {
        char op = panel.getCalculator().getOperatorType();
        boolean success = false;
        switch(op) {
            case '+': {
                success = panel.getCalculator().add();
            }
            break;

            case '-': {


            }
            break;

            case '*': {


            }
            break;

            case '/': {


            }
            break;

            case '%': {


            }
            break;

            default: {
                System.out.println("Error");
            }
            break;
        }
        return success;
    }

    public void clear() {
        Calculator calculator = panel.getCalculator();
        calculator.setOperator(false);
        calculator.setFirst(new UnsignedNumber());
        calculator.setSecond(new UnsignedNumber());
        calculator.setResult(new UnsignedNumber());
        calculator.setResultExists(false);
        calculator.setOperator(false);
        panel.getDisplay().setText(calculator.getFirst().getNumberText());
    }


}
