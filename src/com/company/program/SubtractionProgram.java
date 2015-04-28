package com.company.program;

import com.company.machine.ReadWriteHead;
import com.company.state.State;
import com.company.state.Transition;

import java.util.ArrayList;

/**
 * Created by brandt on 4/27/15.
 */
public class SubtractionProgram extends Program {

    public SubtractionProgram() {
        State q0 = new State(0, this);
        State q1 = new State(1, this);
        State q2 = new State(2, this);
        State q3 = new State(3, this);
        State q4 = new State(4, this);
        State q5 = new State(5, this);
        State q6 = new State(6, this);

        // q0
        q0.addTransition(new Transition(0, ReadWriteHead.BLANK, ReadWriteHead.Direction.Right, q1));
        q0.addTransition(new Transition(1, ReadWriteHead.BLANK, ReadWriteHead.Direction.Right, q5));

        // q1
        q1.addTransition(new Transition(0, 0, ReadWriteHead.Direction.Right, q1));
        q1.addTransition(new Transition(1, 1, ReadWriteHead.Direction.Right, q2));

        // q2
        q2.addTransition(new Transition(ReadWriteHead.BLANK, ReadWriteHead.BLANK, ReadWriteHead.Direction.Left, q4));
        q2.addTransition(new Transition(0, 1, ReadWriteHead.Direction.Left, q3));
        q2.addTransition(new Transition(1, 1, ReadWriteHead.Direction.Right, q2));

        // q3
        q3.addTransition(new Transition(ReadWriteHead.BLANK, ReadWriteHead.BLANK, ReadWriteHead.Direction.Right, q0));
        q3.addTransition(new Transition(0, 0, ReadWriteHead.Direction.Left, q3));
        q3.addTransition(new Transition(1, 1, ReadWriteHead.Direction.Left, q3));

        // q4
        q4.addTransition(new Transition(ReadWriteHead.BLANK, 0, ReadWriteHead.Direction.Right, q6));
        q4.addTransition(new Transition(0, 0, ReadWriteHead.Direction.Left, q4));
        q4.addTransition(new Transition(1, ReadWriteHead.BLANK, ReadWriteHead.Direction.Left, q4));

        // q5
        q5.addTransition(new Transition(ReadWriteHead.BLANK, ReadWriteHead.BLANK, ReadWriteHead.Direction.Right, q6));
        q5.addTransition(new Transition(0, ReadWriteHead.BLANK, ReadWriteHead.Direction.Right, q5));
        q5.addTransition(new Transition(1, ReadWriteHead.BLANK, ReadWriteHead.Direction.Right, q5));

        // Note: q6 has no transitions.

        setState(q0);
    }

    public void run(int m, int n) {

        ArrayList<Integer> input = new ArrayList<Integer>(m + 2 * n);

        for (int i = 0; i < m; i++) {
            input.add(0);
        }

        for (int i = 0; i < n; i++) {
            input.add(1);
            input.add(0);
        }

        super.run(input);
    }
}
