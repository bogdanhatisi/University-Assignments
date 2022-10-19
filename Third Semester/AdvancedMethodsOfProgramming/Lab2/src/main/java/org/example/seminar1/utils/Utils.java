package org.example.seminar1.utils;

import java.time.format.DateTimeFormatter;

public class Utils {
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm");
    public static final void printArray(int[] n)
    {
        System.out.println("This is your array, printed! :)");
        for(int i = 0; i < n.length; i++)
        {
            System.out.println(n[i]);
        }
    }
}
