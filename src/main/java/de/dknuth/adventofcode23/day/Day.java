package de.dknuth.adventofcode23.day;

import java.util.List;

public interface Day {

    String solutionToPart1(List<String> input);

    default String solutionToPart2(List<String> input) {
        return "";
    }

    default void init() {
    }

}
