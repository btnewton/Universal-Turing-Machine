package com.company;

import com.company.machine.ReadWriteHead;
import com.company.program.Program;
import com.company.program.SubtractionProgram;
import com.company.state.State;
import com.company.state.Transition;

public class Main {

    public static void main(String[] args) {

        SubtractionProgram subtractionProgram = new SubtractionProgram();
        subtractionProgram.run(6, 1);
    }
}
