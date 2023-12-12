package de.dknuth.adventofcode23.day11;

import java.util.ArrayList;
import java.util.List;

public class Universe {

    private char[][] image;

    private List<Long> expandableRows;
    private List<Long> expandableColumns;

    Universe(List<String> inputs) {
        this.expandableColumns = expandableColumns(inputs);
        this.expandableRows = expandableRows(inputs);
        this.image = new char[inputs.size()][inputs.get(0).length()];
        for (int i = 0; i < inputs.size(); i++) {
            for (int j = 0; j < inputs.get(i).length(); j++) {
                image[i][j] = inputs.get(i).charAt(j);
            }
        }
    }

    private List<Long> expandableRows(List<String> inputs) {
        List<Long> rows = new ArrayList<>();
        for (int i = 0; i < inputs.size(); i++) {
            if (inputs.get(i).chars().distinct().count() == 1) {
                rows.add((long) i);
            }
        }
        return rows;
    }

    private List<Long> expandableColumns(List<String> inputs) {
        List<Long> columns = new ArrayList<>();
        for (int index = 0; index < inputs.get(0).length(); index++) {
            int i = index;
            if (inputs.stream().map(s -> s.charAt(i)).distinct().count() == 1) {
                columns.add((long) i);
            }
        }
        return columns;
    }

    long calcDistanceSum(long expansionFactor) {
        long totalDistances = 0;
        List<int[]> galaxies = findAllGalaxies();
        for (int i = 0; i < galaxies.size(); i++) {
            for (int j = i + 1; j < galaxies.size(); j++) {
                totalDistances += calcDistanceBetweenGalaxies(galaxies.get(i), galaxies.get(j), expansionFactor);
            }
        }
        return totalDistances;
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

    private long calcDistanceBetweenGalaxies(int[] g1, int[] g2, long expansionFactor) {
        long expansionDistance = 0;
        long start = g1[0] < g2[0] ? g1[0] : g2[0];
        long end = g1[0] > g2[0] ? g1[0] : g2[0];

        for (long i = start; i < end; i++) {
            if (expandableRows.contains(i)) {
                expansionDistance += expansionFactor - 1;
            }
        }
        start = g1[1] < g2[1] ? g1[1] : g2[1];
        end = g1[1] > g2[1] ? g1[1] : g2[1];
        for (long i = start; i < end; i++) {
            if (expandableColumns.contains(i)) {
                expansionDistance += expansionFactor - 1;
            }
        }
        return expansionDistance + Math.abs(g1[0] - g2[0]) + Math.abs(g1[1] - g2[1]);
    }
}
