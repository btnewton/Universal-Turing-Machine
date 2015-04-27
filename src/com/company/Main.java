package com.company;

import com.company.machine.ReadWriteHead;
import com.company.state.State;
import com.company.state.Transition;

public class Main {

    public static void main(String[] args) {

        State q1 = new State(1);
        State q2 = new State(2);

        q1.addTransition(new Transition(2, 2, ReadWriteHead.Direction.Left, q2));
        q1.debug();
    }
}
