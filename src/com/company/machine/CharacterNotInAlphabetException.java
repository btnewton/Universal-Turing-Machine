package com.company.machine;

/**
 * Created by brandt on 4/28/15.
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