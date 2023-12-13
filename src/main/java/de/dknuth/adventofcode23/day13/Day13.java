package de.dknuth.adventofcode23.day13;

import java.util.ArrayList;
import java.util.List;

import de.dknuth.adventofcode23.day.Day;

public class Day13 implements Day {

    @Override
    public String solutionToPart1(List<String> inputs) {

        List<Pattern> patterns = generatePatternInputs(inputs).stream().map(Pattern::new).toList();
        return "";
    }

    @Override
    public String solutionToPart2(List<String> inputs) {
        return "";
    }

    List<List<String>> generatePatternInputs(List<String> inputs) {
        List<List<String>> patternInputs = new ArrayList<>();
        int i = 0;
        while (i < inputs.size()) {
            List<String> input = new ArrayList<>();
            for (int j = 0; !inputs.get(j).isBlank(); j++) {
                input.add(inputs.get(j));
                i++;
            }
            i++;
            patternInputs.add(input);
        }
        return patternInputs;

    }

}
