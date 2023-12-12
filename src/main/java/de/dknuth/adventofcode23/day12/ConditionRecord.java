package de.dknuth.adventofcode23.day12;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.util.Pair;

public class ConditionRecord {

    private String springs;
    private List<Integer> damagedSpringsPattern = new ArrayList<>();
    private Map<Pair<String, List<Integer>>, Long> solutionCache = new HashMap<>();


    public List<Integer> getDamagedSpringsPattern() {
        return damagedSpringsPattern;
    }

    public String getSprings() {
        return springs;
    }

    ConditionRecord(String input) {
        this.springs = input.substring(0, input.indexOf(" "));
        this.damagedSpringsPattern = Arrays.asList(input.substring(input.indexOf(" ")).trim().split(","))
                .stream().map(Integer::parseInt).toList();
    }

    ConditionRecord unfold() {
        StringBuilder builder = new StringBuilder(this.springs);
        List<Integer> newPattern = new ArrayList<>(this.damagedSpringsPattern);
        for (int i = 0; i < 4; i++) {
            builder.append("?").append(this.springs);
            newPattern.addAll(this.damagedSpringsPattern);
        }
        this.springs = builder.toString();
        this.damagedSpringsPattern = newPattern;
        return this;
    }

    long countMatches(String springs, List<Integer> pattern) {
        Pair<String, List<Integer>> wantedSolution = new Pair<>(springs, pattern);
        if (solutionCache.get(wantedSolution) != null) {
            return solutionCache.get(wantedSolution);
        }
        long count = 0;
        if (springs.isBlank()) {
            if (pattern.isEmpty()) {
                return 1;
            } else {
                return 0;
            }
        }

        if (springs.charAt(0) == '.') {
            count = countMatches(springs.substring(1), pattern);
        } else if (springs.charAt(0) == '?') {
            count = countMatches("#" + springs.substring(1), pattern) + countMatches(springs.substring(1), pattern);
        } else if (springs.charAt(0) == '#') {
            if (pattern.isEmpty()) {
                count = 0;
            } else {
                int groupSize = pattern.get(0);
                if (groupSize <= springs.length() && springs.substring(0, groupSize).matches("^[#\\?]+$")) {
                    List<Integer> newPattern = pattern.subList(1, pattern.size());
                    if (newPattern.isEmpty() && springs.length() == groupSize) {
                        count = 1;
                    } else if (!newPattern.isEmpty() && springs.length() == groupSize) {
                        count = 0;
                    } else if (springs.charAt(groupSize) == '.' || springs.charAt(groupSize) == '?') {
                        count = countMatches(springs.substring(groupSize + 1), newPattern);
                    } else if (springs.charAt(groupSize) == '#') {
                        count = 0;
                    }
                } else {
                    count = 0;
                }
            }
        }
        solutionCache.put(wantedSolution, count);
        return count;
    }
}
