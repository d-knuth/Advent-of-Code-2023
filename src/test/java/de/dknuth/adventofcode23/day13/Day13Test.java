package de.dknuth.adventofcode23.day13;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import de.dknuth.adventofcode23.utils.InputReader;

class Day13Test {
    @Test
    void testSolutionToPart1() {
        Day13 day = new Day13();
        List<String> testInputs = InputReader.read("inputDay13test.txt");
        String expectedOutput = "405";
        Assertions.assertEquals(expectedOutput, day.solutionToPart1(testInputs));
    }

    @Test
    void testSolutionToPart2() {
        Day13 day = new Day13();
        List<String> testInputs = InputReader.read("inputDay13test.txt");
        String expectedOutput = "400";
        Assertions.assertEquals(expectedOutput, day.solutionToPart2(testInputs));
    }
}
