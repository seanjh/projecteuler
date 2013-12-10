/*
* Author:     Sean Herman
* Date:       12/xx/2013
* 
* Power digit sum
* Problem 16 http://projecteuler.net/problem=16
* 215 = 32768 and the sum of its digits is 3 + 2 + 7 + 6 + 8 = 26.
* What is the sum of the digits of the number 21000?
*
*/

import java.util.LinkedList;


public class PowerDigitSum {
    public static void main(String[] args) {        
        MegaInt product = new MegaInt(1);
        for (int i = 1; i <= 1000; i++) {
            product.multiply(2);
        }

        System.out.println("2^1000 = " + product);

        String numString = product.toString();
        int sum = 0;
        for (char ch : numString.toCharArray()) {
            sum += Integer.parseInt("" + ch);
        }

        System.out.println("Sum of the digits of 2^15 = " + sum);
    }
}

class MegaInt {
    private LinkedList<Byte> value;

    public MegaInt() {
        this(0);
    }

    public MegaInt(int input) {
        value = new LinkedList<Byte>();
        String inString = "" + input;
        for (int i = 0; i < inString.length(); i++) {
            value.add(Byte.parseByte("" + inString.charAt(i)));
        }
    }

    public LinkedList<Byte> getValue() {
        return value;
    }

    public void multiply(int input) {
        int temp;
        int carry = 0;
        byte place;
        for (int i = value.size() - 1; i >= 0; i--) {
            temp = ((int)value.get(i) * input) + carry;
            place = (byte)(temp % 10);
            value.set(i, place);
            carry = (byte) temp / 10;
        }
        while (carry > 0) {
            place = (byte)(carry % 10);
            value.addFirst(place);
            carry /= 10;
        }
    }

    @Override 
    public String toString() {
        String out = "";
        for (byte place : value) {
            out += place;
        }
        return out;
    }
}