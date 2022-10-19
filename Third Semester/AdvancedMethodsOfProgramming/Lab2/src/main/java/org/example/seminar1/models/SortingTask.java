package org.example.seminar1.models;

import org.example.seminar1.utils.Utils;

public class SortingTask extends Task {
    private int [] numberList;
    private String method;

    public SortingTask(String taskID, String description, int[] numberList,String method) {
        super(taskID, description);
        this.numberList = numberList;
        this.method = method;
    }

    @Override
    public void execute() {

        if(method=="BubbleSort")
        {
            AbstractSorter sorter = new BubbleSort(numberList);
            sorter.sort();
            numberList = sorter.getNumberList();
            Utils.printArray(numberList);
        }

        else if (method=="QuickSort")
        {
            AbstractSorter sorter = new QuickSort(numberList);
            sorter.sort();
            numberList = sorter.getNumberList();
            Utils.printArray(numberList);
        }
        else {
            System.out.println("Please select a valid sorting method");
            System.out.println("BubbleSort or QuickSort");
        }

    }
}
