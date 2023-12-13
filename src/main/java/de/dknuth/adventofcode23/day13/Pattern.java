package de.dknuth.adventofcode23.day13;

import java.util.ArrayList;
import java.util.List;

public class Pattern {

    private char[][] content;
    private List<String> contentS;

    Pattern(List<String> input) {
        this.content = new char[input.size()][input.get(0).length()];
        for (int i = 0; i < input.size(); i++) {
            for (int j = 0; j < input.get(i).length(); j++) {
                content[i][j] = input.get(i).charAt(j);
            }
        }
        this.contentS = input;
    }

    long vertSymLinePosition() {
        List<Long> possibleSymLinePositions = vertSymLinePositions(contentS.get(0));
        for (int i = 1; i < contentS.size(); i++) {
            possibleSymLinePositions.retainAll(vertSymLinePositions(contentS.get(i)));
            if (possibleSymLinePositions.isEmpty()) {
                return -1;
            }
        }
        return possibleSymLinePositions.get(0);
    }

    private List<Long> vertSymLinePositions(String input) {
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

    private List<Long> horSymLinePositions(String input) {
        List<Long> symLinePosittions = new ArrayList<>();

        return symLinePosittions;
    }

    private boolean isSymetric(char[] input) {
        if (input.length % 2 != 0) {
            return false;
        }
        for (int i = 0; i < input.length / 2; i++) {
            if (input[i] != input[input.length - 1 - i])
                return false;
        }
        return true;
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
