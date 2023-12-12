package de.dknuth.adventofcode23.day12;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConditionRecord {

    private String springs;

    public String getSprings() {
        return springs;
    }

    public List<Integer> getDamagedSpringsPattern() {
        return damagedSpringsPattern;
    }

    private List<Integer> damagedSpringsPattern;

    ConditionRecord(String input) {
        this.springs = input.substring(0, input.indexOf(" "));
        this.damagedSpringsPattern = Arrays.asList(input.substring(input.indexOf(" ")).trim().split(","))
                .stream().map(Integer::parseInt).toList();
    }

    public void unfold() {
        StringBuilder builder = new StringBuilder(this.springs);
        List<Integer> newPattern = new ArrayList<>(this.damagedSpringsPattern);
        for (int i = 0; i < 4; i++) {
            builder.append("?").append(this.springs);
            newPattern.addAll(this.damagedSpringsPattern);
        }
        this.springs = builder.toString();
        this.damagedSpringsPattern = newPattern;
    }

    long countMatches(String springs, List<Integer> pattern) {
        long count = 0;

        if (!springs.contains("?")) {
            if (matchesPattern(springs, pattern)) {
                return 1;
            } else {
                return 0;
            }
        }
        count += countMatches(springs.replaceFirst("\\?", "#"), pattern);
        count += countMatches(springs.replaceFirst("\\?", "."), pattern);
        return count;
    }

    private boolean matchesPattern(String springs, List<Integer> pattern) {
        pattern = new ArrayList<>(pattern);
        while (springs.length() > 0) {
            if (springs.length() < pattern.stream().reduce(0, Integer::sum)) {
                return false;
            }
            if (countStartingDamagedSprings(springs) != pattern.get(0) && countStartingDamagedSprings(springs) != 0) {
                return false;
            } else if (countStartingDamagedSprings(springs) == pattern.get(0)) {
                springs = springs.substring(pattern.get(0));
                pattern.remove(0);
                if (pattern.isEmpty() && (springs.length() != 0 && springs.contains("#"))) {
                    return false;
                } else if (pattern.isEmpty()) {
                    return true;
                }
            } else {
                springs = springs.substring(1);
            }
        }
        return false;
    }

    private int countStartingDamagedSprings(String springs) {
        int count = 0;
        for (int i = 0; i < springs.length() && springs.charAt(i) == '#'; i++) {
            count++;
        }
        return count;
    }
}
