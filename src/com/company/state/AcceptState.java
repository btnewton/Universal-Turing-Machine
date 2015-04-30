package com.company.state;

import com.company.program.UTM;

/**
 * Entering this state will end the machine's process
 */
public class AcceptState extends State {

    public AcceptState(int id) {
        super(id);
    }

    @Override
    protected boolean isAcceptState() {
        return true;
    }
}
