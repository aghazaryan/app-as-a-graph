package com.picsart.test.graph.tools;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.util.stream.Stream;

/**
 * @author ag on 2019-04-24
 */

public class FileReader {

    public static Stream<String> readTextFile(String path) throws FileNotFoundException {
        return new BufferedReader(new java.io.FileReader(path)).lines().filter(s -> !s.isEmpty());
    }
}
