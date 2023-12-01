package de.dknuth.adventofcode23.day01;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Day01Test {
    @Test
    void testSolutionToPart1() {
        Day01 day = new Day01();
        List<String> testInputs = Arrays.asList(
                "1abc2", "pqr3stu8vwx", "a1b2c3d4e5f", "treb7uchet");
        List<String> expectedOutputs = Arrays.asList("12", "38", "15", "77");

        for (int i = 0; i < testInputs.size(); i++) {
            String output = day.numberMadeOfFirstAndLastDigits(testInputs.get(i));
            Assertions.assertEquals(expectedOutputs.get(i), output);
        }
    }

    @Test
    void testSolutionToPart2() {
        Day01 day = new Day01();
        List<String> testInputs = Arrays.asList(
                "two1nine",
                "eightwothree",
                "abcone2threexyz",
                "xtwone3four",
                "4nineeightseven2",
                "zoneight234",
                "7pqrstsixteen");
        List<String> expectedOutputs = Arrays.asList("29", "83", "13", "24", "42", "14", "76");

        for (int i = 0; i < testInputs.size(); i++) {
            String output = day.numberMadeOfFirstAndLastDigits(day.replaceSpelledOutDigits(testInputs.get(i)));
            Assertions.assertEquals(expectedOutputs.get(i), output);
        }
    }
}