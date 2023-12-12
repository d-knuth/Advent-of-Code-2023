package de.dknuth.adventofcode23.day10;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Day10Test {
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
        Day10 day = new Day10();
        List<String> testInputs = Arrays.asList(
                "...........", ".S-------7.", ".|F-----7|.", ".||.....||.", ".||.....||.", ".|L-7.F-J|.", ".|..|.|..|.",
                ".L--J.L--J.", "...........");

        String expectedOutput = "4";
        Assertions.assertEquals(expectedOutput, day.solutionToPart2(testInputs));

    }

    @Test
    void test2SolutionToPart2() {
        Day10 day = new Day10();
        List<String> testInputs = Arrays.asList(
                "FF7FSF7F7F7F7F7F---7", "L|LJ||||||||||||F--J", "FL-7LJLJ||||||LJL-77", "F--JF--7||LJLJ7F7FJ-",
                "L---JF-JLJ.||-FJLJJ7", "|F|F-JF---7F7-L7L|7|", "|FFJF7L7F-JF7|JL---7", "7-L-JL7||F7|L7F-7F7|",
                "L.L7LFJ|||||FJL7||LJ", "L7JLJL-JLJLJL--JLJ.L");

        String expectedOutput = "10";
        Assertions.assertEquals(expectedOutput, day.solutionToPart2(testInputs));

    }
}
