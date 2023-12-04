package de.dknuth.adventofcode23.day03;

import java.util.List;

import de.dknuth.adventofcode23.day.Day;

public class Day03 implements Day {

    @Override
    public String solutionToPart1(List<String> inputs) {
        EngineSchematic engineSchematic = new EngineSchematic(inputs);
        return engineSchematic.listRequestedNumbers(1).stream().reduce(0, Integer::sum).toString();
    }

    @Override
    public String solutionToPart2(List<String> inputs) {
        EngineSchematic engineSchematic = new EngineSchematic(inputs);
        return engineSchematic.listRequestedNumbers(2).stream().reduce(0, Integer::sum).toString();
    }

}
