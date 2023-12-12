package de.dknuth.adventofcode23.day11;

import java.util.List;

import de.dknuth.adventofcode23.day.Day;

public class Day11 implements Day {

    @Override
    public String solutionToPart1(List<String> inputs) {
        Universe universe = new Universe(inputs);
        return String.valueOf(universe.calcDistances());
    }

    @Override
    public String solutionToPart2(List<String> inputs) {
        return "";
    }

}
