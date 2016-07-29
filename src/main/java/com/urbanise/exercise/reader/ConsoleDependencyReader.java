package com.urbanise.exercise.reader;

import java.io.InputStream;
import java.util.*;

/**
 * Provides and implementation that is capable of reading a dependency graph from the console.
 */
public class ConsoleDependencyReader implements DependencyReader<String> {

    private final InputStream inputStream;

    /**
     * Configures the {@link ConsoleDependencyReader} to read the dependency graph from System.in
     */
    public ConsoleDependencyReader(){
        this(System.in);
    }

    public ConsoleDependencyReader(InputStream in){
        this.inputStream = in;
    }

    /**
     * Reads a dependency graph from the Console.
     *
     * @return the dependency graph
     */
    @Override
    public Map<String, Set<String>> readDependencyGraph() {
        System.out.println("Welcome to the Coding Exercise!");
        System.out.println("Please enter input dependencies. When done press Enter to start the calculation.");


        return readGraphFromConsole(new Scanner(inputStream));
    }

    private Map<String, Set<String>> readGraphFromConsole(Scanner sc) {
        String inputLine = sc.nextLine().trim();
        Map<String, Set<String>> graph = new HashMap<>();
        while(!inputLine.isEmpty()) {
            String[] input = inputLine.split("\\s+");

            if(!isInputValid(input)){
                System.out.println("Invalid entry. Please try again.");
                continue;
            }

            Set<String> children = readDependencies(input);

            graph.put(input[0], children);

            inputLine = sc.nextLine().trim();
        }
        return graph;
    }

    private Set<String> readDependencies(String[] input) {
        Set<String> children = new HashSet<>();
        for (int i = 1; i < input.length; i++) {
            children.add(input[i]);
        }
        return children;
    }

    private boolean isInputValid(String[] input) {
        return input.length > 0;
        //More validation should go here
    }

}
