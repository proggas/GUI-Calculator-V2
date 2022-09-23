package controller;

import view.CalculatorScreen;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

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
        boolean operatorExists = panel.getCalculator().isOperator();
        //panel.getDisplay().setText(button.getText());

        if(button == panel.getAddButton()) {
            

        } else if(button == panel.getSubButton()) {


        } else if(button == panel.getMulButton()) {


        } else if(button == panel.getDivButton()) {


        } else if(button == panel.getModButton()) {


        } else if(button == panel.getClearButton()) {


        } else if(button == panel.getEqualButton()) {
            if (operatorExists) {
                char op = panel.getCalculator().getOperatorType();

                switch(op) {
                    case '+': {

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
                }

            } else {


            }

        } else {
            //Else is for digit buttons

            boolean success;
            //First Number
            if (!operatorExists) {
                UnsignedNumber first = panel.getCalculator().getFirst();
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
                UnsignedNumber second = panel.getCalculator().getSecond();
                success = second.insert(button.getText().charAt(0) - '0');

                if(success) {
                    
                }
            }
        }

        
    }


}
