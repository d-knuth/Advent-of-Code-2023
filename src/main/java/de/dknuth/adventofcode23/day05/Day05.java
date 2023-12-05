package de.dknuth.adventofcode23.day05;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import de.dknuth.adventofcode23.day.Day;

public class Day05 implements Day {

    @Override
    public String solutionToPart1(List<String> inputs) {
        List<Long> seeds = getSeeds(inputs);
        Almanac almanac = new Almanac(inputs);
        return seeds.stream()
                .map(almanac::seedToLocation)
                .min(Comparator.naturalOrder())
                .orElse(0l).toString();
    }

    @Override
    public String solutionToPart2(List<String> inputs) {
        return "";
    }

    private List<Long> getSeeds(List<String> inputs) {
        String seedString = inputs.get(0);
        return Arrays.asList(seedString
                .substring(seedString.indexOf(":") + 1).trim()
                .replace("  ", " ")
                .split(" ")).stream()
                .map(Long::parseLong).toList();
    }

}
