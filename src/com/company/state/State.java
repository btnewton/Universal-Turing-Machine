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

    public Transition getTransition(int character) {
        for (Transition transition : transitions) {
            if (transition.getAcceptCharacter() == character) {
                return transition;
            }
        }

        // TODO check if accept state and update if necessary?
        if (isAcceptState()) {
            System.out.println("ACCEPTED!");
        }


        // Rejected!
        program.setStatus(Program.Status.Rejected);
        return null;
    }

    public int getId() {
        return id;
    }

    public String toString() {
        return "q" + id;
    }

    public void debug() {
        System.out.println(this + " transitions to:");

        for (Transition transition : transitions) {
            System.out.println("\t" + transition);
        }
    }

    protected boolean isAcceptState() {
        return false;
    }
}
