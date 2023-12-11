package de.dknuth.adventofcode23.day10;

import java.util.List;

public class PipeMaze {
    private char[][] pipeMatrix;

    PipeMaze(List<String> inputs) {
        this.pipeMatrix = new char[inputs.size()][inputs.get(0).length()];
        for (int i = 0; i < inputs.size(); i++) {
            for (int j = 0; j < inputs.get(i).length(); j++) {
                pipeMatrix[i][j] = inputs.get(i).charAt(j);
            }
        }
    }

    int[] findStart() {
        int i = 0;
        int j = 0;
        for (i = 0; i < pipeMatrix.length; i++) {
            for (j = 0; j < pipeMatrix[i].length; j++) {
                if (pipeMatrix[i][j] == 'S') {
                    return new int[] { i, j };
                }
            }
        }
        return new int[] { 0, 0 };
    }

    private int[] findPipesFromStart(boolean findFirst) {
        int[] start = findStart();
        char up = start[0] - 1 < 0 ? '.' : pipeMatrix[start[0] - 1][start[1]];
        char down = start[0] + 1 >= pipeMatrix.length ? '.' : pipeMatrix[start[0] + 1][start[1]];
        char left = start[1] - 1 < 0 ? '.' : pipeMatrix[start[0]][start[1] - 1];
        char right = start[1] + 1 >= pipeMatrix[0].length ? '.' : pipeMatrix[start[0]][start[1] + 1];
        int count = 0;

        if (up == '|' || up == '7' || up == 'F') {
            if (findFirst) {
                return new int[] { start[0] - 1, start[1] };
            } else {
                count++;
            }
        }
        if (down == '|' || down == 'J' || down == 'L') {
            if (findFirst || count > 0) {
                return new int[] { start[0] + 1, start[1] };
            } else {
                count++;
            }
        }
        if (left == '-' || left == 'L' || left == 'F' && (findFirst || count > 0)) {
            return new int[] { start[0], start[1] - 1 };

        }
        if (right == '-' || right == '7' || right == 'J') {
            return new int[] { start[0], start[1] + 1 };
        }
        throw new IllegalArgumentException("Proper start not found");
    }

    int[] findFirstPipeFromStart() {
        return findPipesFromStart(true);
    }

    int[] findSeconddPipeFromStart() {
        return findPipesFromStart(false);
    }

    int[] walk(int[] relativePrev, int[] current) {
        int i = 0;
        int j = 0;

        switch (pipeMatrix[current[0]][current[1]]) {
            case '|' -> {
                if (relativePrev[0] < 0) {
                    i = current[0] + 1;
                } else {
                    i = current[0] - 1;
                }
                j = current[1];
            }
            case '-' -> {
                if (relativePrev[1] < 0) {
                    j = current[1] + 1;
                } else {
                    j = current[1] - 1;
                }
                i = current[0];
            }
            case 'L' -> {
                if (relativePrev[0] < 0) {
                    i = current[0];
                    j = current[1] + 1;
                } else {
                    i = current[0] - 1;
                    j = current[1];
                }
            }
            case 'J' -> {
                if (relativePrev[0] < 0) {
                    i = current[0];
                    j = current[1] - 1;
                } else {
                    i = current[0] - 1;
                    j = current[1];
                }
            }
            case '7' -> {
                if (relativePrev[0] > 0) {
                    i = current[0];
                    j = current[1] - 1;
                } else {
                    i = current[0] + 1;
                    j = current[1];
                }
            }
            case 'F' -> {
                if (relativePrev[0] > 0) {
                    i = current[0];
                    j = current[1] + 1;
                } else {
                    i = current[0] + 1;
                    j = current[1];
                }
            }
            default -> throw new IllegalArgumentException();
        }
        return new int[] { i, j };
    }
}
