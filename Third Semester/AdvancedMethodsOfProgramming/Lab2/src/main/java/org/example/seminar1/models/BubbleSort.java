package org.example.seminar1.models;

public class BubbleSort extends AbstractSorter{

    public BubbleSort(int[] numberList) {
        super(numberList);
    }

    @Override
    public void sort() {
        int numberListSorted[] = getNumberList();
        int n = numberListSorted.length;
        for (int i = 0; i < n - 1; i++)
            for (int j = 0; j < n - i - 1; j++)
                if (numberListSorted[j] > numberListSorted[j + 1]) {
                    // swap arr[j+1] and arr[j]
                    int temp = numberListSorted[j];
                    numberListSorted[j] = numberListSorted[j + 1];
                    numberListSorted[j + 1] = temp;
                }

        setNumberList(numberListSorted);
    }
}
