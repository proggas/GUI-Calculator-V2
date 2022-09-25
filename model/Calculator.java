package model;

public class Calculator {

    //All operations return an Error_code that is used to determine whether the operation was successful or not
    //If an operation is not successful, the Error_code determines the error message the user will receive
    public enum Error_code {INSERTION_FAIL, DIVIDE_BY_ZERO, SUCCESS, NUMBER_OVERFLOW};

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
        Error_code success;

        //Check to see how many operands are negative
        //If both are positive or both are negative, add them together
        if((first.isNeg && second.isNeg) || (!first.isNeg && !second.isNeg)) {

            //If both operands are negative, result is negative
            if((first.isNeg && second.isNeg)) {
                result.isNeg = true;
            } else {
                result.isNeg = false;
            }

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
        } else {    //If one operand is negative, use the subtract method
            boolean first_is_neg = false;

            //Make sure the negative number is second and make the isNeg false
            if(first.isNeg) {
                first_is_neg = true;
                first.isNeg = false;

                Number temp = first;
                first = second;
                second = temp;

            } else {
                second.isNeg = false;
            }

            //Call subtract method
            //If the subtraction is somehow unsuccessful (which it shouldn't be), return the Error_code
            success = subtract();
            if(success != Error_code.SUCCESS) {
                return success;
            }
                
            //Put the isNeg back to true and swap back first and second if needed
            if(first_is_neg) {
                first.isNeg = true;

                Number temp = first;
                first = second;
                second = temp;
            } else {
                second.isNeg = true;
            }

        }
  
        result.computeSize();
        result.int_to_text();
        return Error_code.SUCCESS;

    } // End add

    // Method that subtracts first and second
    public Error_code subtract() {
        //Mapping out all of the cases

        //BOTH NEGATIVE
        //first_smaller = TRUE -> second - first = NONNEGATIVE RESULT (SWAPPING REQUIRED)
        //Ex) (-9 - -10) -> (-9 + 10) -> (10 - 9) -> 1

        //first_smaller = FALSE -> first - second = NEGATIVE RESULT (NO SWAPPING)
        //Ex) (-10 - -9) -> (-10 + 9) -> -1


        //FIRST POSITIVE, SECOND NEGATIVE
        //first_smaller = TRUE/FALSE && first_isNeg = FALSE && second_isNeg = TRUE -> first + second = NONNEGATIVE RESULT (NO SWAPPING)
        //CALL ADD METHOD
        //IMPORTANT: Need to change second isNeg to false and then change it back to true after addition
        //Ex1) (9 - -10) -> (9 + 10) -> 19
        //Ex2) (10 - -9) -> (10 + 9) -> 19


        //FIRST NEGATIVE, SECOND POSITIVE
        //first_smaller = TRUE/FALSE -> first + second = NEGATIVE RESULT (NO SWAPPING)
        //IMPORTANT: Need to change second isNeg to true and then change it back to false after addition
        //Ex1) (-9 - 10) -> (-9 + -10) -> -19
        //Ex2) (-10 - 9) -> (-10 + -9) -> -19

        //BOTH POSITIVE
        //first_smaller = TRUE ->   second - first = NEGATIVE RESULT (SWAPPING REQUIRED)
        //Ex) (9 - 10) -> -1
        
        //first_smaller = FALSE ->   first - second = NONNEGATIVE RESULT (NO SWAPPING)
        //Ex) (10 - 9) -> 1



        //First, check how many operands are negatives
        if((first.isNeg && second.isNeg) || (!first.isNeg && !second.isNeg)) {
            boolean first_smaller = false;

            //Since both have the same operand, it is important to check if the first operand 
            //is smaller than second
            for (int i = 0; i < 40; i++) {

                if(first.number[i] < second.number[i]) {
                    first_smaller = true;
                    break;

                } else if(first.number[i] > second.number[i]) {
                    break;
                }
                
            }

            //Deciding swapping and whether the result is negative or not:
            //All first_smaller = TRUE cases require a swap
            if(first_smaller && first.isNeg) {
                result.isNeg = false;

                Number temp = first;
                first = second;
                second = temp;
            } else if(!first_smaller && first.isNeg) {
                result.isNeg = true;

            } else if(first_smaller) {
                result.isNeg = true;

                Number temp = first;
                first = second;
                second = temp;
            } else {
                result.isNeg = false;
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

            //Swap back if necessary
            if(first_smaller) {
                Number temp = first;
                first = second;
                second = temp;
            }

        } else {    //Else case requires calling the add method
            //If first is negative, the result will be NEGATIVE 
            //If second is negative, the result will be POSITIVE

            if(first.isNeg) {
                //Change second isNeg to true and change it back after add is called
                second.isNeg = true;
            } else {
                second.isNeg = false;
            }

            //Add both operands
            //If there is overflow, return the Error_code
            Error_code success = add();
            if(success != Error_code.SUCCESS) {
                return success;
            }

            //Change back the changed isNeg boolean to the original value
            if(first.isNeg) {
                second.isNeg = false;
            } else {
                second.isNeg = true;
            }

        }


        result.computeSize();
        result.int_to_text();
        return Error_code.SUCCESS;

    } // End subtract

    // Method that multiplies first and second
    public Error_code multiply() {
        //First check how many operands are negative to determine whether
        //the result is negative or not

        //Both negative or positive = positive result, else result is negative
        result.isNeg = ((first.isNeg && second.isNeg) || (!first.isNeg && !second.isNeg)) ? false : true;
    

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

        //Next check how many operands are negative to determine whether
        //the result is negative or not

        //Both negative or positive = positive result, else result is negative
        result.isNeg = ((first.isNeg && second.isNeg) || (!first.isNeg && !second.isNeg)) ? false : true;

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

        //Before doing any arithmetic, check if the second operand is 0
        if(second.isZero) {
            result = new Number(first);
            return Error_code.SUCCESS;
        } 

        boolean swap = false;
        //Also check if the second operand is negative
        //If so, swap their signs
        if(second.isNeg && !first.isNeg) {
            swap = true;
            second.isNeg = false;
            first.isNeg = true;
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

        //5. Check if only one of the operands is negative
        if(first.isNeg && !second.isNeg) {
            
            //If only the first is negative, the result should be nonnegative so
            //keep adding the second operand to the result until it is nonnegative
            first_sec_temp = first;

            while(result.isNeg) {
                first = result;
                add();
            }

            first = first_sec_temp;
        } 

        //If second is negative, the result should be the same as it would be
        //if only first is negative except the result would be negative
        if(swap) {
            swap = true;
            second.isNeg = true;
            first.isNeg = false;
            result.isNeg = true;
        }

        result.computeSize();
        result.int_to_text();
        return Error_code.SUCCESS;
    }
    
}
