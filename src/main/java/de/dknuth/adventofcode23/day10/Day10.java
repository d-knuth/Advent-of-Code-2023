package de.dknuth.adventofcode23.day10;

import java.util.List;

import de.dknuth.adventofcode23.day.Day;

public class Day10 implements Day {

    @Override
    public String solutionToPart1(List<String> inputs) {
        PipeMaze pipeMaze = new PipeMaze(inputs);
        return String.valueOf(pipeMaze.getAllPipesOfLoop().size() / 2);
    }

    @Override
    public String solutionToPart2(List<String> inputs) {

        PipeMaze maze = new PipeMaze(inputs);

        return String.valueOf(maze.countInsideTiles());
    }
}
