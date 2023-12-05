package de.dknuth.adventofcode23.day05;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import de.dknuth.adventofcode23.day.Day;

public class Day05 implements Day {

    @Override
    public String solutionToPart1(List<String> inputs) {
        List<Long> seeds = getSeeds(inputs);
        for (int i = 0; i < seeds.size(); i += 2) {
            System.out.println(seeds.get(i) + seeds.get(i + 1));
        }
        Almanac almanac = new Almanac(inputs);
        return seeds.stream()
                .map(almanac::seedToLocation)
                .min(Comparator.naturalOrder())
                .orElse(0l).toString();
    }

    @Override
    public String solutionToPart2(List<String> inputs) {
        List<List<Long>> seedsWitchRange = getSeedsWithRange(inputs);
        Almanac almanac = new Almanac(inputs);
        return seedsWitchRange.stream()
                .flatMap(this::subSeedStream)
                .map(almanac::seedToLocation)
                .min(Comparator.naturalOrder())
                .orElse(0l).toString();
    }

    private Stream<Long> subSeedStream(List<Long> seedWithRange) {
        long start = seedWithRange.get(0);
        long range = seedWithRange.get(1);
        return Stream.iterate(start, s -> s + 1).limit(range);
    }

    List<List<Long>> getSeedsWithRange(List<String> inputs) {
        List<List<Long>> result = new ArrayList<>();
        List<Long> input = getSeeds(inputs);
        for (int i = 0; i < input.size(); i += 2) {
            result.add(List.of(input.get(i), input.get(i + 1)));
        }
        return result;
    }

    private List<Long> getSeeds(List<String> inputs) {
        String seedString = inputs.get(0);
        return Arrays.asList(seedString
                .substring(seedString.indexOf(":") + 1).trim()
                .replace("  ", " ")
                .split(" ")).stream().map(Long::parseLong).toList();
    }


}
