package com.mype.kata.ocrdigits;

import java.util.Collection;
import java.util.LinkedList;

/**
 * @author Vitaliy Gerya
 */
public class Row {
    private Collection<Digit> rowDigits = new LinkedList<>();

    public void addDigit(final Digit digit) {
        rowDigits.add(digit);
    }

    public long toNumber() {
        long number = 0;
        for (Digit digit : rowDigits) {
            number = number * 10 + digit.toNumber();
        }
        return number;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Digit rowDigit : rowDigits) {
            sb.append(rowDigit.toNumber());
        }

        return sb.toString();
    }

    public int length() {
        return rowDigits.size();
    }
}
