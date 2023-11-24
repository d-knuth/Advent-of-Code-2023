package de.dknuth.adventofcode23;

import de.dknuth.adventofcode23.day.DayFactory;

public class Main {
    public static void main(String[] args) {
        int dayNumber = 1;
        printSolution(dayNumber);
    }

    private static void printSolution(int dayNumber) {
        if (DayFactory.canCreate(dayNumber)) {
            System.out.println(solution(dayNumber));
        } else {
            System.out.println("Day has not been solved yet.");
        }
    }

    private static String solution(int dayNumber) {
        return String.format("""

                Day %d:
                        Part 1: %s
                        Part 2: %s

                """, dayNumber, solutionOfPart1(dayNumber), solutionOfPart2(dayNumber));
    }

    private static String solutionOfPart1(int dayNumber) {
        return DayFactory.create(dayNumber).part1();
    }

    private static String solutionOfPart2(int dayNumber) {
        return DayFactory.create(dayNumber).part2();
    }

}