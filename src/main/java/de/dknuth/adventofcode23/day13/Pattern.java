package de.dknuth.adventofcode23.day13;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.stream.Collectors;

public class Pattern {

    private List<String> content;
    private long oldVertSymLine;
    private long oldHorizSymLine;

    Pattern(List<String> input) {
        this.content = input;
        this.oldVertSymLine = vertSymLinePosition();
        this.oldHorizSymLine = horizSymLinePosition();

    }

    long vertSymLinePosition() {
        return symLinePosition("row");
    }

    long horizSymLinePosition() {
        return symLinePosition("column");
    }

    private long symLinePosition(String rowOrColumn) {
        IntFunction<String> getter;
        int max;
        if (rowOrColumn.equals("row")) {
            getter = this::getRow;
            max = content.size();
        } else {
            getter = this::getColumn;
            max = content.get(0).length();
        }
        List<Long> possibleSymLinePositions = symLinePositions(getter.apply(0));
        for (int i = 1; i < max; i++) {
            possibleSymLinePositions.retainAll(symLinePositions(getter.apply(i)));
            if (possibleSymLinePositions.isEmpty()) {
                return -1;
            }
        }
        return possibleSymLinePositions.get(0);
    }

    long vertSmudgedSymLine() {
        return smudgedSymLine("row");
    }

    long horizSmudgedSymLine() {
        return smudgedSymLine("collumn");
    }

    private long smudgedSymLine(String rowOrColumn) {
        List<List<Long>> allPossibleSmudgedSymLines = allPossibleSmudgedSymLines(rowOrColumn);
        return allPossibleSmudgedSymLines.stream()
                .flatMap(List::stream)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting())).entrySet().stream()
                .filter(e -> e.getValue() == allPossibleSmudgedSymLines.size() - 1)
                .map(Entry::getKey)
                .findFirst().orElse(-1l);
    }

    private List<List<Long>> allPossibleSmudgedSymLines(String rowOrColumn) {
        List<List<Long>> allSymLines = new ArrayList<>();
        IntFunction<String> getter;
        int max;
        long symlineToIgnore;
        if (rowOrColumn.equals("row")) {
            getter = this::getRow;
            max = content.size();
            symlineToIgnore = oldVertSymLine;
        } else {
            getter = this::getColumn;
            max = content.get(0).length();
            symlineToIgnore = oldHorizSymLine;
        }
        for (int i = 0; i < max; i++) {
            List<Long> symLines = symLinePositions(getter.apply(i));
            symLines.remove(symlineToIgnore);
            allSymLines.add(symLines);
        }
        return allSymLines;
    }

    private List<Long> symLinePositions(String input) {
        List<Long> symLinePosittions = new ArrayList<>();
        int i = input.length();
        if (input.length() % 2 != 0) {
            i--;
        }
        for (; i > 0; i -= 2) {
            if (isSymetric(input.substring(0, i))) {
                symLinePosittions.add((long) i / 2);
            }
            if (isSymetric(input.substring(input.length() - i, input.length()))) {
                symLinePosittions.add((long) input.length() - i / 2);
            }
        }
        return symLinePosittions;
    }

    private String getRow(int i) {
        return content.get(i);
    }

    private String getColumn(int i) {
        StringBuilder builder = new StringBuilder();
        for (int j = 0; j < content.size(); j++) {
            builder.append(content.get(j).charAt(i));
        }
        return builder.toString();
    }

    private boolean isSymetric(String input) {
        if (input.length() % 2 != 0) {
            return false;
        }
        for (int i = 0; i < input.length() / 2; i++) {
            if (input.charAt(i) != input.charAt(input.length() - 1 - i))
                return false;
        }
        return true;
    }

}
