package com.company.util;

/**
 * Created by brandt on 11/11/15.
 */
public class TapeSection {

    private TapeSection prevTapeSection;
    private TapeSection nextTapeSection;
    private char value;
    private static char defaultValue;

    public TapeSection(TapeSection prevTapeSection, TapeSection nextTapeSection) {
        this(prevTapeSection, nextTapeSection, defaultValue);
    }

    public TapeSection(TapeSection prevTapeSection, TapeSection nextTapeSection, char value) {
        setPrevTapeSection(prevTapeSection);
        setNextTapeSection(nextTapeSection);
        this.value = value;
    }

    public char getValue() {
        return value;
    }

    public void setValue(char value) {
        this.value = value;
    }

    public void setNextTapeSection(TapeSection nextTapeSection) {
        if (nextTapeSection != null) {
            nextTapeSection.prevTapeSection = this;
        }
        this.nextTapeSection = nextTapeSection;
    }

    public void setPrevTapeSection(TapeSection prevTapeSection) {
        if (prevTapeSection != null) {
            prevTapeSection.nextTapeSection = this;
        }
        this.prevTapeSection = prevTapeSection;
    }

    public boolean hasNext() {
        return nextTapeSection != null;
    }

    public boolean hasPrev() {
        return prevTapeSection != null;
    }
    public TapeSection getNextTapeSection() {
        return nextTapeSection;
    }

    public TapeSection getPrevTapeSection() {
        return prevTapeSection;
    }
    public static void setDefaultValue(char defaultValue) {
        TapeSection.defaultValue = defaultValue;
    }
}
