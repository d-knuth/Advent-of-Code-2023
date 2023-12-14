package de.dknuth.adventofcode23.day13;

import java.util.ArrayList;
import java.util.List;
import java.util.function.IntFunction;

public class Pattern {

    private List<String> content;

    Pattern(List<String> input) {
        this.content = input;
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
