package com.urbanise.exercise;

import com.urbanise.exercise.graph.GraphTraverser;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 * @param <T>
 */
public class GraphDependencyCalculator<T> {
    private GraphTraverser<T> graphTraverser;

    /**
     *
     * @param graphTraverser
     */
    public GraphDependencyCalculator(GraphTraverser<T> graphTraverser){
        this.graphTraverser = graphTraverser;
    }

    /**
     *
     * @param graph
     * @return
     */
    public Map<T, Set<T>> getChildTransitiveDependencies(Map<T, Set<T>> graph){
        validateInputGraph(graph);

        Map<T, Set<T>> result = new HashMap<>();

        for(T node:graph.keySet()){

            Set<T> dependencies = new HashSet<>();

            graphTraverser.visitChildElements(graph, node, element -> {
                if (element != node)
                    dependencies.add(element);
                return true;
            });

            result.put(node, dependencies);
        }

        return result;
    }

    /**
     *
     * @param graph
     * @return
     */
    public Map<T, Set<T>> getParentTransitiveDependencies(Map<T, Set<T>> graph){
        validateInputGraph(graph);

        Map<T, Set<T>> result = new HashMap<>();

        for(T node:graph.keySet()){

            Set<T> dependencies = new HashSet<>();

            graphTraverser.visitParentElements(graph, node, element -> {
                if (element != node)
                    dependencies.add(element);
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
