package de.dknuth.adventofcode23.day03;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class EngineSchematic {

    private char[][] engineSchematicMatrix;

    EngineSchematic(List<String> schematic) {
        this.engineSchematicMatrix = new char[schematic.size()][schematic.get(0).length()];
        for (int i = 0; i < schematic.size(); i++) {
            for (int j = 0; j < schematic.get(i).length(); j++) {
                engineSchematicMatrix[i][j] = schematic.get(i).charAt(j);
            }
        }
    }

    private List<Coordinate> findSurroundingDigits(int i, int j) {
        List<Coordinate> surroundingFieldsWithDigit = new ArrayList<>();
        List<Coordinate> surroundingFields = new ArrayList<>();
        surroundingFields.add(Coordinate.of(i - 1, j - 1));
        surroundingFields.add(Coordinate.of(i - 1, j));
        surroundingFields.add(Coordinate.of(i - 1, j + 1));
        surroundingFields.add(Coordinate.of(i, j - 1));
        surroundingFields.add(Coordinate.of(i, j + 1));
        surroundingFields.add(Coordinate.of(i - 1, j + 1));
        surroundingFields.add(Coordinate.of(i + 1, j - 1));
        surroundingFields.add(Coordinate.of(i + 1, j));
        surroundingFields.add(Coordinate.of(i + 1, j + 1));
        for (Coordinate surroundingField : surroundingFields) {
            if (Character.isDigit(engineSchematicMatrix[surroundingField.i][surroundingField.j])) {
                surroundingFieldsWithDigit.add(surroundingField);
            }
        }
        return surroundingFieldsWithDigit;
    }

    List<Integer> listRequestedNumbers(int part) {
        List<Integer> requestedNumbers = new ArrayList<>();
        List<Coordinate> usedCoordinates = new ArrayList<>();
        List<Coordinate> coordinatesToCheck;
        if (part == 1) {
            coordinatesToCheck = coordinatesOfSymbols();
        } else {
            coordinatesToCheck = coordinatesOfGears();
        }
        for (Coordinate coordinate : coordinatesToCheck) {
            List<Integer> factors = new ArrayList<>();
            int i = coordinate.i;
            int j = coordinate.j;
            List<Coordinate> surroundingFielsdWithDigit = findSurroundingDigits(i, j);
            for (Coordinate fieldWithDigit : surroundingFielsdWithDigit) {
                if (!usedCoordinates.contains(fieldWithDigit)) {
                    int numberStartIndex = fieldWithDigit.j;
                    int numberEndIndex = fieldWithDigit.j;
                    for (int k = numberStartIndex; Character.isDigit(engineSchematicMatrix[fieldWithDigit.i][k]); k--) {
                        numberStartIndex = k;
                        usedCoordinates.add(Coordinate.of(fieldWithDigit.i, k));
                        if (k == 0) {
                            break;
                        }
                    }
                    for (int k = numberEndIndex; Character.isDigit(engineSchematicMatrix[fieldWithDigit.i][k]); k++) {
                        numberEndIndex = k;
                        usedCoordinates.add(Coordinate.of(fieldWithDigit.i, k));
                        if (k == engineSchematicMatrix[1].length - 1) {
                            break;
                        }
                    }
                    String number = "";
                    for (int l = numberStartIndex; l <= numberEndIndex; l++) {
                        number = number + engineSchematicMatrix[fieldWithDigit.i][l];
                    }
                    if (part == 1) {
                        requestedNumbers.add(Integer.parseInt(number));
                    } else if (part == 2) {
                        factors.add(Integer.parseInt(number));
                    }
                }
            }
            if (part == 2 && factors.size() == 2) {
                requestedNumbers.add(factors.get(0) * factors.get(1));
            }
        }
        return requestedNumbers;
    }

    private List<Coordinate> coordinatesOfGears() {
        return filterSchematicBy(c -> c == '*');
    }

    private List<Coordinate> coordinatesOfSymbols() {
        return filterSchematicBy(c -> !Character.isDigit(c) && c != '.');
    }

    private List<Coordinate> filterSchematicBy(Predicate<Character> filter) {
        List<Coordinate> coordinates = new ArrayList<>();
        for (int i = 0; i < engineSchematicMatrix.length; i++) {
            for (int j = 0; j < engineSchematicMatrix[i].length; j++) {
                if (filter.test(engineSchematicMatrix[i][j])) {
                    coordinates.add(Coordinate.of(i, j));
                }
            }
        }
        return coordinates;
    }
}
