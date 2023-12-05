package de.dknuth.adventofcode23.day05;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.LongStream;

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
        List<List<Long>> seedsWithRange = getSeedsWithRange(inputs);
        Almanac almanac = new Almanac(inputs);
        return Long.toString(
                seedsWithRange.stream().parallel()
                        .flatMapToLong(this::subSeedStream)
                        .map(almanac::seedToLocation)
                        .min().orElse(0));
    }

    private LongStream subSeedStream(List<Long> seedWithRange) {
        long start = seedWithRange.get(0);
        long range = seedWithRange.get(1);
        return LongStream.range(start, start + range);
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
