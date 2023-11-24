package de.dknuth.adventofcode23.day;

public interface Day {

    String part1();

    default String part2() {
        return "";
    }

    default void init() {
    }

}
