package de.dknuth.adventofcode23;

import de.dknuth.adventofcode23.day.DayFactory;

public class Main {
    public static void main(String[] args) {
        int dayNumber = 1;
        print(dayNumber);
    }

    private static void print(int dayNumber) {
        if (DayFactory.canCreate(dayNumber)) {
            StringBuilder builder = new StringBuilder("\n");
            builder.append("Day " + dayNumber + ":\n");
            builder.append("\tPart 1: " + DayFactory.create(dayNumber).part1() + "\n");
            builder.append("\tPart 2: " + DayFactory.create(dayNumber).part2() + "\n");
            System.out.println(builder.toString());
        } else {
            System.out.println("Day has not been solved yet.");
        }
    }
}