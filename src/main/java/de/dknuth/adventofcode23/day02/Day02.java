package de.dknuth.adventofcode23.day02;

import java.util.List;

import de.dknuth.adventofcode23.day.Day;

public class Day02 implements Day {

    @Override
    public String solutionToPart1(List<String> inputs) {
        return inputs.stream()
                .map(Game::new)
                .filter(g -> g.isGamePossible(12, 13,
                        14))
                .map(Game::getId)
                .reduce(0, Integer::sum)
                .toString();
    }

    @Override
    public String solutionToPart2(List<String> inputs) {
        return inputs.stream()
                .map(Game::new)
                .map(Game::powerOfFewestNumberForPossibleGame)
                .reduce(0, Integer::sum)
                .toString();
    }
}
