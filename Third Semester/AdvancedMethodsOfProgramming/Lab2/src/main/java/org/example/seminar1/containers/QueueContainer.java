package org.example.seminar1.containers;

import org.example.seminar1.models.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that implements a queue container using abstract container
 */
public class QueueContainer extends AbstractContainer {
    @Override
    public Task remove() {
        return dataStructure.remove(0);
    }

}
