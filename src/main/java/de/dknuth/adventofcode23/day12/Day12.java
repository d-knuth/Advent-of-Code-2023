package de.dknuth.adventofcode23.day12;

import java.util.List;

import de.dknuth.adventofcode23.day.Day;

public class Day12 implements Day {

    @Override
    public String solutionToPart1(List<String> inputs) {
        return String.valueOf(inputs.stream().map(ConditionRecord::new)
                .map(c -> c.countMatches(c.getSprings(), c.getDamagedSpringsPattern()))
                .reduce(0l, Long::sum));
    }

    @Override
    public String solutionToPart2(List<String> inputs) {
        return String.valueOf(inputs.stream().map(ConditionRecord::new).map(c -> c.unfold())
                .map(c -> c.countMatches(c.getSprings(), c.getDamagedSpringsPattern()))
                .reduce(0l, Long::sum));
    }

}
