package de.dknuth.adventofcode23.day09;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Day09Test {
    @Test
    void testSolutionToPart1() {
        Day09 day = new Day09();
        List<String> testInputs = Arrays.asList(
                "0 3 6 9 12 15", "1 3 6 10 15 21", "10 13 16 21 30 45");
        String expectedOutput = "114";
        Assertions.assertEquals(expectedOutput, day.solutionToPart1(testInputs));

    }

    @Test
    void testSolutionToPart2() {
        Day09 day = new Day09();
        List<String> testInputs = Arrays.asList(
                "0 3 6 9 12 15", "1 3 6 10 15 21", "10 13 16 21 30 45");
        String expectedOutput = "2";
        Assertions.assertEquals(expectedOutput, day.solutionToPart2(testInputs));
    }
}
