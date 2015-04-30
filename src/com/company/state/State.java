package com.company.state;

import com.company.program.UTM;

import java.util.ArrayList;

/**
 *  Structure to define a state in a Turing Machine
 */
public class State {

    private int id;
    private UTM utm;
    private ArrayList<Transition> transitions;

    /**
     * @param id    id of state (should be unique)
     */
    public State(int id) {
        this.id = id;
        utm = null;
        transitions = new ArrayList<Transition>();
    }

    /**
     * Sets the State's UTM which will trace to all of its child UTMs
     * @param utm
     */
    public void setUTM(UTM utm) {
        if (this.utm == null) {
            this.utm = utm;
            for (Transition transition : transitions) {
                if (transition.getNextState() != null)
                    transition.getNextState().setUTM(utm);
            }
        }
    }

    /**
     * Adds a transition to the State
     * @param transition
     */
    public void addTransition(Transition transition) {
        transitions.add(transition);
    }

    /**
     * Returns the transition function corresponding to the given character
     * @param character character transitioning from
     * @return          transition function for given character
     */
    public Transition getTransition(char character) {
        for (Transition transition : transitions) {
            if (transition.getAcceptCharacter() == character) {
                return transition;
            }
        }

        // Halt state
        if (isAcceptState()) {
            // Accepted!
            utm.setStatus(UTM.Status.Accepted);
            return null;
        } else {
            // Rejected!
            utm.setStatus(UTM.Status.Rejected);
            return null;
        }
    }

    public String toString() {
        return "q" + id;
    }

    protected boolean isAcceptState() {
        return false;
    }
}
