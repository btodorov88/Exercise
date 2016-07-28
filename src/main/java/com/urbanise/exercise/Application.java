package com.urbanise.exercise;

import com.urbanise.exercise.graph.GraphTraverserImp;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Implementation of the Urbanise Coding Exercise.
 */
public class Application {

    public static void main(String[] args) {

        new Application().run();
    }

    private void run() {
        TransitiveDependencyCalculator<String> dependencyCalculator = new TransitiveDependencyCalculator<>(new GraphTraverserImp<>());

        Map<String, Set<String>> directDependencyGraph = prepareSampleGraph();

        System.out.println("Direct dependencies:");
        prettyPrint(directDependencyGraph);

        Map<String, Set<String>> transitiveDependencies = dependencyCalculator.getChildTransitiveDependencies(directDependencyGraph);
        System.out.println("Child transitive dependencies:");
        prettyPrint(transitiveDependencies);

        Map<String, Set<String>> reversedTransitiveDependencies = dependencyCalculator.getParentTransitiveDependencies(directDependencyGraph);
        System.out.println("Parent transitive dependencies:");
        prettyPrint(reversedTransitiveDependencies);
    }

    /**
     * Outputs a graph in a user-friendly way.
     *
     * @param graph the graph that will be visualized.
     */
    private void prettyPrint(Map<String, Set<String>> graph) {
        for(String element : graph.keySet()){
            String dependentElements = graph.get(element).stream().collect(Collectors.joining(" "));
            System.out.println(element + " " + dependentElements);
        }
    }

    /**
     * Creates example dependency graph.
     */
    private Map<String, Set<String>> prepareSampleGraph() {
        Map<String,Set<String>> graph = new HashMap<>();
        HashSet<String> children = new HashSet<>();
        children.add("B");
        children.add("C");

        graph.put("A", children);

        children = new HashSet<>();
        children.add("C");
        children.add("E");

        graph.put("B", children);

        children = new HashSet<>();
        children.add("G");

        graph.put("C", children);

        children = new HashSet<>();
        children.add("A");
        children.add("F");

        graph.put("D", children);

        children = new HashSet<>();
        children.add("F");

        graph.put("E", children);

        children = new HashSet<>();
        children.add("H");

        graph.put("F", children);
        return graph;
    }
}
