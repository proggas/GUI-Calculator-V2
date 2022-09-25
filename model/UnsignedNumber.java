package model;

import model.Calculator.Error_code;

public class UnsignedNumber {
    protected int[] number;
    protected int size;
    protected boolean isZero;
    protected String numberText;
    
    public UnsignedNumber() {
        number = new int[40];
        size = 1;
        isZero = true;
        numberText = "0";
    }

    public UnsignedNumber(UnsignedNumber copy) {
        this.size = copy.size;
        this.isZero = copy.isZero;
        this.numberText = copy.numberText;
        this.number = new int[40];

        for(int i = 40 - this.size; i < 40; i++) {
            this.number[i] = copy.number[i];
        }
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
        for(int i = 40 - size; i < 40; i++) {
            numberText += (char) (number[i] + '0');
        }
    }

    //Computes size of array without leading zeros
    public void computeSize() {
        int blank_space = 0;

        for (int i = 0; i < 40; i++)
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

        if (blank_space != 40)
        {
            size = 40 - blank_space;
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
        if(size == 40) {
            return Error_code.INSERTION_FAIL;
        }

        if(num == 0 && isZero) {
            return Error_code.SUCCESS;
        }

        if(isZero) {
            isZero = false;
            number[39] = num;
        } else {
            for(int i = 40 - size - 1; i < 39; i++) {
                number[i] = number[i + 1];
            }
            number[39] = num;
            size++;
        }
        

        return Error_code.SUCCESS;
    }
}
