package com.urbanise.exercise;

import com.urbanise.exercise.graph.GraphTraverser;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Responsible to provide an implementation that calculates transitive dependencies based on the direct ones.
 *
 * @param <T> the type of the items that have dependencies between each other.
 */
public class TransitiveDependencyCalculator<T> {
    private GraphTraverser<T> graphTraverser;

    /**
     * @param graphTraverser provides the graph traversal algorithms implementation.
     */
    public TransitiveDependencyCalculator(GraphTraverser<T> graphTraverser){
        this.graphTraverser = graphTraverser;
    }

    /**
     * Calculates all transitive dependencies for the provided graph.
     *
     * @param graph the input graph representing the direct dependencies.
     * @return graph containing all transitive dependencies.
     */
    public Map<T, Set<T>> getChildTransitiveDependencies(Map<T, Set<T>> graph){
        validateInputGraph(graph);

        Map<T, Set<T>> result = new HashMap<>();

        // Calculate the transitive dependencies for each item(node).
        for(T node:graph.keySet()){

            Set<T> dependencies = new HashSet<>();

            graphTraverser.visitChildNodes(graph, node, child -> {
                if (child != node)
                    dependencies.add(child);
                return true;
            });

            result.put(node, dependencies);
        }

        return result;
    }

    /**
     * Calculates all inverse(parent) transitive dependencies for the provided graph.
     *
     * @param graph the input graph representing the direct dependencies.
     * @return graph containing all inverse(parent) transitive dependencies.
     */
    public Map<T, Set<T>> getParentTransitiveDependencies(Map<T, Set<T>> graph){
        validateInputGraph(graph);

        Map<T, Set<T>> result = new HashMap<>();

        // Calculate the transitive dependencies for each item(node).
        for(T node:graph.keySet()){

            Set<T> dependencies = new HashSet<>();

            graphTraverser.visitParentNodes(graph, node, parent -> {
                if (parent != node)
                    dependencies.add(parent);
                return true;
            });

            result.put(node, dependencies);
        }

        return result;
    }

    private void validateInputGraph(Map<T, Set<T>> graph) {
        if(graph == null)
            throw new IllegalArgumentException("Input dependency graph cannot be null");

        //Additional validation should go here
    }

}
