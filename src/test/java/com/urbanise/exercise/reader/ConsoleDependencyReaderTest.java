package com.urbanise.exercise.reader;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Created by btodorov on 7/29/16.
 */
public class ConsoleDependencyReaderTest {

    @Test
    public void testReadDependencyGraphFromConsole() throws Exception {
        StringBuilder input = new StringBuilder();
        input.append("A B C");
        input.append(System.lineSeparator());
        input.append("B D");
        input.append(System.lineSeparator());
        input.append(System.lineSeparator());

        // Create a dummy inputStream
        InputStream in = new ByteArrayInputStream(input.toString().getBytes(StandardCharsets.UTF_8));

        ConsoleDependencyReader dependencyReader = new ConsoleDependencyReader(in);
        Map<String, Set<String>> dependencies = dependencyReader.readDependencyGraph();

        assertArrayEquals(new String[] {"B", "C"}, dependencies.get("A").toArray());
        assertArrayEquals(new String[] {"D"}, dependencies.get("B").toArray());
    }
}