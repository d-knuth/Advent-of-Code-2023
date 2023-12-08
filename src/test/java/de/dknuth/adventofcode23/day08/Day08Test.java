package de.dknuth.adventofcode23.day08;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Day08Test {
    @Test
    void testSolutionToPart1() {
        Day08 day = new Day08();
        List<String> testInputs = Arrays.asList(
                "RL", "", "AAA = (BBB, CCC)", "BBB = (DDD, EEE)", "CCC = (ZZZ, GGG)", "DDD = (DDD, DDD)",
                "EEE = (EEE, EEE)", "GGG = (GGG, GGG)", "ZZZ = (ZZZ, ZZZ)");
        String expectedOutput = "2";
        Assertions.assertEquals(expectedOutput, day.solutionToPart1(testInputs));

    }

    @Test
    void testSecondSolutionToPart1() {
        Day08 day = new Day08();
        List<String> testInputs = Arrays.asList(
                "LLR", "", "AAA = (BBB, BBB)", "BBB = (AAA, ZZZ)", "ZZZ = (ZZZ, ZZZ)");
        String expectedOutput = "6";
        Assertions.assertEquals(expectedOutput, day.solutionToPart1(testInputs));

    }

    @Test
    void testSolutionToPart2() {
        Day08 day = new Day08();
        List<String> testInputs = Arrays.asList(
                "LR", "", "11A = (11B, XXX)", "11B = (XXX, 11Z)", "11Z = (11B, XXX)", "22A = (22B, XXX)",
                "22B = (22C, 22C)", "22C = (22Z, 22Z)", "22Z = (22B, 22B)", "XXX = (XXX, XXX)");
        String expectedOutput = "6";
        Assertions.assertEquals(expectedOutput, day.solutionToPart2(testInputs));

    }
}
