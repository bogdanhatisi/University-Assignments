package org.example.seminar1.containers;

import org.example.seminar1.models.Task;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractContainer implements Container {

    protected List<Task> dataStructure = new ArrayList<>();

    public abstract Task remove();

    public void add(Task task) {
        dataStructure.add(task);
    }


    public int size() {
        return dataStructure.size();
    }


    public boolean isEmpty() {
        return size() == 0;
    }
}
