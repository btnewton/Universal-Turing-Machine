package com.company.program;

import com.company.machine.CharacterNotInAlphabetException;
import com.company.machine.ReadWriteHead;
import com.company.machine.Tape;
import com.company.state.State;
import com.company.state.Transition;
import com.sun.xml.internal.ws.api.pipe.FiberContextSwitchInterceptor;

import java.util.ArrayList;

/**
 * Created by brandt on 4/27/15.
 */
public class Program {

    public static enum Status {
        Rejected,
        Accepted,
        Working,
        Waiting
    }

    private Status status;
    private State state;

    public Program(State state) {
        // Has not started and is not working yet
        status = Status.Waiting;
        this.state = state;
    }

    public Program() {}


    public void run(ArrayList<Character> input) {
        ReadWriteHead readWriteHead;


        try {
            readWriteHead = new ReadWriteHead(new Tape(input));
            status = Status.Working;
        } catch (CharacterNotInAlphabetException cna) {
            readWriteHead = null;
            System.err.println(cna.getMessage());
            status = Status.Rejected;
        }

        while(status == Status.Working) {
            Transition transition = state.getTransition(readWriteHead.read());

            if (transition != null) {
                readWriteHead.write(transition.getWriteCharacter());
                readWriteHead.moveHead(transition.getMove());

                state = transition.getNextState();
                System.out.println("Moving to: " + transition);
            }
        }

        System.out.println(statusToString(status));

        if (readWriteHead != null) {
            System.out.println(readWriteHead.getTapeString());
        }

        // Reset status
        status = Status.Waiting;
    }

    public static String statusToString(Status status) {
        switch (status) {
            case Rejected:
                return "Rejected";
            case Accepted:
                return "Accepted";
            case Working:
                return "Working";
            default:
                return "Waiting";
        }
    }

    public void start() {
        status = Status.Working;
    }

    protected void setState(State state) {
        this.state = state;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
