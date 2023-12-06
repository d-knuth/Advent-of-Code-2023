package de.dknuth.adventofcode23.day06;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import de.dknuth.adventofcode23.day.Day;

public class Day06 implements Day {

    @Override
    public String solutionToPart1(List<String> inputs) {
        return raceList(inputs).stream().map(Race::countWaysToBeatRecord).reduce(1l, Math::multiplyExact).toString();
    }

    @Override
    public String solutionToPart2(List<String> inputs) {
        return String.valueOf(inputToRacePart2(inputs).countWaysToBeatRecord());
    }

    List<Race> raceList(List<String> inputs) {
        List<Long> times = inputToNumberList(inputs.get(0), 6);
        List<Long> distances = inputToNumberList(inputs.get(1), 10);
        List<Race> raceList = new ArrayList<>();
        for (int i = 0; i < times.size(); i++) {
            raceList.add(new Race(times.get(i), distances.get(i)));
        }
        return raceList;
    }

    private List<Long> inputToNumberList(String input, int startIndex) {
        return Arrays.asList(
                input
                        .substring(startIndex).trim()
                        .replaceAll(" +", " ")
                        .split(" "))
                .stream().map(Long::parseLong).toList();
    }

    private Long inputToNumber(String input, int startIndex) {
        return Long.parseLong(input
                .substring(startIndex).trim()
                .replace(" ", ""));
    }

    private Race inputToRacePart2(List<String> inputs) {
        long time = inputToNumber(inputs.get(0), 6);
        long distance = inputToNumber(inputs.get(1), 10);
        return new Race(time, distance);
    }
}
