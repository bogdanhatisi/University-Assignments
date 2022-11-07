package org.example.seminar1.factories;

import org.example.seminar1.containers.Container;

/**
 * Factory Interface
 */
public interface Factory {
    Container createContainer(Strategy strategy);
}
