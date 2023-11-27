package de.dknuth.adventofcode23;

import de.dknuth.adventofcode23.day.DayFactory;

public class Main {
    public static void main(String[] args) {
        int dayNumber = DayFactory.getMaxDayNumber();
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

                """, dayNumber, solutionToPart1(dayNumber), solutionToPart2(dayNumber));
    }

    private static String solutionToPart1(int dayNumber) {
        return DayFactory.create(dayNumber).solutionToPart1();
    }

    private static String solutionToPart2(int dayNumber) {
        return DayFactory.create(dayNumber).solutionToPart2();
    }

}