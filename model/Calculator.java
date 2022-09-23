package model;

public class Calculator {
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

    boolean add() {
        boolean exceedLimit = false;
  
        int[] temp = new int[31];
        
        for (int i = 29; i >= 0; i--)
        {
            if(i == 0 && ((first.number[i] + second.number[i] + temp[i + 1]) > 9))
            {
                exceedLimit = true;
            }

            if (first.number[i] + second.number[i] + temp[i + 1] > 9)
            {
                temp[i] = (first.number[i] + second.number[i] + temp[i + 1]) / 10;
                result.number[i] = (first.number[i] + second.number[i] + temp[i + 1]) % 10;
            }
            else
            {
                result.number[i] = first.number[i] + second.number[i] + temp[i + 1];
            }

        }
            
        if (exceedLimit)
        {
            return false;
        }

        result.computeSize();
        return true;

    }
    
}
