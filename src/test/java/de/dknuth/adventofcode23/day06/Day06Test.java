package de.dknuth.adventofcode23.day06;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Day06Test {
    @Test
    void testSolutionToPart1() {
        Day06 day = new Day06();
        List<String> testInputs = Arrays.asList(
                "Time:      7  15   30", "Distance:  9  40  200");
        String expectedOutput = "288";
        Assertions.assertEquals(expectedOutput, day.solutionToPart1(testInputs));
    }

    @Test
    void testSolutionToPart2() {
        Day06 day = new Day06();
        List<String> testInputs = Arrays.asList(
                "Time:      7  15   30", "Distance:  9  40  200");
        String expectedOutput = "71503";
        Assertions.assertEquals(expectedOutput, day.solutionToPart2(testInputs));
    }
}
