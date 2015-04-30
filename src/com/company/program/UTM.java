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
 * UTM is a Universal Turing Machine that accepts an arbitrary number of states and input length.
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

    /**
     * Creates a new UTM with TM M and a tape filled with w
     * @param M TM
     * @param w universal tape character
     */
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

    /**
     * Creates a new UTM with TM M and string (character array) w
     *
     * @param M TM
     * @param w input string
     */
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

    /**
     * Runs the TM
     */
    private void run() {
        ReadWriteHead readWriteHead;
        int transitionCounter = 0;

        readWriteHead = new ReadWriteHead(tape);
        status = Status.Working;

        while(status == Status.Working) {

            Transition transition = null;

            // Output to show machine is running on potentially divergent TMs
            if (transitionCounter % 5000000 == 0) {
                System.out.println(transitionCounter + ": " + tape.toString());
            }

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

        outputResults(transitionCounter);

        // Reset status
        status = Status.Waiting;
    }

    /**
     * Outputs the status of the TM and, the number of transitions it has made and the tape
     * @param transitionCounter transition count
     */
    public void outputResults(int transitionCounter) {
        System.out.println("\n+------------------------");
        System.out.println("|\tSTATUS: " + statusToString(status));
        System.out.println("+------------------------\n");

        if (tape != null) {
            System.out.println("The TM transitioned " + transitionCounter + " times!\n");
            System.out.println(tape.toString() + "\n");
        }
    }

    /**
     * @param status    status to convert to String
     * @return          String equivalent of status
     */
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

    /**
     * @param status    sets the status of the machine
     */
    public void setStatus(Status status) {
        this.status = status;
    }

    /**
     * @return  returns the tape that the UTM is using.
     */
    public Tape getTape() {
        return tape;
    }
}
