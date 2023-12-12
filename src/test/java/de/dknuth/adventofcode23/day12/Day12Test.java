package de.dknuth.adventofcode23.day12;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Day12Test {
    @Test
    void testSolutionToPart1() {
        Day12 day = new Day12();
        List<String> testInputs = Arrays.asList(
                "???.### 1,1,3",
                ".??..??...?##. 1,1,3",
                "?#?#?#?#?#?#?#? 1,3,1,6",
                "????.#...#... 4,1,1",
                "????.######..#####. 1,6,5",
                "?###???????? 3,2,1");
        String expectedOutput = "21";
        Assertions.assertEquals(expectedOutput, day.solutionToPart1(testInputs));
    }

    @Test
    void testSolutionToPart2() {

    }
}
