package com.urbanise.exercise.graph;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Created by btodorov on 7/28/16.
 */
public class GraphTraverserImpTest {
    private static  GraphTraverser<String> traverser;

    private static Map<String, Set<String>> graph;
    private static Map<String, Set<String>> cyclicGraph;

    @BeforeClass
    public static void setupTest(){
        traverser = new GraphTraverserImp<>();

        graph = createTestGraph();
        cyclicGraph = createTestGraphWithCycle();
    }

    private static Map<String, Set<String>>  createTestGraphWithCycle() {
        Map<String, Set<String>> graph = new HashMap<>();

        HashSet<String> children = new HashSet<>();
        children.add("B");

        graph.put("A", children);

        children = new HashSet<>();
        children.add("C");

        graph.put("B", children);

        children = new HashSet<>();
        children.add("A");

        graph.put("C", children);

        return graph;
    }

    private static Map<String, Set<String>> createTestGraph() {
        Map<String, Set<String>>  graph = new HashMap<>();
        HashSet<String> children = new HashSet<>();
        children.add("C");

        graph.put("A", children);

        children = new HashSet<>();
        children.add("C");

        graph.put("B", children);

        children = new HashSet<>();
        children.add("G");

        graph.put("C", children);

        graph.put("G", new HashSet<>());

        return graph;
    }

    @Test
    public void testVisitChildNodes() throws Exception {
        Set<String> expectedResult = new HashSet<>();
        expectedResult.add("A");
        expectedResult.add("C");
        expectedResult.add("G");

        Set<String> result = new HashSet<>();
        traverser.visitChildNodes(graph, "A", result::add);

        assertEquals(expectedResult, result);

    }

    @Test
    public void testVisitChildNodesWithCycles() throws Exception {
        Set<String> expectedResult = new HashSet<>();
        expectedResult.add("A");
        expectedResult.add("B");
        expectedResult.add("C");

        Set<String> result = new HashSet<>();
        traverser.visitChildNodes(cyclicGraph, "A", result::add);

        assertEquals(expectedResult, result);

    }

    @Test
    public void testVisitParentNodes() throws Exception {
        Set<String> expectedResult = new HashSet<>();
        expectedResult.add("A");
        expectedResult.add("B");
        expectedResult.add("C");
        expectedResult.add("G");

        Set<String> result = new HashSet<>();
        traverser.visitParentNodes(graph, "G", result::add);

        assertEquals(expectedResult, result);
    }

    @Test
    public void testVisitParentNodesWithCycles() throws Exception {
        Set<String> expectedResult = new HashSet<>();
        expectedResult.add("A");
        expectedResult.add("B");
        expectedResult.add("C");

        Set<String> result = new HashSet<>();
        traverser.visitParentNodes(cyclicGraph, "A", result::add);

        assertEquals(expectedResult, result);
    }
}