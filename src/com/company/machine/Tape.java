package com.company.machine;

import java.util.ArrayList;

/**
 * Created by brandt on 4/27/15.
 */
public class Tape {


    private ArrayList<Integer> tape;

    public Tape(ArrayList<Integer> tape) {
        this.tape = tape;
    }

    public int get(int position) {
        if (position == tape.size()) {
            tape.add(ReadWriteHead.BLANK);
        }
        return tape.get(position);
    }

    public void set(int position, int newCharacter) {
        tape.set(position, newCharacter);
    }

    public String toString() {
        String tapeString = "";

        for (Integer value : tape) {
            tapeString += value + " ";
        }

        return tapeString.substring(0, tapeString.length() - 1);
    }
}
