package model;

import model.Calculator.Error_code;

public class UnsignedNumber {
    protected int[] number;
    protected int size;
    protected boolean isZero;
    protected String numberText;
    
    public UnsignedNumber() {
        number = new int[30];
        size = 1;
        isZero = true;
        numberText = "0";
    }

    public UnsignedNumber(int[] number) {
        this.number = number;
        computeSize();
        int_to_text();
    }

    public int[] getNumber() {
        return number;
    }

    public int getSize() {
        return size;
    }

    public boolean isZero() {
        return isZero;
    }

    public String getNumberText() {
        return numberText;
    }

    //Turns the array into a string
    public void int_to_text() {
        numberText = "";
        for(int i = 30 - size; i < 30; i++) {
            numberText += (char) (number[i] + '0');
        }
    }

    //Computes size of array without leading zeros
    public void computeSize() {
        int blank_space = 0;

        for (int i = 0; i < 30; i++)
        {
            if(number[i] != 0)
            {
                break;
            }
            else
            {
                blank_space++;
            }
        }

        if (blank_space != 30)
        {
            size = 30 - blank_space;
            isZero = false;
        }
        else
        {
            size = 1;
            isZero = true;
        }
    }

    //Insert number into array
    public Error_code insert(int num) {
        if(size == 30) {
            return Error_code.INSERTION_FAIL;
        }

        if(num == 0 && isZero) {
            return Error_code.SUCCESS;
        }

        if(isZero) {
            isZero = false;
            number[29] = num;
        } else {
            for(int i = 30 - size - 1; i < 29; i++) {
                number[i] = number[i + 1];
            }
            number[29] = num;
            size++;
        }
        

        return Error_code.SUCCESS;
    }
}
