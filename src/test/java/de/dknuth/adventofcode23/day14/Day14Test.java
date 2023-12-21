package de.dknuth.adventofcode23.day14;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import de.dknuth.adventofcode23.utils.InputReader;

class Day14Test {
    @Test
    void testSolutionToPart1() {
        Day14 day = new Day14();
        List<String> testInputs = InputReader.read("inputDay14test.txt");
        String expectedOutput = "136";
        Assertions.assertEquals(expectedOutput, day.solutionToPart1(testInputs));

    }

    @Test
    void testSolutionToPart2() {
        Day14 day = new Day14();
        List<String> testInputs = InputReader.read("inputDay14test.txt");
        String expectedOutput = "64";
        Assertions.assertEquals(expectedOutput, day.solutionToPart2(testInputs));

    }
}
