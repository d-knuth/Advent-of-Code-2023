package de.dknuth.adventofcode23.day15;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import de.dknuth.adventofcode23.utils.InputReader;

public class Day15Test {

    @Test
    void testSolutionToPart1() {
        Day15 day = new Day15();
        List<String> testInputs = List.of("rn=1,cm-,qp=3,cm=2,qp-,pc=4,ot=9,ab=5,pc-,pc=6,ot=7");
        testInputs = InputReader.read("inputDay15test.txt");
        String expectedOutput = "1320";
        Assertions.assertEquals(expectedOutput, day.solutionToPart1(testInputs));
    }

    @Test
    void testSolution2ToPart1() {
        Day15 day = new Day15();
        List<String> testInputs = List.of("HASH");
        String expectedOutput = "52";
        Assertions.assertEquals(expectedOutput, day.solutionToPart1(testInputs));
    }

    @Test
    void testSolutionToPart2() {

    }
}
