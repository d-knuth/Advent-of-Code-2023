package de.dknuth.adventofcode23.day10;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Day10Test {
    @Test
    void testSolutionToPart1() {
        Day10 day = new Day10();
        List<String> testInputs = Arrays.asList(
                "-L|F7", "7S-7|", "L|7||", "-L-J|", "L|-JF");
        String expectedOutput = "4";
        Assertions.assertEquals(expectedOutput, day.solutionToPart1(testInputs));
    }

    @Test
    void test2SolutionToPart1() {
        Day10 day = new Day10();
        List<String> testInputs = Arrays.asList(
                "7-F7-", ".FJ|7", "SJLL7", "|F--J", "LJ.LJ");
        String expectedOutput = "8";
        Assertions.assertEquals(expectedOutput, day.solutionToPart1(testInputs));
    }

    @Test
    void testSolutionToPart2() {

    }
}
