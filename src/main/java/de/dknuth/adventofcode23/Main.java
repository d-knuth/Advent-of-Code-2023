package de.dknuth.adventofcode23;

import de.dknuth.adventofcode23.day.DayFactory;
import de.dknuth.adventofcode23.utils.InputReader;

public class Main {
    public static void main(String[] args) {
        int dayNumber = 4;
        DayFactory.showSolvedDays();
        printSolution(dayNumber);
    }

    private static void printSolution(int dayNumber) {
        if (DayFactory.canCreate(dayNumber)) {
            System.out.println(solution(dayNumber));
        } else {
            System.out.println(String.format("""

                    Day %d has not been solved yet.
                    """, dayNumber));
        }
    }

    private static String solution(int dayNumber) {
        return String.format("""

                Solution to Day %d:
                                    Part 1: %s
                                    Part 2: %s
                """, dayNumber, solutionToPart1(dayNumber), solutionToPart2(dayNumber));
    }

    private static String solutionToPart1(int dayNumber) {
        return DayFactory.create(dayNumber).solutionToPart1(InputReader.read(dayNumber));
    }

    private static String solutionToPart2(int dayNumber) {
        return DayFactory.create(dayNumber).solutionToPart2(InputReader.read(dayNumber));
    }
}