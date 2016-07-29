package com.urbanise.exercise;

import com.urbanise.exercise.graph.GraphTraverserImp;
import com.urbanise.exercise.reader.ConsoleDependencyReader;
import com.urbanise.exercise.reader.DependencyReader;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * The main class of the application which provides an implementation
 * for the Urbanise Coding Exercise.
 */
public class Application {

    public static void main(String[] args) {

        new Application().run();
    }
    
    private void run() {
        TransitiveDependencyCalculator<String> dependencyCalculator = new TransitiveDependencyCalculator<>(new GraphTraverserImp<>());
        DependencyReader dependencyReader = new ConsoleDependencyReader();

        Map<String, Set<String>> directDependencyGraph = dependencyReader.readDependencyGraph();

        // Print the input dependencies
        System.out.println("Direct dependencies:");
        prettyPrint(directDependencyGraph);

        // Calculate and print the transitive dependencies
        Map<String, Set<String>> transitiveDependencies = dependencyCalculator.getChildTransitiveDependencies(directDependencyGraph);
        System.out.println("Child transitive dependencies:");
        prettyPrint(transitiveDependencies);

        // Calculate and print the inverse transitive dependencies
        Map<String, Set<String>> inverseTransitiveDependencies = dependencyCalculator.getParentTransitiveDependencies(directDependencyGraph);
        System.out.println("Inverse (parent) transitive dependencies:");
        prettyPrint(inverseTransitiveDependencies);
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
