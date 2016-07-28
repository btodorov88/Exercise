package com.urbanise.exercise.visitor;

/**
 * A class can implement the {@link Visitor} interface when it wants to be informed when an graph node is visited.
 *
 * @param <T> the type of nodes that are visited.
 */
public interface Visitor<T> {
    /**
     * This method is called whenever a node in the graph is visited.
     *
     * @param node the node that is visited.
     * @return true if the visiting process should continue.
     */
    boolean visit(T node);
}
