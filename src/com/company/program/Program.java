package com.company.program;

/**
 * Created by brandt on 4/27/15.
 */
public class Program {

    public static enum Status {
        Rejected,
        Accepted,
        Working
    }

    private Status status;

    public Program() {
        status = null;
    }

    public void start() {
        status = Status.Working;
    }


    public void setStatus(Status status) {
        this.status = status;
    }
}
