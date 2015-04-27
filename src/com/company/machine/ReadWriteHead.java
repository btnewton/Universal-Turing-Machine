package com.company.machine;

/**
 * Created by brandt on 4/27/15.
 */
public class ReadWriteHead {

    public static enum Direction {
        Left,
        Right
    }

    private Tape tape;
    private int position;

    public ReadWriteHead(Tape tape) {
        this.tape = tape;
    }

    // TODO throw out of bounds exceptions?
    public void moveHead(Direction move) {
        if (move.equals(Direction.Left)) {
            position--;
        } else {
            position++;
        }
    }

    public int read() {
        return tape.get(position);
    }

    public void write(int newCharacter) {
        tape.set(position, newCharacter);
    }

    public static String directionToString(Direction direction) {
        if (direction == Direction.Left) {
            return "left";
        } else {
            return "right";
        }
    }
}
