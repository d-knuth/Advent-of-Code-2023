package de.dknuth.adventofcode23.day05;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import de.dknuth.adventofcode23.utils.InputReader;

class Day05Test {
    @Test
    void testSolutionToPart1() {
        Day05 day = new Day05();
        List<String> testInputs = InputReader.read("inputDay05test.txt");
        String expectedOutput = "35";
        Assertions.assertEquals(expectedOutput, day.solutionToPart1(testInputs));
    }

    @Test
    void testSolutionToPart2() {
        Day05 day = new Day05();
        List<String> testInputs = InputReader.read("inputDay05test.txt");
        String expectedOutput = "46";
        Assertions.assertEquals(expectedOutput, day.solutionToPart2(testInputs));
    }
}
