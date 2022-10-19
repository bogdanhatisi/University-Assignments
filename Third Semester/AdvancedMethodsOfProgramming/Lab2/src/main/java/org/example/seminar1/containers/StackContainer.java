package org.example.seminar1.containers;

import org.example.seminar1.models.Task;

import java.util.ArrayList;
import java.util.List;

public class StackContainer extends AbstractContainer {

    @Override
    public Task remove() {
        return dataStructure.remove(size() - 1);
    }

}
