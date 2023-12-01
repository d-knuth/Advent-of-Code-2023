package de.dknuth.adventofcode23.utils;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class InputReader {

    private InputReader() {
    }

    private static final String PATH_TO_INPUTS = "./src/main/resources/inputs/";

    public static List<String> readInput(String fileName) {
        try {
            return Files.readAllLines(Path.of(PATH_TO_INPUTS, fileName));
        } catch (IOException e) {
            e.printStackTrace();
            throw new UncheckedIOException(e);
        }
    }

}
