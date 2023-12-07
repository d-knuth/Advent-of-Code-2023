package de.dknuth.adventofcode23.day07;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Day07Test {
    @Test
    void testSolutionToPart1() {
        Day07 day = new Day07();
        List<String> testInputs = Arrays.asList(
                "32T3K 765", "T55J5 684", "KK677 28", "KTJJT 220", "QQQJA 483");
        String expectedOutput = "6440";
        Assertions.assertEquals(expectedOutput, day.solutionToPart1(testInputs));
    }

    @Test
    void testSolutionToPart2() {
        Day07 day = new Day07();
        List<String> testInputs = Arrays.asList(
                "32T3K 765", "T55J5 684", "KK677 28", "KTJJT 220", "QQQJA 483");
        String expectedOutput = "5905";
        Assertions.assertEquals(expectedOutput, day.solutionToPart2(testInputs));
    }
}
