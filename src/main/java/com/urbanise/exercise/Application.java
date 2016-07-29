package com.urbanise.exercise;

import com.urbanise.exercise.graph.GraphTraverserImp;
import com.urbanise.exercise.reader.ConsoleDependencyReader;
import com.urbanise.exercise.reader.DependencyReader;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Implementation of the Urbanise Coding Exercise.
 */
public class Application {

    // Reads the dependency graph from the Console
    private final DependencyReader dependencyReader = new ConsoleDependencyReader();

    public static void main(String[] args) {

        new Application().run();
    }

    private void run() {
        TransitiveDependencyCalculator<String> dependencyCalculator = new TransitiveDependencyCalculator<>(new GraphTraverserImp<>());

        Map<String, Set<String>> directDependencyGraph = dependencyReader.readDependencyGraphFromConsole();

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
        for(String node : graph.keySet()){
            String dependentNodes = graph.get(node).stream().collect(Collectors.joining(" "));
            System.out.println(node + " " + dependentNodes);
        }
    }
}
