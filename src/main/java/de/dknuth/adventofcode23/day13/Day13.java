package de.dknuth.adventofcode23.day13;

import java.util.ArrayList;
import java.util.List;

import de.dknuth.adventofcode23.day.Day;

public class Day13 implements Day {

    @Override
    public String solutionToPart1(List<String> inputs) {

        return String.valueOf(generatePatternInputs(inputs).stream()
                .map(Pattern::new)
                .map(p -> p.vertSymLinePosition() != -1 ? p.vertSymLinePosition() : p.horizSymLinePosition() * 100)
                .reduce(0l, Long::sum));

    }

    @Override
    public String solutionToPart2(List<String> inputs) {
        return String.valueOf(generatePatternInputs(inputs).stream()
                .map(Pattern::new)
                .map(p -> p.vertSmudgedSymLine() != -1 ? p.vertSmudgedSymLine() : p.horizSmudgedSymLine() * 100)
                .reduce(0l, Long::sum));

    }

    List<List<String>> generatePatternInputs(List<String> inputs) {
        List<List<String>> patternInputs = new ArrayList<>();
        int i = 0;
        while (i < inputs.size()) {
            List<String> input = new ArrayList<>();
            for (int j = i; j < inputs.size() && !inputs.get(j).isBlank(); j++) {
                input.add(inputs.get(j));
                i++;
            }
            i++;
            patternInputs.add(input);
        }
        return patternInputs;

    }

}
