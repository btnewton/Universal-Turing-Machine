package com.company.state;

import com.company.program.Program;

/**
 * Created by brandt on 4/27/15.
 */
public class AcceptState extends State {

    public AcceptState(int id, Program program) {
        super(id, program);
    }

    @Override
    protected boolean isAcceptState() {
        return true;
    }
}
