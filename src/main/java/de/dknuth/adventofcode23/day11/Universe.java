package de.dknuth.adventofcode23.day11;

import java.util.ArrayList;
import java.util.List;

public class Universe {

    private char[][] image;

    Universe(List<String> inputs) {
        inputs = expand(inputs);
        this.image = new char[inputs.size()][inputs.get(0).length()];
        for (int i = 0; i < inputs.size(); i++) {
            for (int j = 0; j < inputs.get(i).length(); j++) {
                image[i][j] = inputs.get(i).charAt(j);
            }
        }
    }

    private List<String> expand(List<String> inputs) {
        inputs = expandWidth(inputs);
        inputs = expandHeight(inputs);
        return inputs;
    }

    private List<String> expandHeight(List<String> inputs) {
        inputs = new ArrayList<>(inputs);
        for (int i = 0; i < inputs.size(); i++) {
            if (inputs.get(i).chars().distinct().count() == 1) {
                inputs.add(i, ".".repeat(inputs.get(i).length()));
                i++;
            }
        }
        return inputs;
    }

    private List<String> expandWidth(List<String> inputs) {
        for (int index = 0; index < inputs.get(0).length(); index++) {
            int i = index;
            if (inputs.stream().map(s -> s.charAt(i)).distinct().count() == 1) {
                inputs = inputs.stream()
                        .map(s -> new StringBuilder(s.substring(0, i)).append(".").append(s.substring(i))
                                .toString())
                        .toList();
                index++;
            }
        }
        return inputs;
    }

    private List<int[]> findAllGalaxies() {
        List<int[]> galaxies = new ArrayList<>();
        for (int i = 0; i < image.length; i++) {
            for (int j = 0; j < image[i].length; j++) {
                if (image[i][j] == '#') {
                    galaxies.add(new int[] { i, j });
                }
            }
        }
        return galaxies;
    }

    long calcDistances() {
        long totalDistances = 0;
        List<int[]> galaxies = findAllGalaxies();
        for (int i = 0; i < galaxies.size(); i++) {
            for (int j = i + 1; j < galaxies.size(); j++) {
                totalDistances += calcDistanceBetweenGalaxies(galaxies.get(i), galaxies.get(j));
            }
        }
        return totalDistances;
    }

    private long calcDistanceBetweenGalaxies(int[] g1, int[] g2) {
        return Math.abs(g1[0] - g2[0]) + Math.abs(g1[1] - g2[1]);
    }
}
