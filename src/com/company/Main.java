package com.company;

import com.company.machine.ReadWriteHead;
import com.company.machine.Tape;
import com.company.program.UTM;
import com.company.state.State;
import com.company.state.Transition;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        int selection;

        do {
            System.out.print("\nPlease select a TM to run\n" +
                    "1. Subtraction TM\n" +
                    "2. Non-Terminating TM (Requires manual termination of program!)\n" +
                    "3. Busy Beaver 3\n" +
                    "4. Busy Beaver 4\n" +
                    "5. Busy Beaver 5\n" +
                    "Selection: ");
            Scanner scanner = new Scanner(System.in);
            try {
                selection = scanner.nextInt();
            } catch (InputMismatchException ime) {
                selection = -1;
            }

            System.out.print("\n\n");

            switch (selection) {
                case -1:
                    System.err.print("Invalid input.");
                    break;
                case 1:
                    try{
                        System.out.print("\nCalculating m - n.\nPlease enter a int for n: ");
                        int m = scanner.nextInt();
                        System.out.print("\nCalculating " + m + " - n.\nPlease enter a int for n: ");
                        int n = scanner.nextInt();
                        subtractionUTM(m, n);
                    } catch (InputMismatchException ime){
                        System.err.print("Invalid input.");
                    }
                    break;
                case 2:
                    nonTerminatingUTM();
                    break;
                case 3:
                    busyBeaver3();
                    break;
                case 4:
                    busyBeaver4();
                    break;
                case 5:
                    busyBeaver5();
                    break;
                default:
                    System.exit(0);
            }
            System.out.print("\n\n");
        } while(true);
    }

    /**
     * Runs the Busy Beaver 3 TM. State code was taken from http://www.catonmat.net/blog/busy-beaver/
     */
    private static void busyBeaver3() {

        State q0 = new State(0);
        State q1 = new State(1);
        State q2 = new State(2);

        q0.addTransition(new Transition('0', '1', ReadWriteHead.Direction.Right, q1));
        q0.addTransition(new Transition('1', '1', ReadWriteHead.Direction.Right, null));

        q1.addTransition(new Transition('0', '0', ReadWriteHead.Direction.Right, q2));
        q1.addTransition(new Transition('1', '1', ReadWriteHead.Direction.Right, q1));

        q2.addTransition(new Transition('0', '1', ReadWriteHead.Direction.Left, q2));
        q2.addTransition(new Transition('1', '1', ReadWriteHead.Direction.Left, q0));

        UTM utm = new UTM(q0, '0');
        System.out.println(utm.getTape().countCharcter('1') + " ones were written!");

    }

    /**
     * Runs the Busy Beaver 4 TM. State code was taken from http://www.catonmat.net/blog/busy-beaver/
     */
    private static void busyBeaver4() {

        State q0 = new State(0);
        State q1 = new State(1);
        State q2 = new State(2);
        State q3 = new State(3);

        q0.addTransition(new Transition('0', '1', ReadWriteHead.Direction.Right, q1));
        q0.addTransition(new Transition('1', '1', ReadWriteHead.Direction.Left, q1));

        q1.addTransition(new Transition('0', '1', ReadWriteHead.Direction.Left, q0));
        q1.addTransition(new Transition('1', '0', ReadWriteHead.Direction.Left, q2));

        q2.addTransition(new Transition('0', '1', ReadWriteHead.Direction.Right, null));
        q2.addTransition(new Transition('1', '1', ReadWriteHead.Direction.Left, q3));

        q3.addTransition(new Transition('0', '1', ReadWriteHead.Direction.Right, q3));
        q3.addTransition(new Transition('1', '0', ReadWriteHead.Direction.Right, q0));

        UTM utm = new UTM(q0, '0');
        System.out.println(utm.getTape().countCharcter('1') + " ones were written!");
    }

    /**
     * Runs the Busy Beaver 5 TM. State code was taken from http://www.catonmat.net/blog/busy-beaver/
     */
    private static void busyBeaver5() {

        State q0 = new State(0);
        State q1 = new State(1);
        State q2 = new State(2);
        State q3 = new State(3);
        State q4 = new State(4);

        q0.addTransition(new Transition('0', '1', ReadWriteHead.Direction.Left, q1));
        q0.addTransition(new Transition('1', '1', ReadWriteHead.Direction.Left, q0));

        q1.addTransition(new Transition('0', '1', ReadWriteHead.Direction.Right, q2));
        q1.addTransition(new Transition('1', '1', ReadWriteHead.Direction.Right, q1));

        q2.addTransition(new Transition('0', '1', ReadWriteHead.Direction.Left, q0));
        q2.addTransition(new Transition('1', '1', ReadWriteHead.Direction.Right, q3));

        q3.addTransition(new Transition('0', '1', ReadWriteHead.Direction.Left, q0));
        q3.addTransition(new Transition('1', '1', ReadWriteHead.Direction.Right, q4));

        q4.addTransition(new Transition('0', '1', ReadWriteHead.Direction.Right, null));
        q4.addTransition(new Transition('1', '0', ReadWriteHead.Direction.Right, q2));

        UTM utm = new UTM(q0, '0');
        System.out.println(utm.getTape().countCharcter('1') + " ones were written!");
    }

    /**
     * Runs a very simple UTM that never diverges
     */
    private static void nonTerminatingUTM() {
        State q0 = new State(0);
        State q1 = new State(1);

        q0.addTransition(new Transition(Tape.BLANK, '1', ReadWriteHead.Direction.Right, q1));
        q0.addTransition(new Transition('0', '1', ReadWriteHead.Direction.Right, q1));
        q0.addTransition(new Transition('1', '1', ReadWriteHead.Direction.Right, q1));

        q1.addTransition(new Transition(Tape.BLANK, '1', ReadWriteHead.Direction.Left, q0));
        q1.addTransition(new Transition('0', '1', ReadWriteHead.Direction.Left, q0));
        q1.addTransition(new Transition('1', '1', ReadWriteHead.Direction.Left, q0));


        UTM utm = new UTM(q0, '0');
        System.out.println(utm.getTape().countCharcter('1') + " ones were written!");
    }

    /**
     * Subtracts n from m via modus so if n >= m then m - n = 0
     * @param m first element
     * @param n second element
     */
    private static void subtractionUTM(int m, int n) {
        State q0 = new State(0);
        State q1 = new State(1);
        State q2 = new State(2);
        State q3 = new State(3);
        State q4 = new State(4);
        State q5 = new State(5);
        State q6 = new State(6);

        // q0
        q0.addTransition(new Transition('0', Tape.BLANK, ReadWriteHead.Direction.Right, q1));
        q0.addTransition(new Transition('1', Tape.BLANK, ReadWriteHead.Direction.Right, q5));

        // q1
        q1.addTransition(new Transition('0', '0', ReadWriteHead.Direction.Right, q1));
        q1.addTransition(new Transition('1', '1', ReadWriteHead.Direction.Right, q2));

        // q2
        q2.addTransition(new Transition(Tape.BLANK, Tape.BLANK, ReadWriteHead.Direction.Left, q4));
        q2.addTransition(new Transition('0', '1', ReadWriteHead.Direction.Left, q3));
        q2.addTransition(new Transition('1', '1', ReadWriteHead.Direction.Right, q2));

        // q3
        q3.addTransition(new Transition(Tape.BLANK, Tape.BLANK, ReadWriteHead.Direction.Right, q0));
        q3.addTransition(new Transition('0', '0', ReadWriteHead.Direction.Left, q3));
        q3.addTransition(new Transition('1', '1', ReadWriteHead.Direction.Left, q3));

        // q4
        q4.addTransition(new Transition(Tape.BLANK, '0', ReadWriteHead.Direction.Right, q6));
        q4.addTransition(new Transition('0', '0', ReadWriteHead.Direction.Left, q4));
        q4.addTransition(new Transition('1', Tape.BLANK, ReadWriteHead.Direction.Left, q4));

        // q5
        q5.addTransition(new Transition(Tape.BLANK, Tape.BLANK, ReadWriteHead.Direction.Right, q6));
        q5.addTransition(new Transition('0', Tape.BLANK, ReadWriteHead.Direction.Right, q5));
        q5.addTransition(new Transition('1', Tape.BLANK, ReadWriteHead.Direction.Right, q5));

        // Encode input
        Character[] input = new Character[m + 1 + n];

        for (int i = 0; i < m; i++) {
            input[i] = '0';
        }

        input[m] = '1';

        for (int i = 0; i < n; i++) {
            input[m + 1 + i] = '0';
        }

        UTM utm = new UTM(q0, input);

        // Translate output
        System.out.println(utm.getTape().countCharcter('0') + " = " + m + " monus " + n + "!");
    }
}
