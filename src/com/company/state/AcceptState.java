package com.company.state;

import com.company.program.UTM;

/**
 * Created by brandt on 4/27/15.
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
