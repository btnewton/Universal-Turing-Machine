package com.company.machine;

/**
 * The ReadWriteHead or Tape Head is always positioned at one of the cells of the Tape object. The head is initialized
 * at the start of the Tape input.
 */
public class ReadWriteHead {

    // Directions head can move
    public static enum Direction {
        Left,
        Right
    }

    private Tape tape;
    private int position;

    /**
     * Accepts tape and initializes starting position of head.
     * @param tape  tape to interact with
     */
    public ReadWriteHead(Tape tape) {
        this.tape = tape;
        position = 0;
    }

    /**
     * Moves the position of the head to the left or right by 1 cell
     * @param move  direction to move
     */
    public void moveHead(Direction move) {
        if (move.equals(Direction.Left)) {
            position--;
        } else {
            position++;
        }
    }

    /**
     * Scans the current cell
     * @return  character found in cell
     */
    public char read() {
        return tape.get(position);
    }

    /**
     * Writes the new character in the current cell
     * @param newCharacter  character to write
     */
    public void write(char newCharacter) {
        tape.set(position, newCharacter);
    }

    /**
     * @param direction direction to translate
     * @return          String of the direction
     */
    public static String directionToString(Direction direction) {
        if (direction == Direction.Left) {
            return "left";
        } else {
            return "right";
        }
    }

    public Tape getTape() {
        return tape;
    }
}
