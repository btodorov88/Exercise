package com.urbanise.exercise.reader;

import java.util.Map;
import java.util.Set;

/**
 * A class can implement the {@link DependencyReader} interface to provide implementation for reading
 * a dependency graph from an input source.
 *
 * @param <T> the type of the graph nodes.
 */
public interface DependencyReader<T> {
    /**
     * Reads a dependency graph from an input source.
     *
     * @return the dependency graph
     */
    Map<T, Set<T>> readDependencyGraph();
}
