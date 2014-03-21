package com.mype.kata.ocrdigits;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author Vitaliy Gerya
 */
public class OCRReader {

    private final BufferedReader input;

    public OCRReader(InputStreamReader input) {
        this.input = new BufferedReader(input);
    }


    public Row read() throws IOException {
        String row1 = readLineAndCheck(),
                row2 = readLineAndCheck(),
                row3 = readLineAndCheck();
        readEmptyLineAndCheck();
        Row row = new Row();
        for (int i = 0, n = row1.length() / 3; i < n; i++) {
            Entry entry = Entry.createFromLines(row1.substring(3 * i, 3 * (i + 1)), row2.substring(3 * i, 3 * (i + 1)), row3.substring(3 * i, 3 * (i + 1)));
            row.addDigit(entry);
        }
        return row;
    }

    private void readEmptyLineAndCheck() throws IOException {
        String line = input.readLine();
        if (line == null) throw new IllegalStateException("This is should be empty line, but was: " + line);
        if (line.trim().length() != 0)
            throw new IllegalStateException("This is should be empty line and not contain any symbols, " +
                    "but was: " + line);
    }

    private String readLineAndCheck() throws IOException {
        String line = input.readLine();
        if (line == null) throw new IOException("Nothing to read;");
        if (line.length() != 27) throw new IOException("OCR line should be 27 characters length: '" + line + "'=" +
                line.length());
        return line;
    }
}
