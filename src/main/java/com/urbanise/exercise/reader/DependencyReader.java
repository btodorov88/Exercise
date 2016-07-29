package com.urbanise.exercise.reader;

import java.util.Map;
import java.util.Set;

/**
 * A class can implement the {@link DependencyReader} interface to provide implementation for reading
 * a dependency graph from an input source.
 */
public interface DependencyReader {
    /**
     * Reads a dependency graph from an input source.
     *
     * @return the dependency graph
     */
    Map<String, Set<String>> readDependencyGraphFromConsole();
}
