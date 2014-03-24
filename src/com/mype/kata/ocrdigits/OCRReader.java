package com.mype.kata.ocrdigits;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author Vitaliy Gerya
 */
public class OCRReader {

    private static final int OCR_LINE_LENGTH = 27;
    private static final int OCR_DIGIT_LENGTH = 3;
    private final BufferedReader input;
    private String[] rows = new String[3];

    public OCRReader(InputStreamReader input) {
        this.input = new BufferedReader(input);
    }


    public Row read() throws IOException {
        rows[0] = readLine();

        // end of read
        if (rows[0] == null) return null;
        validateLength(rows[0]);

        rows[1] = readLine();
        validateNotNull(rows[1]);
        validateLength(rows[1]);
        rows[2] = readLine();
        validateNotNull(rows[2]);
        validateLength(rows[2]);
        String emptyString = readLine();
        validateNotNull(emptyString);
        validateIsEmpty(emptyString);

        Row row = new Row();
        for (int i = 0, n = OCR_LINE_LENGTH / OCR_DIGIT_LENGTH; i < n; i++) {
            Digit digit = Digit.createFromLines(rows[0].substring(OCR_DIGIT_LENGTH * i, OCR_DIGIT_LENGTH * (i + 1)), rows[1].substring(OCR_DIGIT_LENGTH * i, OCR_DIGIT_LENGTH * (i + 1)), rows[2].substring(OCR_DIGIT_LENGTH * i, OCR_DIGIT_LENGTH * (i + 1)));
            row.addDigit(digit);
        }
        return row;
    }

    private void validateIsEmpty(final String emptyString) throws IOException {
        if (emptyString.trim().length() != 0)
            throw new IOException("This is should be empty line and not contain any symbols, " +
                    "but was: '" + emptyString + "'.");
    }

    private void validateLength(final String row) throws IOException {
        if (row.length() != OCR_LINE_LENGTH) throw new IOException("OCR line should be " + OCR_LINE_LENGTH +
                " characters length: '" +
                row +
                "'=" +
                row.length());
    }

    private void validateNotNull(final String row) {
        if (row == null) throw new IllegalStateException("Unexpectedly row is null.");
    }

    private String readLine() throws IOException {
        String line = input.readLine();
        if (line == null) return null;

        return line;
    }
}
