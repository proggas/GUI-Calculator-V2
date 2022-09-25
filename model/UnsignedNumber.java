package model;

import model.Calculator.Error_code;

public class UnsignedNumber {
    //The variables are protected so that the Calculator class can efficiently access
    //them without using getters or having other classes access them
    protected int[] number;
    protected int size;
    protected boolean isZero;
    protected String numberText;
    final int MAX_LENGTH = 40;
    
    //Default Constructor
    public UnsignedNumber() {
        number = new int[MAX_LENGTH];
        size = 1;
        isZero = true;
        numberText = "0";
    }

    //Copy Constructor
    public UnsignedNumber(UnsignedNumber copy) {
        this.size = copy.size;
        this.isZero = copy.isZero;
        this.numberText = copy.numberText;
        this.number = new int[MAX_LENGTH];

        for(int i = MAX_LENGTH - this.size; i < MAX_LENGTH; i++) {
            this.number[i] = copy.number[i];
        }
    }

    //Getters
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
        for(int i = MAX_LENGTH - size; i < MAX_LENGTH; i++) {
            numberText += (char) (number[i] + '0');
        }
    }

    //Computes size of array without leading zeros
    //Also determines if number is 0 or not
    public void computeSize() {
        int white_space = 0;

        for (int i = 0; i < MAX_LENGTH; i++) {
            if(number[i] != 0) {
                break;
            }
            else {
                white_space++;
            }
        }

        //If white_space isn't equal to MAX_LENGTH, the number can't be 0
        if (white_space != MAX_LENGTH) {
            size = MAX_LENGTH - white_space;
            isZero = false;
        }
        else {
            //If white_space is equal to MAX_LENGTH, the number has to be 0
            size = 1;
            isZero = true;
        }
    }

    //Inserts digits into array
    public Error_code insert(int num) {

        //Can't make the number any larger if the size is MAX_LENGTH
        if(size == MAX_LENGTH) {
            return Error_code.INSERTION_FAIL;
        }

        //This prevents 0's from being entered into the array and the size
        //increasing if the number is currently 0
        if(num == 0 && isZero) {
            return Error_code.SUCCESS;
        }

        //The first digit inserted should make the 'isZero' false and not increase the
        //size as the size is already 1
        if(isZero) {
            isZero = false;
            number[MAX_LENGTH - 1] = num;
        } else {
            //If the digit inserted is not the first digit, shift the entire array
            //and insert the digit at the very end
            for(int i = MAX_LENGTH - size - 1; i < MAX_LENGTH - 1; i++) {
                number[i] = number[i + 1];
            }
            number[MAX_LENGTH - 1] = num;
            size++;
        }
        
        return Error_code.SUCCESS;
    }
}
