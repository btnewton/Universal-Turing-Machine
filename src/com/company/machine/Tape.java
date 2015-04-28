package com.company.machine;

import java.util.ArrayList;

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
    private static final char[] ALPHABET = new char[]{'0', '1'};
    public static final char BLANK = '_';

    // Positive tape is used to simulate tape in the positive direction
    private ArrayList<Character> positiveTape;

    // Negative tape is used to simulate tape in the negative direction
    private ArrayList<Character> negativeTape;

    /**
     * Validates input and sets it on the positiveTape if it is valid
     *
     * @param input  initial tape values
     * @throws CharacterNotInAlphabetException  if any character in the given input is not included in defined ALPHABET
     */
    public Tape(ArrayList<Character> input) throws CharacterNotInAlphabetException {
        if (validAlphabet(input)) {
            positiveTape = input;

            // Set up negative positiveTape
            negativeTape = new ArrayList<Character>();
        } else {
            throw new CharacterNotInAlphabetException("An invalid character was supplied as input");
        }
    }

    /**
     * Gets the character at the given position
     *
     * Note: If the position is outside of the ArrayList a blank is returned
     *
     * @param position  position to return
     * @return          character at given position
     */
    public char get(int position) {
        if (position == positiveTape.size()) {
            positiveTape.add(BLANK);
        }

        if (position < 0) {
            // Flip position sign
            position *= -1;
            if (position == negativeTape.size()) {
                negativeTape.add(BLANK);
            }

            return negativeTape.get(position);
        } else {
            return positiveTape.get(position);
        }
    }

    /**
     * Sets the character at the position to newCharacter
     *
     * @param position      position on positiveTape to update
     * @param newCharacter  character to write
     */
    public void set(int position, char newCharacter) {
        positiveTape.set(position, newCharacter);
    }

    /**
     * Returns the entire tape as a String
     *
     * @return the tape as a string
     */
    public String toString() {
        String tapeString = "...| ";

        if (negativeTape.size() > 0) {
            for (char character : negativeTape) {
                tapeString += character + " | ";
            }
            tapeString = tapeString.substring(0, tapeString.length() - 1);
        }

        for (char character : positiveTape) {
            tapeString += character + " | ";
        }

        return tapeString.substring(0, tapeString.length() - 1) + "...";
    }

    /**
     * Used to check if contains only valid characters
     *
     * @param input input to check
     * @return      true if all characters are valid
     */
    private boolean validAlphabet(ArrayList<Character> input) {
        for (char character : input) {
            if ( ! isInAlphabet(character)) {
                return false;
            }
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
        for (char validCharacter : ALPHABET) {
            if (character == validCharacter) {
                return true;
            }
        }
        return false;
    }
}
