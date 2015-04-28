package com.company.state;

import com.company.program.Program;

import java.util.ArrayList;

/**
 * Created by brandt on 4/27/15.
 */
public class State {

    private int id;
    private Program program;
    private ArrayList<Transition> transitions;

    public State(int id, Program program) {
        this.id = id;
        this.program = program;
        transitions = new ArrayList<Transition>();
    }

    public void addTransition(Transition transition) {
        transitions.add(transition);
    }

    public Transition getTransition(char character) {
        for (Transition transition : transitions) {
            if (transition.getAcceptCharacter() == character) {
                return transition;
            }
        }

        // Halt state
        if (isAcceptState()) {
            // Accepted!
            program.setStatus(Program.Status.Accepted);
            return null;
        } else {
            // Rejected!
            program.setStatus(Program.Status.Rejected);
            return null;
        }
    }

    public int getId() {
        return id;
    }

    public String toString() {
        return "q" + id;
    }

    protected boolean isAcceptState() {
        return false;
    }
}
