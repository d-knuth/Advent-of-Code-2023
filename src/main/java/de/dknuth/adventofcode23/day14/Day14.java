package de.dknuth.adventofcode23.day14;

import java.util.List;
import java.util.stream.LongStream;

import de.dknuth.adventofcode23.day.Day;

public class Day14 implements Day {

    @Override
    public String solutionToPart1(List<String> inputs) {
        Platform platform = new Platform(inputs);
        platform.moveAllRocksNorth();
        return String.valueOf(LongStream.rangeClosed(1, platform.getContent().size())
                .map(i -> platform.calcWeightOfRow((int) i))
                .sum());
    }

    @Override
    public String solutionToPart2(List<String> inputs) {
        Platform platform = new Platform(inputs);
        platform.performCycle(1000000000);
        return String.valueOf(LongStream.rangeClosed(1, platform.getContent().size())
                .map(i -> platform.calcWeightOfRow((int) i))
                .sum());
    }

}
