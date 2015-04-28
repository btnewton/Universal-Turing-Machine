package com.company.machine;

/**
 * This exception is to be thrown when the an invalid input character is found on the tape
 */
public class CharacterNotInAlphabetException extends Exception {

    private String message = null;

    public CharacterNotInAlphabetException() {
        super();
    }

    public CharacterNotInAlphabetException(String message) {
        super(message);
        this.message = message;
    }

    public CharacterNotInAlphabetException(Throwable cause) {
        super(cause);
    }

    @Override
    public String toString() {
        return message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}