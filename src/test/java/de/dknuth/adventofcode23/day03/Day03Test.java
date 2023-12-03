package de.dknuth.adventofcode23.day03;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Day03Test {
    @Test
    void testSolutionToPart1() {
        Day03 day = new Day03();
        List<String> testInputs = Arrays.asList(
                "467..114..", "...*......", "..35..633.", "......#...", "617*......", ".....+.58.", "..592.....",
                "......755.", "...$.*....", ".664.598..");
        String expectedOutput = "4361";
        Assertions.assertEquals(expectedOutput, day.solutionToPart1(testInputs));
    }

    @Test
    void testSolutionToPart2() {

    }
}
