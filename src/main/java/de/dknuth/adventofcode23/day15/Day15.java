package de.dknuth.adventofcode23.day15;

import java.util.Arrays;
import java.util.List;

import de.dknuth.adventofcode23.day.Day;

public class Day15 implements Day {

    @Override
    public String solutionToPart1(List<String> inputs) {
        return String.valueOf(Arrays.asList(inputs.get(0).split(","))
                .stream()
                .map(s -> s.chars()
                        .reduce(0, (a, b) -> ((a + b) * 17) % 256))
                .reduce(0, Integer::sum));
    }

    @Override
    public String solutionToPart2(List<String> inputs) {
        return "";
    }

}
