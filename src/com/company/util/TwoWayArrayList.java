package com.company.util;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * This data structure was designed to simulate an array extending infinitely in either direction from the starting point.
 * Because it was designed specifically for UTM applications, access of new elements is limited to those adjacent to the
 * end of either list.
 */
public class TwoWayArrayList<E> {

    private ArrayList<E> positiveList;
    private ArrayList<E> negativeList;
    private E defaultValue;

    /**
     * @param size  initialize ArrayLists at given size
     */
    public TwoWayArrayList(int size) {
        positiveList = new ArrayList<E>(size);
        negativeList = new ArrayList<E>(size);
    }

    /**
     * Initialize ArrayLists at default 10
     */
    public TwoWayArrayList() {
        this(10);
    }

    /**
     * Initialize positive ArrayLists to given ArrayList.
     *
     * @param positiveList
     */
    public TwoWayArrayList(ArrayList<E> positiveList) {
        this.positiveList = positiveList;
        negativeList = new ArrayList<E>(10);
    }

    public void setDefaultValue(E defaultValue) {
        this.defaultValue = defaultValue;
    }

    /**
     * Returns the value at the specified index. If the index is one greater than the size of either ArrayList the default
     * value is returned.
     *
     * @param index         index of value to retrieve
     * @return              value found at position
     */
    public E get(int index) {
        if (index >= 0) {
            if (index == frontSize()) {
                addToFront(defaultValue);
                return defaultValue;
            } else {
                return positiveList.get(index);
            }
        } else {
            index = (-1 * index) - 1;
            if (index == backSize()) {
                addToBack(defaultValue);
                return defaultValue;
            } else {
                return negativeList.get(index);
            }
        }
    }

    /**
     * Sets a value at the given location. If the given index is one greater than the size of either ArrayList add the
     * value to the end of the ArrayList.
     *
     * @param index index to add
     * @param value value to add
     */
    public void set(int index, E value) {
        if (index >= 0) {
            if (index == frontSize()) {
                addToFront(value);
            } else {
                positiveList.set(index, value);
            }
        } else {
            index = (-1 * index) - 1;
            if (index == backSize()) {
                addToBack(null);
            } else {
                negativeList.set(index, value);
            }
        }
    }

    /**
     * @return  size of both lists
     */
    public int size() {
        return positiveList.size() + negativeList.size();
    }

    /**
     * @param value     value to be added to positiveList
     */
    public void addToFront(E value) {
        positiveList.add(value);
    }

    /**
     * @param value     value to be added to negativeList
     */
    public void addToBack(E value) {
        negativeList.add(value);
    }

    /**
     * @return  size of negativeList
     */
    public int backSize() {
        return negativeList.size();
    }

    /**
     * @return  size of positiveList
     */
    public int frontSize() {
        return positiveList.size();
    }

    /**
     * Returns an array of both negative and positive Lists combined with negative list first.
     *
     * @param var1  destination
     * @param <T>   Type to copy as
     * @return      array combining both ArrayLists
     */
    public <T> T[] toArray(T[] var1) {

        ArrayList<E> totalList = new ArrayList<E>(size());
        totalList.addAll(negativeList);
        totalList.addAll(positiveList);

        if(var1.length < size()) {
            return (T[]) Arrays.copyOf(totalList.toArray(), size(), var1.getClass());
        } else {
            System.arraycopy(totalList.toArray(), 0, var1, 0, size());
            if(var1.length > size()) {
                var1[size()] = null;
            }
            return var1;
        }
    }

    public E getDefaultValue() {
        return defaultValue;
    }
}
