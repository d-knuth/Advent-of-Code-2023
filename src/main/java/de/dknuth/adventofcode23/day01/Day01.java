package de.dknuth.adventofcode23.day01;

import static java.util.Map.entry;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import de.dknuth.adventofcode23.day.Day;
import de.dknuth.adventofcode23.utils.InputReader;

public class Day01 implements Day {
    private static final List<String> input = InputReader.readInput("inputDay01.txt");

    private static final Map<String, Integer> STRING_TO_DIGIT = Map.ofEntries(
            entry("zero", 0),
            entry("one", 1),
            entry("two", 2),
            entry("three", 3),
            entry("four", 4),
            entry("five", 5),
            entry("six", 6),
            entry("seven", 7),
            entry("eight", 8),
            entry("nine", 9));

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
                .map(s -> replaceFirstAndLastSpelledOutDigit(s))
                .map(s -> findFirstDigit(s) + findLastDigit(s))
                .map(Integer::parseInt)
                .reduce(0, (a, b) -> a + b)
                .toString();
    }

    private String replaceFirstAndLastSpelledOutDigit(String s) {
        String result = s;
        String firstSpelledOutDigit = "zero";
        int indexOfFirst = -1;
        String lastSpelledOutDigit = "zero";
        int indexOfLast = -1;
        for (Entry<String, Integer> e : STRING_TO_DIGIT.entrySet()) {
            int index = result.indexOf(e.getKey());
            if (index != -1 && (indexOfFirst == -1 || index < indexOfFirst)) {
                indexOfFirst = index;
                firstSpelledOutDigit = e.getKey();
            }
            index = result.lastIndexOf(e.getKey());
            if (index != -1 && index > indexOfLast) {
                indexOfLast = index;
                lastSpelledOutDigit = e.getKey();
            }
        }
        // If first spelled out digit ends with same character as last spelled out digit
        // add one character between: eighthree->eightthree
        if (lastCharEqualsFirstChar(firstSpelledOutDigit, lastSpelledOutDigit)) {
            result = duplicateFirstCharOfLastSpelledOutDigit(result, lastSpelledOutDigit, indexOfLast);
        }
        result = result.replace(firstSpelledOutDigit, STRING_TO_DIGIT.get(firstSpelledOutDigit).toString());
        result = result.replace(lastSpelledOutDigit, STRING_TO_DIGIT.get(lastSpelledOutDigit).toString());
        return result;
    }

    private String duplicateFirstCharOfLastSpelledOutDigit(String result, String lastSpelledOutDigit, int indexOfLast) {
        result = result.substring(0, indexOfLast) + lastSpelledOutDigit.substring(0, 1) +
                result.substring(indexOfLast);
        return result;
    }

    private boolean lastCharEqualsFirstChar(String first, String second) {
        return first.endsWith(second.substring(0, 1));
    }

    private String findFirstDigit(String s) {
        char character = (char) s.chars().filter(c -> Character.isDigit((char) c)).findFirst().getAsInt();
        return String.valueOf(character);
    }

    private String findLastDigit(String s) {
        return findFirstDigit(new StringBuilder(s).reverse().toString());
    }
}
