package org.example.seminar1.models;

import java.util.List;

public abstract class AbstractSorter {
    private int[] numberList;

    public AbstractSorter(int[] numberList) {
        this.numberList = numberList;
    }

    public abstract void sort();

    public int[] getNumberList() {
        return numberList;
    }

    public void setNumberList(int[] numberList) {
        this.numberList = numberList;
    }
}
