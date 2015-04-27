package com.company.state;

import com.company.machine.ReadWriteHead;
import com.company.machine.Tape;
import com.company.state.State;

/**
 * Created by brandt on 4/27/15.
 */
public class Transition {

    private int acceptCharacter;
    private int writeCharacter;
    private ReadWriteHead.Direction move;
    private State nextState;

    public Transition(int acceptCharacter, int writeCharacter, ReadWriteHead.Direction move, State nextState) {
        this.acceptCharacter = acceptCharacter;
        this.writeCharacter = writeCharacter;
        this.move = move;
        this.nextState = nextState;
    }

    public int getWriteCharacter() {
        return writeCharacter;
    }

    public State getNextState() {
        return nextState;
    }

    public ReadWriteHead.Direction getMove() {
        return move;
    }

    public int getAcceptCharacter() {
        return acceptCharacter;
    }

    public String toString() {
        return nextState + " moving " + ReadWriteHead.directionToString(move) + " on " + acceptCharacter + " writing " + writeCharacter;
    }
}
