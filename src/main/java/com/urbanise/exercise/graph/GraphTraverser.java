package com.urbanise.exercise.graph;

import com.urbanise.exercise.visitor.Visitor;

import java.util.Map;
import java.util.Set;

/**
 * A class can implement the {@link GraphTraverser} interface to provide implementation for the graph traversal algorithms.
 *
 * @param <T> the type of the nodes.
 */
public interface GraphTraverser<T> {
    /**
     * Traverses and visits all child nodes for the provided input node in the graph.
     *
     * @param graph the input graph.
     * @param node the starting node for the traversal.
     * @param visitor {@link Visitor} that will be notified for every node that is being traversed.
     */
    void visitChildNodes(Map<T, Set<T>> graph, T node, Visitor<T> visitor);

    /**
     * Traverses and visits all parent nodes for the provided input node in the graph.
     *
     * @param graph the input graph.
     * @param node the starting node for the traversal.
     * @param visitor {@link Visitor} that will be notified for every node that is being traversed.
     */
    void visitParentNodes(Map<T, Set<T>> graph, T node, Visitor<T> visitor);
}
