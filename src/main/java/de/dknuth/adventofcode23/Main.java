package de.dknuth.adventofcode23;

import de.dknuth.adventofcode23.day.DayFactory;

public class Main {
    public static void main(String[] args) {
        int dayNumber = 1;
        print(dayNumber);
    }

    private static void print(int dayNumber) {
        if (DayFactory.canCreate(dayNumber)) {
            System.out.println(solution(dayNumber));
        } else {
            System.out.println("Day has not been solved yet.");
        }
    }

    private static String solution(int dayNumber) {
        StringBuilder builder = new StringBuilder("\n");
        builder.append("Day ");
        builder.append(dayNumber);
        builder.append(":\n\tPart 1: ");
        builder.append(solutionOfPart1(dayNumber));
        builder.append("\n\tPart 2: ");
        builder.append(solutionOfPart2(dayNumber));
        builder.append("\n");
        return builder.toString();
    }

    private static String solutionOfPart1(int dayNumber) {
        return DayFactory.create(dayNumber).part1();
    }

    private static String solutionOfPart2(int dayNumber) {
        return DayFactory.create(dayNumber).part2();
    }

}