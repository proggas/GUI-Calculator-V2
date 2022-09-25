package controller;

import view.CalculatorScreen;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import model.Calculator;
import model.UnsignedNumber;
import model.Calculator.Error_code;

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
        Error_code success = Error_code.SUCCESS;

        
        if(button == panel.getAddButton()) {

            // This takes care of 2 cases
            // 1.) (A (operator) B = C and then C + D) (Equals + Add)
            // 2.) (A (operator) B + C) (Multiple Operations)
            if(operatorExists || calculator.isResultExists()) {
                if(operatorExists) {
                    success = doOperation(calculator);
                }

                if(success == Error_code.SUCCESS) {
                    multiple_operations(calculator, '+');
                } else {
                    clear();
                    error(success);
                }

            } else {    //If adding only 2 numbers (A + B), this code executes
                single_operation(calculator, '+');
            }

        } else if(button == panel.getSubButton()) {

            // This takes care of 2 cases
            // 1.) (A (operator) B = C and then C - D) (Equals + Subtract)
            // 2.) (A (operator) B - C) (Multiple Operations)
            if(operatorExists || calculator.isResultExists()) {
                if(operatorExists) {
                    success = doOperation(calculator);
                }

                if(success == Error_code.SUCCESS) {
                    multiple_operations(calculator, '-');

                } else {
                    clear();
                    error(success);
                }

            } else {    //If subtracting only 2 numbers (A - B), this code executes
                single_operation(calculator, '-');
            }



        } else if(button == panel.getMulButton()) {

            // This takes care of 2 cases
            // 1.) (A (operator) B = C and then C * D) (Equals + Multiply)
            // 2.) (A (operator) B * C) (Multiple Operations)
            if(operatorExists || calculator.isResultExists()) {
                if(operatorExists) {
                    success = doOperation(calculator);
                }

                if(success == Error_code.SUCCESS) {
                    multiple_operations(calculator, '*');

                } else {
                    clear();
                    error(success);
                }

            } else {    //If multiplying only 2 numbers (A * B), this code executes
                single_operation(calculator, '*');
            }


        } else if(button == panel.getDivButton()) {
            
            // This takes care of 2 cases
            // 1.) (A (operator) B = C and then C / D) (Equals + Divide)
            // 2.) (A (operator) B / C) (Multiple Operations)
            if(operatorExists || calculator.isResultExists()) {
                if(operatorExists) {
                    success = doOperation(calculator);
                }

                if(success == Error_code.SUCCESS) {
                    multiple_operations(calculator, '/');

                } else {
                    clear();
                    error(success);
                }

            } else {    //If dividing only 2 numbers (A / B), this code executes
                single_operation(calculator, '/');
            }

        } else if(button == panel.getModButton()) {
            
            // This takes care of 2 cases
            // 1.) (A (operator) B = C and then C % D) (Equals + Modulo)
            // 2.) (A (operator) B % C) (Multiple Operations)
            if(operatorExists || calculator.isResultExists()) {
                if(operatorExists) {
                    success = doOperation(calculator);
                }

                if(success == Error_code.SUCCESS) {
                    multiple_operations(calculator, '%');

                } else {
                    clear();
                    error(success);
                }

            } else {    //If using modulo on only 2 numbers (A % B), this code executes
                single_operation(calculator, '%');
            }

        } else if(button == panel.getClearButton()) {
            clear();

        } else if(button == panel.getEqualButton()) {
            //Only do something if there is an operator, else keep the display as is
            if (operatorExists) {
                
                success = doOperation(calculator);

                if(success == Error_code.SUCCESS) {
                    // Set operator to FALSE and result to TRUE
                    calculator.setOperator(false);
                    calculator.setResultExists(true);
                    panel.getDisplay().setText(calculator.getFirst().getNumberText() + "\n" + calculator.getOperatorType() +
                                                "\n" + calculator.getSecond().getNumberText() + "\n" + "=" + "\n" +
                                                calculator.getResult().getNumberText());
                } else {
                    clear();
                    error(success);
                }
                

            }

        } else {
            //Else is for digit buttons

            //If a digit is pressed while there is a result, clear the calculator
            if(calculator.isResultExists()) {
                clear();
            }

            //First number being entered
            if (!operatorExists) {
                UnsignedNumber first = calculator.getFirst();
                success = first.insert(button.getText().charAt(0) - '0');

                //If insertion successful, display number
                if(success == Error_code.SUCCESS) {
                    first.int_to_text();
                    panel.getDisplay().setText(first.getNumberText());
                } else {
                    //Error occurs when max limit of 40 is reached (but it doesn't clear the calculator screen)
                    error(success);
                }

                
            } else{ //Second number being entered
                UnsignedNumber second = calculator.getSecond();
                success = second.insert(button.getText().charAt(0) - '0');

                //If insertion successful, display number
                if(success == Error_code.SUCCESS) {
                    second.int_to_text();
                    panel.getDisplay().setText(calculator.getFirst().getNumberText() + "\n" + calculator.getOperatorType() +
                                                "\n" + second.getNumberText());
                } else {
                    //Error occurs when max limit of 40 is reached (but it doesn't clear the calculator screen)
                    error(success);
                }
            }
        }

        
    }

    //Calls calculator operations depending on what the current operator type is
    public Error_code doOperation(Calculator calculator) {
        char op = calculator.getOperatorType();
        Error_code success = Error_code.SUCCESS;
        switch(op) {
            case '+': {
                success = calculator.add();
            }
            break;

            case '-': {
                success = calculator.subtract();

            }
            break;

            case '*': {
                success = calculator.multiply();

            }
            break;

            case '/': {
                success = calculator.divide();

            }
            break;

            case '%': {
                success = calculator.modulo();

            }
            break;

            default: {
                System.out.println("Error");
            }
            break;
        }
        return success;
    }

    //Resets calculator data
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

    //Displays different types of errors to the user
    public void error(Error_code code) {
        String msg = "";
        switch(code) {
            case INSERTION_FAIL: {
                msg = "Max unsigned integer limit of 40 achieved. Cannot make number any larger.";
            }
            break;

            case NUMBER_OVERFLOW: {
                msg = "Result is too large.";
            }
            break;

            case DIVIDE_BY_ZERO: {
                msg = "Cannot divide by 0.";
            }
            break;

            case LEFT_HAND_SMALLER: {
                msg = "Cannot have a negative result.";
            }
            break;

            case SUCCESS: {
                msg = "Error_code = SUCCESS. Error in program.";
            }
            break;
        }

        JOptionPane optionPane = new JOptionPane();
        optionPane.setMessage(msg);
        JDialog dialog = optionPane.createDialog(null, "Error");
        dialog.setVisible(true);
    }

    // Method called when multiple operations are at work or if an operation is called
    // right after using equals button
    public void multiple_operations(Calculator calculator, char op) {
        UnsignedNumber result = calculator.getResult();
        calculator.setFirst(result);
        calculator.setSecond(new UnsignedNumber());
        calculator.setResult(new UnsignedNumber());

        //Change operator and make sure resultExists = FALSE
        calculator.setOperatorType(op); 
        calculator.setOperator(true);  
        calculator.setResultExists(false);
        panel.getDisplay().setText(calculator.getFirst().getNumberText() + "\n" + op);
    }

    //Method called when an operation between 2 numbers is being done (A (operator) B = C)
    public void single_operation(Calculator calculator, char op) {
        calculator.setOperatorType(op);
        calculator.setOperator(true);
        panel.getDisplay().setText(calculator.getFirst().getNumberText() + "\n" + op);
    }


}
