package model;

public class Calculator {

    public enum Error_code {INSERTION_FAIL, DIVIDE_BY_ZERO, LEFT_HAND_SMALLER, SUCCESS, NUMBER_OVERFLOW};

    private UnsignedNumber first;
    private UnsignedNumber second;
    private UnsignedNumber result;
    private boolean operator;
    private boolean resultExists;
    private char operatorType;

    public Calculator() {
        first = new UnsignedNumber();
        second = new UnsignedNumber();
        result = new UnsignedNumber();
        operator = false;
        resultExists = false;
        operatorType = '+';
    }

    public UnsignedNumber getFirst() {
        return first;
    }

    public char getOperatorType() {
        return operatorType;
    }

    public UnsignedNumber getResult() {
        return result;
    }

    public UnsignedNumber getSecond() {
        return second;
    }

    public boolean isOperator() {
        return operator;
    }

    public boolean isResultExists() {
        return resultExists;
    }

    public Error_code add() {
  
        int currRemainder = 0, prevRemainder = 0;

        for (int i = 29; i >= 0; i--) {

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

    }

    public void setOperator(boolean operator) {
        this.operator = operator;
    }

    public void setOperatorType(char operatorType) {
        this.operatorType = operatorType;
    }

    public void setFirst(UnsignedNumber first) {
        this.first = first;
    }

    public void setResult(UnsignedNumber result) {
        this.result = result;
    }

    public void setSecond(UnsignedNumber second) {
        this.second = second;
    }

    public void setResultExists(boolean resultExists) {
        this.resultExists = resultExists;
    }
    
}
