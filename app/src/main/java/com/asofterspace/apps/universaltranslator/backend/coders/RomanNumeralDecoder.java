package com.asofterspace.apps.universaltranslator.backend.coders;

import java.util.ArrayList;
import java.util.List;

/**
 * A class that can decode numbers from Roman numerals
 *
 * @author Moya (a softer space, 2017)
 */
public class RomanNumeralDecoder {

    /**
     * Decodes Roman numerals back into regular numbers
     *
     * @param numerals  A string full of Roman numerals
     * @return A string of numbers corresponding to the input
     */
    public String decodeRomanNumeralsIntoNumbers(String numerals) {

        numerals = numerals.toUpperCase();

        // assume XCIIIX as input; we want 97 as output
        // first of all, we get from X,C,I,I,I,X the numbers 10,100,1,1,1,10
        // and add like ones, so 10,100,1+1+1,10 = 10,100,3,10

        List<Integer> numbers = new ArrayList<>();
        int lastNumber = 0;
        int currentNumber;
        int carryNumber = 0;

        for (int i = 0; i < numerals.length(); i++) {

            switch (numerals.charAt(i)) {
                case 'I':
                    currentNumber = 1;
                    break;
                case 'V':
                    currentNumber = 5;
                    break;
                case 'X':
                    currentNumber = 10;
                    break;
                case 'L':
                    currentNumber = 50;
                    break;
                case 'C':
                    currentNumber = 100;
                    break;
                case 'D':
                    currentNumber = 500;
                    break;
                case 'M':
                    currentNumber = 1000;
                    break;
                default:
                    currentNumber = 0;
            }

            if (currentNumber == lastNumber) {
                carryNumber += currentNumber;
            } else {
                numbers.add(carryNumber);
                carryNumber = currentNumber;
            }

            lastNumber = currentNumber;
        }

        numbers.add(carryNumber);

        // we now subtract all numbers that need to be subtracted locally, so from 10,100,3,10
        // we want to get 100-10,0,10-3,0 = 90,0,7,0

        for (int i = numbers.size() - 1; i > 0; i--) {
            if (numbers.get(i-1) < numbers.get(i)) {
                numbers.set(i-1, numbers.get(i) - numbers.get(i-1));
                numbers.set(i, 0);
            }
        }

        // we now iterate over this mess once again, this time just adding all the numbers that we
        // find, so from 90,0,7,0 we get 90+0+7+0 = 90+7 = 97

        if (numbers.size() < 1) {
            return "0";
        }

        int result = 0;

        for (int i : numbers) {
            result += i;
        }

        return ""+result;
    }
}
