package controller;

import view.CalculatorScreen;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JDialog;
import model.Calculator;
import model.Number;
import model.Calculator.Error_code;


public class ButtonListener implements ActionListener {
    
    private CalculatorScreen panel;

    public ButtonListener(CalculatorScreen panel) {
        this.panel = panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //Get source of button
        var button = (JButton)e.getSource();  
        
        //These variables that are used multiple times so it's more efficient to declare them here
        Calculator calculator = panel.getCalculator();  
        boolean operatorExists = calculator.isOperator();
        Error_code success = Error_code.SUCCESS;

        //Determine what type of button the source is
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
                    //If operation fails, clear screen and display error message
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
                    //If operation fails, clear screen and display error message
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
                    //If operation fails, clear screen and display error message
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
                    //If operation fails, clear screen and display error message
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
                    //If operation fails, clear screen and display error message
                    clear();
                    error(success);
                }

            } else {    //If using modulo on only 2 numbers (A % B), this code executes
                single_operation(calculator, '%');
            }

        } else if(button == panel.getClearButton()) {
            //Clears calculator screen
            clear();

        } else if (button == panel.getNegButton()){
            //If result exists, make the first number equal to result
            if(calculator.isResultExists()) {
                first_is_result(calculator);
            }

            //If operatorExists is false, the first number is being inserted
            if(!operatorExists) {
                Number first = calculator.getFirst();

                if(first.isZero() || first.isNeg()) {
                    first.setNeg(false);
                } else {
                    first.setNeg(true);
                }

                //Display the first number
                display_first(calculator);

            } else {
                Number second = calculator.getSecond();
                
                if(second.isZero() || second.isNeg()) {
                    second.setNeg(false);
                } else {
                    second.setNeg(true);
                }

                //Display the second number
                display_second(calculator);
            }

        } else if(button == panel.getEqualButton()) {
            //Only do something if there is an operator, else keep the display as is
            if (operatorExists) {
                
                success = doOperation(calculator);

                if(success == Error_code.SUCCESS) {
                    //Set 'operator' to FALSE and 'resultExists' to TRUE since an operation has been completed
                    //and the resulting number is placed into 'result'
                    calculator.setOperator(false);
                    calculator.setResultExists(true);

                    //Display first (operator) second = result
                    panel.getDisplay().setText(calculator.getFirst().getNumberText() + "\n" + calculator.getOperatorType() +
                                                "\n" + calculator.getSecond().getNumberText() + "\n" + "=" + "\n" +
                                                calculator.getResult().getNumberText());
                } else {
                    //If operation fails, clear screen and display error message
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
                Number first = calculator.getFirst();
                success = first.insert(button.getText().charAt(0) - '0');

                //If insertion successful, display number
                if(success == Error_code.SUCCESS) {
                    display_first(calculator);
                } else {
                    //Error occurs when max limit of 40 is reached (but it doesn't clear the calculator screen)
                    error(success);
                }

                
            } else{ //Second number being entered
                Number second = calculator.getSecond();
                success = second.insert(button.getText().charAt(0) - '0');

                //If insertion successful, display first (operator) second
                if(success == Error_code.SUCCESS) {
                    display_second(calculator);
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
        calculator.setFirst(new Number());
        calculator.setSecond(new Number());
        calculator.setResult(new Number());
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
            

            case SUCCESS: {
                msg = "Error_code = SUCCESS. Error in program.";
            }
            break;
        }

        //Displays the message to user
        JOptionPane optionPane = new JOptionPane();
        optionPane.setMessage(msg);
        JDialog dialog = optionPane.createDialog(null, "Error");
        dialog.setVisible(true);
    }

    //Method called when multiple operations are at work or if an operation is called
    //right after using equals button
    public void multiple_operations(Calculator calculator, char op) {
        first_is_result(calculator);

        //Change operator and display
        single_operation(calculator, op);
    }

    //Method called when an operation between 2 numbers is being done (A (operator) B = C)
    public void single_operation(Calculator calculator, char op) {
        calculator.setOperatorType(op);
        calculator.setOperator(true);
        panel.getDisplay().setText(calculator.getFirst().getNumberText() + "\n" + op);
    }

    //Method makes the first number equal the result while resetting the second and result
    public void first_is_result(Calculator calculator) {
        Number result = calculator.getResult();
        calculator.setFirst(result);
        calculator.setSecond(new Number());
        calculator.setResult(new Number());
        calculator.setResultExists(false);
    }

    //Displays first number
    public void display_first(Calculator calculator) {
        calculator.getFirst().int_to_text();
        panel.getDisplay().setText(calculator.getFirst().getNumberText());
    }

    //Displays first (operator) second
    public void display_second(Calculator calculator) {
        calculator.getSecond().int_to_text();
        panel.getDisplay().setText(calculator.getFirst().getNumberText() + "\n" + calculator.getOperatorType() +
                                    "\n" + calculator.getSecond().getNumberText());
    }


}
