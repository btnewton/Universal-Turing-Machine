package com.company.machine;

import com.company.util.TapeSection;

import java.util.HashSet;

/**
 * Tape simulates the Turing Machine Tape. This is also where the input language and blank symbol is defined.
 * The data structure holding the tape will expand as necessary to simulate infinite space. To simulate space in both
 * directions (from the starting point) there are two tapes each corresponding to one direction.  As far as all
 * accessors of the Tape (mainly the read-write head) are concerned, the tape is one continuous object.
 *
 * When setting the initial tape values to the input, the tape will validate all characters within the input to ensure
 * that they exist in the input alphabet.
 */
public class Tape {

    // Define tape alphabet (Binary)
    private HashSet<Character> alphabet;
    public static final char BLANK = '_';
    private TapeSection leftMostSection;
    private TapeSection tape;


    public static TapeSection fromArray(char[] values) {
        TapeSection tapeSection = null;
        for (char value : values) {
            tapeSection = new TapeSection(tapeSection, null, value);
        }
        return tapeSection;
    }

    public Tape(TapeSection input, char defaultCharacter) throws CharacterNotInAlphabetException {
        alphabet = new HashSet<Character>();
        alphabet.add('0');
        alphabet.add('1');

        if (validAlphabet(input)) {
            leftMostSection = getLeftMostSection(input);
            tape = leftMostSection;
            TapeSection.setDefaultValue(defaultCharacter);
        } else {
            throw new CharacterNotInAlphabetException("An invalid character was supplied as input");
        }
    }

    private TapeSection getLeftMostSection(TapeSection tape) {
        TapeSection tapeSection = tape;
        while (tapeSection.hasPrev()) {
            tapeSection = tapeSection.getPrevTapeSection();
        }
        return tapeSection;
    }

    /**
     * Validates input and sets it on the positiveTape if it is valid.  Sets BLANK as the default value to be returned for
     * all accessed cells that have not been set.
     *
     * @param input  initial tape values
     * @throws CharacterNotInAlphabetException  if any character in the given input is not included in defined ALPHABET
     */
    public Tape(TapeSection input) throws CharacterNotInAlphabetException {
        this(input, BLANK);
    }

    public void moveLeft() {
        if (tape.hasPrev()) {
            tape = tape.getPrevTapeSection();
        } else {
            tape = new TapeSection(null, tape);
            leftMostSection = tape;
        }
    }
    public void moveRight() {
        if (tape.hasNext()) {
            tape = tape.getNextTapeSection();
        } else {
            tape = new TapeSection(tape, null);
        }
    }

    public char get() {
        return tape.getValue();
    }

    /**
     * Sets the character at the position to newCharacter
     *
     * @param value character to write
     */
    public void set(char value) {
        tape.setValue(value);
    }

    /**
     * Returns the entire tape as a String
     *
     * @return the tape as a string
     */
    public String toString() {
        String tapeString = "...| ";

        TapeSection tapeSection = leftMostSection;

        while (tapeSection != null) {
            tapeString += tapeSection.getValue() + " | ";
            tapeSection = tapeSection.getNextTapeSection();
        }

        return tapeString.substring(0, tapeString.length() - 1) + "...";
    }

    /**
     * Used to check if contains only valid characters
     *
     * @param input input to check
     * @return      true if all characters are valid
     */
    private boolean validAlphabet(TapeSection input) {
        TapeSection currentSection = input;

        while(currentSection != null) {
            if (! isInAlphabet(currentSection.getValue())) {
                return false;
            }
            currentSection = currentSection.getNextTapeSection();
        }

        currentSection = input.getPrevTapeSection();

        while(currentSection != null) {
            if (! isInAlphabet(currentSection.getValue())) {
                return false;
            }
            currentSection = currentSection.getPrevTapeSection();
        }

        return true;
    }

    /**
     * Used to check if a character is in the tape alphabet.
     *
     * @param character character to test
     * @return          true if character is in alphabet
     */
    private boolean isInAlphabet(char character) {
        return alphabet.contains(character);
    }

    /**
     * Counts the occurrences of a character in the tape.
     * @param targetChar    character to be counted
     * @return              occurrences of character
     */
    public int countCharacter(char targetChar) {
        int counter = 0;
        TapeSection tapeSection = leftMostSection;

        while (tapeSection != null) {
            if (tapeSection.getValue() == targetChar)
                counter++;
            tapeSection = tapeSection.getNextTapeSection();
        }
        return counter;
    }
}
