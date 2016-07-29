package com.urbanise.exercise.graph;

import com.urbanise.exercise.visitor.Visitor;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Implements the Depth-first search (DFS) algorithm for traversing graphs.
 *
 * @param <T> the type of the nodes.
 */
public class GraphTraverserImp<T> implements GraphTraverser<T> {

    /**
     * Traverses and visits all child nodes for the provided input node in the graph.
     *
     * @param graph the input graph.
     * @param node the starting node for the traversal.
     * @param visitor {@link Visitor} that will be notified for every node that is being traversed.
     */
    @Override
    public void visitChildNodes(Map<T, Set<T>> graph, T node, Visitor<T> visitor) {
            Set<T> visited = new HashSet<>();
            dfs(graph, node, visited, visitor);
    }

    private void dfs(Map<T, Set<T>> graph, T node, Set<T> visited, Visitor<T> visitor){
        if(!visited.contains(node)){
            visited.add(node);

            boolean shouldContinue = visitor.visit(node);
            if(!shouldContinue)
                return;

            if(graph.get(node) != null) {
                for (T child : graph.get(node)) {
                    dfs(graph, child, visited, visitor);
                }
            }
        }
    }

    /**
     * Traverses and visits all parent nodes for the provided input node in the graph.
     *
     * @param graph the input graph.
     * @param inputNode the starting node for the traversal.
     * @param visitor {@link Visitor} that will be notified for every node that is being traversed.
     */
    @Override
    public void visitParentNodes(Map<T, Set<T>> graph, T inputNode, Visitor<T> visitor) {
        for(T node : graph.keySet()){
            visitChildNodes(graph, node, child -> {
                if (child.equals(inputNode)) {
                    visitor.visit(node);
                    // Parent confirmed. Stop the traversal.
                    return false;
                }
                return true;
            });
        }
    }
}
