package com.urbanise.exercise;

import com.urbanise.exercise.graph.GraphTraverser;
import com.urbanise.exercise.visitor.Visitor;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Created by btodorov on 7/28/16.
 */
public class TransitiveDependencyCalculatorTest {

    private static TransitiveDependencyCalculator<String> dependencyCalculator;
    private static Map<String, Set<String>> inputGraph;

    @BeforeClass
    public static void setupTest(){
        // Create mock GraphTraverser
        dependencyCalculator = new TransitiveDependencyCalculator<>(new GraphTraverser<String>() {
            @Override
            public void visitChildNodes(Map<String, Set<String>> graph, String node, Visitor<String> visitor) {
                visitor.visit("A");
                visitor.visit("B");
                visitor.visit("C");
            }

            @Override
            public void visitParentNodes(Map<String, Set<String>> graph, String node, Visitor<String> visitor) {
                visitor.visit("A");
                visitor.visit("B");
                visitor.visit("C");
            }
        });

        createTestGraph();
    }

    private static void createTestGraph() {
        inputGraph = new HashMap<>();

        inputGraph.put("A", null);
        inputGraph.put("B", null);
        inputGraph.put("C", null);
    }

    @Test
    public void testGetChildTransitiveDependencies() throws Exception {
        Map<String, Set<String>> dependencies = dependencyCalculator.getChildTransitiveDependencies(inputGraph);

        assertArrayEquals(new String[] {"B", "C"}, dependencies.get("A").toArray());
        assertArrayEquals(new String[] {"A", "C"}, dependencies.get("B").toArray());
        assertArrayEquals(new String[] {"A", "B"}, dependencies.get("C").toArray());
    }

    @Test
    public void testGetParentTransitiveDependencies() throws Exception {
        Map<String, Set<String>> dependencies = dependencyCalculator.getParentTransitiveDependencies(inputGraph);

        assertArrayEquals(new String[] {"B", "C"}, dependencies.get("A").toArray());
        assertArrayEquals(new String[] {"A", "C"}, dependencies.get("B").toArray());
        assertArrayEquals(new String[] {"A", "B"}, dependencies.get("C").toArray());
    }
}