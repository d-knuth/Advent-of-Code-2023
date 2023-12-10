package de.dknuth.adventofcode23.day09;

import java.util.Arrays;
import java.util.List;
import java.util.stream.LongStream;

import de.dknuth.adventofcode23.day.Day;

public class Day09 implements Day {

    @Override
    public String solutionToPart1(List<String> inputs) {
        return String.valueOf(inputs.stream().map(this::history).map(this::nextPrediction)
                .reduce(0l, Long::sum));
    }

    @Override
    public String solutionToPart2(List<String> inputs) {
        return String.valueOf(inputs.stream().map(this::history).map(this::prevPrediction)
                .reduce(0l, Long::sum));
    }

    List<Long> history(String input) {
        return Arrays.asList(input.split(" ")).stream().map(Long::parseLong).toList();
    }

    long nextPrediction(List<Long> history) {
        long n = history.size();
        return LongStream.rangeClosed(0, n - 1).reduce(0,
                (l, k) -> l + (long) Math.pow(-1, n - 1.0 - k) * binomialCoeff(n, k) * history.get((int) k));
    }

    long prevPrediction(List<Long> history) {
        long n = history.size();
        return LongStream.rangeClosed(1, n).reduce(0,
                (l, k) -> l + (long) Math.pow(-1, k + 1.0) * binomialCoeff(n, k) * history.get((int) k - 1));
    }

    private long binomialCoeff(long n, long k) {
        k = 2 * k > n ? n - k : k;
        return LongStream.rangeClosed(1, k).reduce(1, (l1, l2) -> l1 * (n + 1 - l2) / l2);
    }

}
