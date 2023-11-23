package de.dknuth.adventofcode23;

import de.dknuth.adventofcode23.day.Day;
import de.dknuth.adventofcode23.day.DayFactory;
import de.dknuth.adventofcode23.day01.Day01;

public class Main {
    public static void main(String[] args) {
        Class<? extends Day> day = Day01.class;
        print(day);
    }

    private static void print(Class<? extends Day> day) {
        StringBuilder builder = new StringBuilder("\n");
        builder.append(day.getSimpleName() + ":\n");
        builder.append("\tPart 1: " + DayFactory.create(day).part1() + "\n");
        builder.append("\tPart 2: " + DayFactory.create(day).part2() + "\n");
        System.out.println(builder.toString());
    }
}