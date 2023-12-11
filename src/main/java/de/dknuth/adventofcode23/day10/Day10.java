package de.dknuth.adventofcode23.day10;

import java.util.Arrays;
import java.util.List;

import de.dknuth.adventofcode23.day.Day;

public class Day10 implements Day {

    @Override
    public String solutionToPart1(List<String> inputs) {
        PipeMaze pipeMaze = new PipeMaze(inputs);
        int[] start = pipeMaze.findStart();
        int[] firstCurrent = pipeMaze.findFirstPipeFromStart();
        int[] secondCurrent = pipeMaze.findSeconddPipeFromStart();
        int[] firstRelativPrev = new int[] { start[0] - firstCurrent[0], start[1] - firstCurrent[1] };
        int[] secondRelativPrev = new int[] { start[0] - secondCurrent[0], start[1] - secondCurrent[1] };
        long count = 1;
        while (!Arrays.equals(firstCurrent, secondCurrent)) {

            int[] firstNext = pipeMaze.walk(firstRelativPrev, firstCurrent);
            firstRelativPrev = new int[] { firstCurrent[0] - firstNext[0], firstCurrent[1] - firstNext[1] };
            firstCurrent = firstNext;

            int[] secondNext = pipeMaze.walk(secondRelativPrev, secondCurrent);
            secondRelativPrev = new int[] { secondCurrent[0] - secondNext[0], secondCurrent[1] - secondNext[1] };
            secondCurrent = secondNext;
            count++;
        }
        return String.valueOf(count);
    }

    @Override
    public String solutionToPart2(List<String> inputs) {
        return "";
    }

}
