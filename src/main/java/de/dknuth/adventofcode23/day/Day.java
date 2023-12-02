package de.dknuth.adventofcode23.day;

import java.util.List;

public interface Day {

    String solutionToPart1(List<String> inputs);

    default String solutionToPart2(List<String> inputs) {
        return "";
    }

    default void init() {
    }

}
