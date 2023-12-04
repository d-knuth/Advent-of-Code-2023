package de.dknuth.adventofcode23.day04;

import java.util.List;

import de.dknuth.adventofcode23.day.Day;

public class Day04 implements Day {

    @Override
    public String solutionToPart1(List<String> inputs) {

        return inputs.stream()
                .map(Card::new)
                .map(Card::calcPoints)
                .reduce(0, Integer::sum)
                .toString();
    }

    @Override
    public String solutionToPart2(List<String> inputs) {
        return "";
    }

}
