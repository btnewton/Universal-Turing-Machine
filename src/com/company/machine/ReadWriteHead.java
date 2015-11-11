package com.company.machine;

/**
 * The ReadWriteHead or Tape Head is always positioned at one of the cells of the Tape object. The head is initialized
 * at the start of the Tape input.
 */
public class ReadWriteHead {

    // Directions head can move
    public enum Direction {
        Left,
        Right
    }

    private Tape tape;

    /**
     * Accepts tape and initializes starting position of head.
     * @param tape  tape to interact with
     */
    public ReadWriteHead(Tape tape) {
        this.tape = tape;
    }

    /**
     * Moves the position of the head to the left or right by 1 cell
     * @param move  direction to move
     */
    public void moveHead(Direction move) {
        if (move.equals(Direction.Left)) {
            tape.moveLeft();
        } else {
            tape.moveRight();
        }
    }

    /**
     * Scans the current cell
     * @return  character found in cell
     */
    public char read() {
        return tape.get();
    }

    /**
     * Writes the new character in the current cell
     * @param newCharacter  character to write
     */
    public void write(char newCharacter) {
        tape.set(newCharacter);
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
