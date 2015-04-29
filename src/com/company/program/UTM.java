package com.company.program;

import com.company.machine.CharacterNotInAlphabetException;
import com.company.machine.ReadWriteHead;
import com.company.machine.Tape;
import com.company.state.State;
import com.company.state.Transition;
import com.sun.xml.internal.ws.api.pipe.FiberContextSwitchInterceptor;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by brandt on 4/27/15.
 */
public class UTM {

    public static enum Status {
        Rejected,
        Accepted,
        Working,
        Waiting
    }

    private Status status;
    private State state;
    private Tape tape;

    public UTM(State M, char w) {
        status = Status.Waiting;
        state = M;
        state.setUTM(this);

        try {
            tape = new Tape(new ArrayList<Character>(Arrays.asList(new Character[]{w})), w);
            run();
        } catch (CharacterNotInAlphabetException e) {
            e.printStackTrace();
        }
    }

    public UTM(State M, Character[] w) {
        // Has not started and is not working yet
        status = Status.Waiting;
        state = M;
        state.setUTM(this);

        try {
            tape = new Tape(new ArrayList<Character>(Arrays.asList(w)));
            run();
        } catch (CharacterNotInAlphabetException e) {
            e.printStackTrace();
        }
    }

    public UTM() {
        status = Status.Waiting;
        state = null;
    }

    private void run() {
        ReadWriteHead readWriteHead;
        int transitionCounter = 0;

        readWriteHead = new ReadWriteHead(tape);
        status = Status.Working;

        while(status == Status.Working) {

            Transition transition = null;

            try {
                 transition = state.getTransition(readWriteHead.read());
            } catch (NullPointerException npe) {
                status = Status.Accepted;
            }

            if (transition != null) {
                readWriteHead.write(transition.getWriteCharacter());
                readWriteHead.moveHead(transition.getMove());

                state = transition.getNextState();
                transitionCounter++;
            }
        }

        outputResults(readWriteHead, transitionCounter);

        // Reset status
        status = Status.Waiting;
    }

    public void outputResults(ReadWriteHead readWriteHead, int transitionCounter) {
        System.out.println("+------------------------");
        System.out.println("|\tSTATUS: " + statusToString(status));
        System.out.println("+------------------------");

        if (readWriteHead != null) {
            System.out.println("The TM transitioned " + transitionCounter + " times!");
            System.out.println(tape.toString());
        }

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

    public Tape getTape() {
        return tape;
    }
}
