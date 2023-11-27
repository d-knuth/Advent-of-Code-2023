package de.dknuth.adventofcode23.day;

public interface Day {

    String solutionToPart1();

    default String solutionToPart2() {
        return "";
    }

    default void init() {
    }

}
