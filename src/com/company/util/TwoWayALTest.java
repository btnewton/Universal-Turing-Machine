package com.company.util;

/**
 * Created by brandt on 4/29/15.
 */
public class TwoWayALTest {

    TwoWayArrayList<String> test;


    public TwoWayALTest() {
        test = new TwoWayArrayList<String>();
        test.setDefaultValue("_");
        test.addToFront("Brandt");
        test.addToFront("Thomas");
        test.addToFront("Newton");
        test.addToBack("Hi");
        test.addToBack("My");
        test.addToBack("Name");
        test.addToBack("Is");
        output();

        System.out.println(test.get(3));
        System.out.println(test.get(0));
        System.out.println(test.get(-2));

        output();
    }

    public void output() {
        String[] outputarray = test.toArray(new String[test.size()]);
        for (String str : outputarray) {
            System.out.print(str + " ");
        }
        System.out.print("\n");
    }

    public static void main(String[] args) {
        TwoWayALTest twoWayALTest = new TwoWayALTest();
    }

}
