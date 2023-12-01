package de.dknuth.adventofcode23.day01;

import static java.util.Map.entry;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import de.dknuth.adventofcode23.day.Day;
import de.dknuth.adventofcode23.utils.InputReader;

public class Day01 implements Day {
    private static final List<String> input = InputReader.readInput("inputDay01.txt");

    private static final Map<String, String> STRING_TO_DIGIT = Map.ofEntries(
            entry("zero", "z0ro"),
            entry("one", "o1e"),
            entry("two", "t2o"),
            entry("three", "t3ree"),
            entry("four", "f4ur"),
            entry("five", "f5ve"),
            entry("six", "s6x"),
            entry("seven", "s7ven"),
            entry("eight", "e8ght"),
            entry("nine", "n9ne"));

    @Override
    public String solutionToPart1() {
        return input.stream()
                .map(s -> findFirstDigit(s) + findLastDigit(s))
                .map(Integer::parseInt)
                .reduce(0, (a, b) -> a + b)
                .toString();
    }

    @Override
    public String solutionToPart2() {
        return input.stream()
                .map(this::replaceSpelledOutDigits)
                .map(s -> findFirstDigit(s) + findLastDigit(s))
                .map(Integer::parseInt)
                .reduce(0, (a, b) -> a + b)
                .toString();
    }

    private String replaceSpelledOutDigits(String s) {
        for (Entry<String, String> e : STRING_TO_DIGIT.entrySet()) {
            s = s.replace(e.getKey(), e.getValue());
        }
        return s;
    }

    private String findFirstDigit(String s) {
        char character = (char) s.chars().filter(c -> Character.isDigit((char) c)).findFirst().getAsInt();
        return String.valueOf(character);
    }

    private String findLastDigit(String s) {
        return findFirstDigit(new StringBuilder(s).reverse().toString());
    }
}
