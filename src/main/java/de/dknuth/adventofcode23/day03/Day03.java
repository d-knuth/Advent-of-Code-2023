package de.dknuth.adventofcode23.day03;

import java.util.List;

import de.dknuth.adventofcode23.day.Day;

public class Day03 implements Day {

    @Override
    public String solutionToPart1(List<String> inputs) {
        EngineSchematic engineSchematic = new EngineSchematic(inputs);
        return engineSchematic.sumOfPartnumbers().toString();
    }

    @Override
    public String solutionToPart2(List<String> inputs) {
        EngineSchematic engineSchematic = new EngineSchematic(inputs);
        return engineSchematic.gearRatios().stream().reduce(0, Integer::sum).toString();
    }

}
