package com.company.machine;

import java.util.ArrayList;

/**
 * This tape
 */
public class Tape {

    // Define tape alphabet
    private static final char[] ALPHABET = new char[]{'0', '1'};
    public static final char BLANK = '_';

    private ArrayList<Character> tape;

    /**
     * Validates input and sets it on the tape if it is valid
     *
     * @param input  new tape
     * @throws CharacterNotInAlphabetException  if any character in the given tape is not included in ALPHABET
     */
    public Tape(ArrayList<Character> input) throws CharacterNotInAlphabetException {
        if (validAlphabet(input)) {
            tape = input;
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
        if (position == tape.size()) {
            tape.add(Tape.BLANK);
        }
        return tape.get(position);
    }

    /**
     * Sets the character at the position to newCharacter
     *
     * @param position      position on tape to update
     * @param newCharacter  character to write
     */
    public void set(int position, char newCharacter) {
        tape.set(position, newCharacter);
    }

    /**
     * Returns the entire tape as a String
     *
     * @return the tape as a string
     */
    public String toString() {
        String tapeString = "| ";

        for (char character : tape) {
            tapeString += character + " | ";
        }

        return tapeString.substring(0, tapeString.length() - 1);
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
