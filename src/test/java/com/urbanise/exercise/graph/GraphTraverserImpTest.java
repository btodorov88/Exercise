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
        cyclicGraph = createTestGraphWithCicle();
    }

    private static Map<String, Set<String>>  createTestGraphWithCicle() {
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
    public void testVisitChildElements() throws Exception {
        Set<String> expectedResult = new HashSet<>();
        expectedResult.add("A");
        expectedResult.add("C");
        expectedResult.add("G");

        Set<String> result = new HashSet<>();
        traverser.visitChildElements(graph, "A", e -> result.add(e));

        assertEquals(expectedResult, result);

    }

    @Test
    public void testVisitChildElementsWithCicles() throws Exception {
        Set<String> expectedResult = new HashSet<>();
        expectedResult.add("A");
        expectedResult.add("B");
        expectedResult.add("C");

        Set<String> result = new HashSet<>();
        traverser.visitChildElements(cyclicGraph, "A", e -> result.add(e));

        assertEquals(expectedResult, result);

    }

    @Test
    public void testVisitParentElements() throws Exception {
        Set<String> expectedResult = new HashSet<>();
        expectedResult.add("A");
        expectedResult.add("B");
        expectedResult.add("C");
        expectedResult.add("G");

        Set<String> result = new HashSet<>();
        traverser.visitParentElements(graph, "G", e -> result.add(e));

        assertEquals(expectedResult, result);
    }

    @Test
    public void testVisitParentElementsWithCicles() throws Exception {
        Set<String> expectedResult = new HashSet<>();
        expectedResult.add("A");
        expectedResult.add("B");
        expectedResult.add("C");

        Set<String> result = new HashSet<>();
        traverser.visitParentElements(cyclicGraph, "A", e -> result.add(e));

        assertEquals(expectedResult, result);
    }
}