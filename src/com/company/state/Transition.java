package com.company.state;

import com.company.machine.ReadWriteHead;
import com.company.machine.Tape;
import com.company.state.State;

/**
 * Defines a transition function in a Turing Machine
 */
public class Transition {

    private char acceptCharacter;
    private char writeCharacter;
    private ReadWriteHead.Direction move;
    private State nextState;

    /**
     * Creates a new Transition function
     * @param acceptCharacter   character function accepts
     * @param writeCharacter    character function writes
     * @param move              direction function moves
     * @param nextState         state function transitions to
     */
    public Transition(char acceptCharacter, char writeCharacter, ReadWriteHead.Direction move, State nextState) {
        this.acceptCharacter = acceptCharacter;
        this.writeCharacter = writeCharacter;
        this.move = move;
        this.nextState = nextState;
    }

    public char getWriteCharacter() {
        return writeCharacter;
    }

    public State getNextState() {
        return nextState;
    }

    public ReadWriteHead.Direction getMove() {
        return move;
    }

    public char getAcceptCharacter() {
        return acceptCharacter;
    }

    public String toString() {
        return nextState + " moving " + ReadWriteHead.directionToString(move) + " on " + acceptCharacter + " writing " + writeCharacter;
    }
}
