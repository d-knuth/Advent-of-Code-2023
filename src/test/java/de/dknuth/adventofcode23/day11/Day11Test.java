package de.dknuth.adventofcode23.day11;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Day11Test {
    @Test
    void testSolutionToPart1() {
        Day11 day = new Day11();
        List<String> testInputs = Arrays.asList(
                "...#......", ".......#..", "#.........", "..........", "......#...", ".#........", ".........#",
                "..........", ".......#..", "#...#.....");
        String expectedOutput = "374";
        Assertions.assertEquals(expectedOutput, day.solutionToPart1(testInputs));
    }

    @Test
    void testSolutionToPart2() {

    }
}
