package com.mype.kata.ocrdigits;

import static com.mype.kata.ocrdigits.Character.*;

/**
 * @author Vitaliy Gerya
 */
public enum Digit {

    //    "   _  _     _  _  _  _  _  _\n" +
    //    " | _| _||_||_ |_   ||_||_|| |\n" +
    //    " ||_  _|  | _||_|  ||_| _||_|";
    ZERO(SPACE, UNDERSCORE, SPACE, PIPE, SPACE, PIPE, PIPE, UNDERSCORE, PIPE),
    ONE(SPACE, SPACE, SPACE, SPACE, SPACE, PIPE, SPACE, SPACE, PIPE),
    TWO(SPACE, UNDERSCORE, SPACE, SPACE, UNDERSCORE, PIPE, PIPE, UNDERSCORE, SPACE),
    THREE(SPACE, UNDERSCORE, SPACE, SPACE, UNDERSCORE, PIPE, SPACE, UNDERSCORE, PIPE),
    FOUR(SPACE, SPACE, SPACE, PIPE, UNDERSCORE, PIPE, SPACE, SPACE, PIPE),
    FIVE(SPACE, UNDERSCORE, SPACE, PIPE, UNDERSCORE, SPACE, SPACE, UNDERSCORE, PIPE),
    SIX(SPACE, UNDERSCORE, SPACE, PIPE, UNDERSCORE, SPACE, PIPE, UNDERSCORE, PIPE),
    SEVEN(SPACE, UNDERSCORE, SPACE, SPACE, SPACE, PIPE, SPACE, SPACE, PIPE),
    EIGHT(SPACE, UNDERSCORE, SPACE, PIPE, UNDERSCORE, PIPE, PIPE, UNDERSCORE, PIPE),
    NINE(SPACE, UNDERSCORE, SPACE, PIPE, UNDERSCORE, PIPE, SPACE, UNDERSCORE, PIPE),;


    private String representation;

    Digit(Character... representation) {
        StringBuilder sb = new StringBuilder();
        for (Character character : representation) {
            sb.append(character.getSymbol());
        }
        this.representation = sb.toString();
    }

    public long toNumber() {
        return ordinal();
    }

    public static Digit createFromLines(final String row1, final String row2, final String row3) {
        boolean lengthCompatible = row1.length() == 3 && row2.length() == 3 && row3.length() == 3;
        if(!lengthCompatible)
            throw new IllegalArgumentException("Parts should be 3 char length. But was:\n" + row1 + "\n" + row2 +
                    "\n" + row3);

        for(Digit digit : values()) {

            boolean matches = digit.representation.startsWith(row1) && digit.representation.substring(3, 6).equals(row2) && digit.representation.endsWith(row3);
            if(matches)
                return digit;
        }

        throw new IllegalStateException("Not found acceptable entry for: \n'" + row1 + "'\n'" + row2 + "'\n'" + row3
                + "'");
    }
}

enum Character {
    UNDERSCORE('_'),
    SPACE(' '),
    PIPE('|');

    private final char symbol;

    Character(final char symbol) {
        this.symbol = symbol;
    }

    public char getSymbol(){
        return this.symbol;
    }
}
