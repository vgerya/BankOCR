package com.mype.kata.ocrdigits;

import java.util.Collection;
import java.util.LinkedList;

/**
 * @author Vitaliy Gerya
 */
public class Row {
    private Collection<Entry> rowDigits = new LinkedList<>();

    public void addDigit(final Entry entry) {
        rowDigits.add(entry);
    }

    public long toNumber() {
        long number = 0;
        for (Entry digit : rowDigits) {
            number = number * 10 + digit.toNumber();
        }
        return number;
    }
}
