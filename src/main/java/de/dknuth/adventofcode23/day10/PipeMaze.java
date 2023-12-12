package de.dknuth.adventofcode23.day10;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
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

    List<int[]> getAllPipesOfLoop() {
        List<int[]> allPipesOfLoop = new ArrayList<>();
        int[] start = findStart();
        int[] current = findPipeFromStart();
        allPipesOfLoop.add(current);
        int[] previous = start;
        while (!Arrays.equals(current, start)) {
            int[] next = walk(previous, current);
            allPipesOfLoop.add(current);
            previous = current;
            current = next;
        }
        return allPipesOfLoop;
    }

    private int[] findStart() {
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

    private int[] findPipeFromStart() {
        int[] start = findStart();
        char up = start[0] - 1 < 0 ? '.' : pipeMatrix[start[0] - 1][start[1]];
        char down = start[0] + 1 >= pipeMatrix.length ? '.' : pipeMatrix[start[0] + 1][start[1]];
        char left = start[1] - 1 < 0 ? '.' : pipeMatrix[start[0]][start[1] - 1];
        char right = start[1] + 1 >= pipeMatrix[0].length ? '.' : pipeMatrix[start[0]][start[1] + 1];

        if (up == '|' || up == '7' || up == 'F') {
            return new int[] { start[0] - 1, start[1] };
        }
        if (down == '|' || down == 'J' || down == 'L') {

            return new int[] { start[0] + 1, start[1] };
            }
            if (left == '-' || left == 'L' || left == 'F') {
                return new int[] { start[0], start[1] - 1 };
        }
        if (right == '-' || right == '7' || right == 'J') {
            return new int[] { start[0], start[1] + 1 };
        }
        throw new IllegalArgumentException("Proper start not found");
    }

    private int[] walk(int[] previous, int[] current) {
        int i = 0;
        int j = 0;

        int[] relativePreviousPosition = new int[] { previous[0] - current[0], previous[1] - current[1] };

        switch (pipeMatrix[current[0]][current[1]]) {
            case '|' -> {
                if (relativePreviousPosition[0] < 0) {
                    i = current[0] + 1;
                } else {
                    i = current[0] - 1;
                }
                j = current[1];
            }
            case '-' -> {
                if (relativePreviousPosition[1] < 0) {
                    j = current[1] + 1;
                } else {
                    j = current[1] - 1;
                }
                i = current[0];
            }
            case 'L' -> {
                if (relativePreviousPosition[0] < 0) {
                    i = current[0];
                    j = current[1] + 1;
                } else {
                    i = current[0] - 1;
                    j = current[1];
                }
            }
            case 'J' -> {
                if (relativePreviousPosition[0] < 0) {
                    i = current[0];
                    j = current[1] - 1;
                } else {
                    i = current[0] - 1;
                    j = current[1];
                }
            }
            case '7' -> {
                if (relativePreviousPosition[0] > 0) {
                    i = current[0];
                    j = current[1] - 1;
                } else {
                    i = current[0] + 1;
                    j = current[1];
                }
            }
            case 'F' -> {
                if (relativePreviousPosition[0] > 0) {
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

    long countInsideTiles() {
        long count = 0;
        char[][] blownUpPipeMatrix = blowUpPipeMatrix();
        markConnectedOutsideTiles(blownUpPipeMatrix);
        for (int i = 0; i < blownUpPipeMatrix.length; i++) {
            for (int j = 0; j < blownUpPipeMatrix[i].length; j++) {
                if (blownUpPipeMatrix[i][j] == '.') {
                    count++;
                }
            }
        }
        return count;
    }

    private char[][] blowUpPipeMatrix() {
        removeUnusedPipes();
        char[][] blownUpPipeMatrix = new char[pipeMatrix.length *
                3][pipeMatrix[0].length * 3];
        for (int i = 0; i < pipeMatrix.length; i++) {
            for (int j = 0; j < pipeMatrix[i].length; j++) {
                switch (pipeMatrix[i][j]) {
                    case '|' -> {
                        blownUpPipeMatrix[i * 3][j * 3] = ' ';
                        blownUpPipeMatrix[i * 3 + 1][j * 3] = ' ';
                        blownUpPipeMatrix[i * 3 + 2][j * 3] = ' ';
                        blownUpPipeMatrix[i * 3][j * 3 + 1] = '#';
                        blownUpPipeMatrix[i * 3 + 1][j * 3 + 1] = '#';
                        blownUpPipeMatrix[i * 3 + 2][j * 3 + 1] = '#';
                        blownUpPipeMatrix[i * 3][j * 3 + 2] = ' ';
                        blownUpPipeMatrix[i * 3 + 1][j * 3 + 2] = ' ';
                        blownUpPipeMatrix[i * 3 + 2][j * 3 + 2] = ' ';
                    }
                    case '-' -> {
                        blownUpPipeMatrix[i * 3][j * 3] = ' ';
                        blownUpPipeMatrix[i * 3 + 1][j * 3] = '#';
                        blownUpPipeMatrix[i * 3 + 2][j * 3] = ' ';
                        blownUpPipeMatrix[i * 3][j * 3 + 1] = ' ';
                        blownUpPipeMatrix[i * 3 + 1][j * 3 + 1] = '#';
                        blownUpPipeMatrix[i * 3 + 2][j * 3 + 1] = ' ';
                        blownUpPipeMatrix[i * 3][j * 3 + 2] = ' ';
                        blownUpPipeMatrix[i * 3 + 1][j * 3 + 2] = '#';
                        blownUpPipeMatrix[i * 3 + 2][j * 3 + 2] = ' ';

                    }
                    case 'L' -> {
                        blownUpPipeMatrix[i * 3][j * 3] = ' ';
                        blownUpPipeMatrix[i * 3 + 1][j * 3] = ' ';
                        blownUpPipeMatrix[i * 3 + 2][j * 3] = ' ';
                        blownUpPipeMatrix[i * 3][j * 3 + 1] = '#';
                        blownUpPipeMatrix[i * 3 + 1][j * 3 + 1] = '#';
                        blownUpPipeMatrix[i * 3 + 2][j * 3 + 1] = ' ';
                        blownUpPipeMatrix[i * 3][j * 3 + 2] = ' ';
                        blownUpPipeMatrix[i * 3 + 1][j * 3 + 2] = '#';
                        blownUpPipeMatrix[i * 3 + 2][j * 3 + 2] = ' ';

                    }
                    case 'J' -> {
                        blownUpPipeMatrix[i * 3][j * 3] = ' ';
                        blownUpPipeMatrix[i * 3 + 1][j * 3] = '#';
                        blownUpPipeMatrix[i * 3 + 2][j * 3] = ' ';
                        blownUpPipeMatrix[i * 3][j * 3 + 1] = '#';
                        blownUpPipeMatrix[i * 3 + 1][j * 3 + 1] = '#';
                        blownUpPipeMatrix[i * 3 + 2][j * 3 + 1] = ' ';
                        blownUpPipeMatrix[i * 3][j * 3 + 2] = ' ';
                        blownUpPipeMatrix[i * 3 + 1][j * 3 + 2] = ' ';
                        blownUpPipeMatrix[i * 3 + 2][j * 3 + 2] = ' ';
                    }
                    case '7' -> {
                        blownUpPipeMatrix[i * 3][j * 3] = ' ';
                        blownUpPipeMatrix[i * 3 + 1][j * 3] = '#';
                        blownUpPipeMatrix[i * 3 + 2][j * 3] = ' ';
                        blownUpPipeMatrix[i * 3][j * 3 + 1] = ' ';
                        blownUpPipeMatrix[i * 3 + 1][j * 3 + 1] = '#';
                        blownUpPipeMatrix[i * 3 + 2][j * 3 + 1] = '#';
                        blownUpPipeMatrix[i * 3][j * 3 + 2] = ' ';
                        blownUpPipeMatrix[i * 3 + 1][j * 3 + 2] = ' ';
                        blownUpPipeMatrix[i * 3 + 2][j * 3 + 2] = ' ';
                    }
                    case 'F' -> {
                        blownUpPipeMatrix[i * 3][j * 3] = ' ';
                        blownUpPipeMatrix[i * 3 + 1][j * 3] = ' ';
                        blownUpPipeMatrix[i * 3 + 2][j * 3] = ' ';
                        blownUpPipeMatrix[i * 3][j * 3 + 1] = ' ';
                        blownUpPipeMatrix[i * 3 + 1][j * 3 + 1] = '#';
                        blownUpPipeMatrix[i * 3 + 2][j * 3 + 1] = '#';
                        blownUpPipeMatrix[i * 3][j * 3 + 2] = ' ';
                        blownUpPipeMatrix[i * 3 + 1][j * 3 + 2] = '#';
                        blownUpPipeMatrix[i * 3 + 2][j * 3 + 2] = ' ';
                    }
                    case 'S' -> {
                        blownUpPipeMatrix[i * 3][j * 3] = '#';
                        blownUpPipeMatrix[i * 3 + 1][j * 3] = '#';
                        blownUpPipeMatrix[i * 3 + 2][j * 3] = '#';
                        blownUpPipeMatrix[i * 3][j * 3 + 1] = '#';
                        blownUpPipeMatrix[i * 3 + 1][j * 3 + 1] = '#';
                        blownUpPipeMatrix[i * 3 + 2][j * 3 + 1] = '#';
                        blownUpPipeMatrix[i * 3][j * 3 + 2] = '#';
                        blownUpPipeMatrix[i * 3 + 1][j * 3 + 2] = '#';
                        blownUpPipeMatrix[i * 3 + 2][j * 3 + 2] = '#';
                    }
                    case '.' -> {
                        blownUpPipeMatrix[i * 3][j * 3] = ' ';
                        blownUpPipeMatrix[i * 3 + 1][j * 3] = ' ';
                        blownUpPipeMatrix[i * 3 + 2][j * 3] = ' ';
                        blownUpPipeMatrix[i * 3][j * 3 + 1] = ' ';
                        blownUpPipeMatrix[i * 3 + 1][j * 3 + 1] = '.';
                        blownUpPipeMatrix[i * 3 + 2][j * 3 + 1] = ' ';
                        blownUpPipeMatrix[i * 3][j * 3 + 2] = ' ';
                        blownUpPipeMatrix[i * 3 + 1][j * 3 + 2] = ' ';
                        blownUpPipeMatrix[i * 3 + 2][j * 3 + 2] = ' ';
                    }
                    case ' ' -> {
                        blownUpPipeMatrix[i * 3][j * 3] = ' ';
                        blownUpPipeMatrix[i * 3 + 1][j * 3] = ' ';
                        blownUpPipeMatrix[i * 3 + 2][j * 3] = ' ';
                        blownUpPipeMatrix[i * 3][j * 3 + 1] = ' ';
                        blownUpPipeMatrix[i * 3 + 1][j * 3 + 1] = ' ';
                        blownUpPipeMatrix[i * 3 + 2][j * 3 + 1] = ' ';
                        blownUpPipeMatrix[i * 3][j * 3 + 2] = ' ';
                        blownUpPipeMatrix[i * 3 + 1][j * 3 + 2] = ' ';
                        blownUpPipeMatrix[i * 3 + 2][j * 3 + 2] = ' ';
                    }
                    default -> throw new IllegalArgumentException();
                }
            }
        }
        return blownUpPipeMatrix;
    }

    private void removeUnusedPipes() {
        List<int[]> allPipesOfLoop = getAllPipesOfLoop();
        for (int i = 0; i < pipeMatrix.length; i++) {
            for (int j = 0; j < pipeMatrix[i].length; j++) {
                int[] current = new int[] { i, j };
                if (!isInList(allPipesOfLoop, current)
                        && (pipeMatrix[i][j] == '|' || pipeMatrix[i][j] == '-' || pipeMatrix[i][j] == '7'
                                || pipeMatrix[i][j] == 'F' || pipeMatrix[i][j] == 'J' || pipeMatrix[i][j] == 'L')) {
                    pipeMatrix[i][j] = '.';
                }
            }
        }
    }

    private boolean isInList(List<int[]> list, int[] element) {
        return list.stream().anyMatch(a -> Arrays.equals(a, element));
    }

    private void markConnectedOutsideTiles(char[][] matrix) {

        Deque<int[]> stack = new ArrayDeque<>();
        int[] start = new int[] { 0, 0 };
        stack.push(start);
        while (stack.size() > 0) {
            int[] current = stack.pop();
            int i = current[0];
            int j = current[1];
            if (i < 0 || j < 0 || i >= matrix.length || j >= matrix[0].length) {
                continue;
            }

            if (matrix[i][j] == '#' || matrix[i][j] == 'O') {
                continue;
            }
            if (matrix[i][j] == ' ' || matrix[i][j] == '.') {
                matrix[i][j] = 'O';
            }

            stack.push(new int[] { i + 1, j });
            stack.push(new int[] { i - 1, j });
            stack.push(new int[] { i, j + 1 });
            stack.push(new int[] { i, j - 1 });
        }
    }
}
