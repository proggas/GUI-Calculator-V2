package model;

public class Calculator {

    //All operations return an Error_code that is used to determine whether the operation was successful or not
    //If an operation is not successful, the Error_code determines the error message the user will receive
    public enum Error_code {INSERTION_FAIL, DIVIDE_BY_ZERO, LEFT_HAND_SMALLER, SUCCESS, NUMBER_OVERFLOW};

    private Number first;
    private Number second;
    private Number result;
    private boolean operator;
    private boolean resultExists;
    private char operatorType;

    public Calculator() {
        first = new Number();
        second = new Number();
        result = new Number();
        operator = false;
        resultExists = false;
        operatorType = '+';
    }

    //Getters
    public Number getFirst() {
        return first;
    }

    public char getOperatorType() {
        return operatorType;
    }

    public Number getResult() {
        return result;
    }

    public Number getSecond() {
        return second;
    }

    public boolean isOperator() {
        return operator;
    }

    public boolean isResultExists() {
        return resultExists;
    }

    //Setters
    public void setOperator(boolean operator) {
        this.operator = operator;
    }

    public void setOperatorType(char operatorType) {
        this.operatorType = operatorType;
    }

    public void setFirst(Number first) {
        this.first = first;
    }

    public void setResult(Number result) {
        this.result = result;
    }

    public void setSecond(Number second) {
        this.second = second;
    }

    public void setResultExists(boolean resultExists) {
        this.resultExists = resultExists;
    }

    // Method that adds first and second
    public Error_code add() {
  
        int currRemainder = 0, prevRemainder = 0;

        //Addition Algorithm
        for (int i = 39; i >= 0; i--) {
            currRemainder = (first.number[i] + second.number[i] + prevRemainder) / 10;
            result.number[i] = (first.number[i] + second.number[i] + prevRemainder) % 10;
            prevRemainder = currRemainder;
        }
            
        if (currRemainder >= 1) {
            return Error_code.INSERTION_FAIL;
        }

        result.computeSize();
        result.int_to_text();
        return Error_code.SUCCESS;

    } // End add

    // Method that subtracts first and second
    public Error_code subtract() {

        // First, check if the left hand side is smaller than right hand side
        for (int i = 0; i < 40; i++) {

            if(first.number[i] < second.number[i]) {

                return Error_code.LEFT_HAND_SMALLER;

            } else if(first.number[i] > second.number[i]) {

                break;
            }
            
        }

        int prevRemainder = 0, subt = 0;

        //Subtraction Algorithm
        for (int i = 39; i >= 0; i--) {
            subt = first.number[i] - prevRemainder - second.number[i];
            
            if (subt < 0) {
                result.number[i] = (first.number[i] + 10) - prevRemainder - second.number[i];
                prevRemainder = 1;
            }
            else {
                result.number[i] = subt;
                prevRemainder = 0;
            }
            

        }

        result.computeSize();
        result.int_to_text();
        return Error_code.SUCCESS;

    } // End subtract

    // Method that multiplies first and second
    public Error_code multiply() {
        int currRemainder = 0, prevRemainder = 0, currMultiple = 0;
        int k = 0;
        boolean right_larger = false, exceedLimit = false;

        // Make sure the larger number is the first number
        // Later, they'll be swapped back
        if(first.size >= second.size) {
            Number temp;
            temp = first;
            first = second;
            second = temp;
            right_larger = true;
        }

        for (int i = 39; i >= 0; i--) {
            k = i;
                
            for (int j = 39; j >= 0; j--) {
                if (k < 0) {
                    break;
                }

                    //Raw Multiplication
                    currMultiple = (first.number[j] * second.number[i]) + prevRemainder;

                    //Checks if result is too large
                    if((k == 0) && (result.number[k] + currMultiple > 9)) {
                        exceedLimit = true;
                    }

                    //Multiplication Algorithm
                    result.number[k] += currMultiple % 10;
                    currRemainder = (currMultiple / 10) + result.number[k] / 10;
                    result.number[k] %= 10;
                    prevRemainder = currRemainder;
                    k--;   
                    
            }
                
                
        }

        //Swap back first and second if needed
        if(right_larger) {
            Number temp;
            temp = first;
            first = second;
            second = temp;
        }

        if(exceedLimit) {
            return Error_code.NUMBER_OVERFLOW;
        }

        result.computeSize();
        result.int_to_text();
        return Error_code.SUCCESS;
    }

    //Method that divides first and second
    public Error_code divide() {

        //Check if divisor is 0
        if(second.isZero) {
            return Error_code.DIVIDE_BY_ZERO;
        }

        double divisor = 0;
        
        //Put divisor into a double variable
        for(int i = 39, j = 0; i >= 0; i--, j++) {
            divisor += (second.number[i] * Math.pow(10, j));
        }

        double dividend = 0, currRemainder = 0, prevRemainder = 0;

        //Division Algorithm
        for(int i = 40 - first.size; i < 40; i++) {
            dividend = ((prevRemainder * 10) + first.number[i]);
            result.number[i] = (int)(dividend / divisor); 
            currRemainder = (dividend - (result.number[i] * divisor));
            prevRemainder = currRemainder;
        }
    
        result.computeSize();
        result.int_to_text();
        return Error_code.SUCCESS;
        
    }

    public Error_code modulo() {

        if(second.isZero) {
            result = new Number(first);
            return Error_code.SUCCESS;
        }

        //1. first (dividend) / second (divisor) = result (quotient)
        //   Save quotient in the variable temp
        divide();
        Number temp = result;

        //2. quotient * second (divisor)
        //   This requires storing first in a temp variable and replacing it with quotient 
        //   Result also needs to be cleared
        Number first_sec_temp = first;
        first = temp;
        result = new Number();
        multiply();

        //3. dividend - (divisor * quotient)
        //   Change first back to its original value
        //   Replace temp with second
        //   Replace second with (divisor * quotient)
        //   Clear result
        //   Use subtract method
        first = first_sec_temp;
        first_sec_temp = second;
        second = result;
        result = new Number();
        subtract();

        //4. First and result are correct
        //   The only thing that needs to be changed back is second
        second = first_sec_temp;

        result.computeSize();
        result.int_to_text();
        return Error_code.SUCCESS;
    }
    
}
