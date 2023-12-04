package de.dknuth.adventofcode23.day03;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class EngineSchematic {

    private char[][] engineSchematicMatrix;

    private List<String> schematic;

    EngineSchematic(List<String> schematic) {
        this.engineSchematicMatrix = new char[schematic.size()][schematic.get(0).length()];
        this.schematic = schematic;
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

    List<Integer> gearRatios() {
        List<Integer> gearRatios = new ArrayList<>();
        List<Coordinate> usedCoordinates = new ArrayList<>();
        for (Coordinate gear : coordinatesOfGears()) {
            List<Integer> factors = new ArrayList<>();
            int i = gear.i;
            int j = gear.j;
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
                    String factor = "";
                    for (int l = numberStartIndex; l <= numberEndIndex; l++) {
                        factor = factor + engineSchematicMatrix[fieldWithDigit.i][l];
                    }
                    factors.add(Integer.parseInt(factor));
                }
            }
            if (factors.size() == 2) {
                gearRatios.add(factors.get(0) * factors.get(1));
            }
        }
        return gearRatios;
    }

    private List<Coordinate> coordinatesOfGears() {
        return filterSchematicBy(c -> c == '*');
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

    Integer sumOfPartnumbers() {
        List<Integer> allPartNumbers = new ArrayList<>();
        for (int i = 0; i < schematic.size(); i++) {
            allPartNumbers.addAll(getPartNumbersIn(i));
        }

        return allPartNumbers.stream().reduce(0, Integer::sum);
    }

    private List<Integer> getPartNumbersIn(int lineIndex) {
        List<Integer> partNumbers = new ArrayList<>();
        String line = schematic.get(lineIndex);
        String lineAbove = lineIndex == 0 ? "." : schematic.get(lineIndex - 1);
        String lineBelow = lineIndex == schematic.size() - 1 ? "." : schematic.get(lineIndex + 1);
        List<Integer> possibleIndexesOfPartnumber = possibleIndexesOfPartnumber(lineAbove, line, lineBelow);
        List<Integer> usedIndexes = new ArrayList<>();
        for (Integer index : possibleIndexesOfPartnumber) {
            if (Character.isDigit(line.charAt(index)) && !usedIndexes.contains(index)) {
                usedIndexes.add(index);
                int numberStartIndex = index;
                int numberEndIndex = index;
                for (int i = index; Character.isDigit(line.charAt(i)); i--) {
                    numberStartIndex = i;
                    usedIndexes.add(i);
                    if (i == 0) {
                        break;
                    }
                }
                for (int i = index; Character.isDigit(line.charAt(i)); i++) {
                    numberEndIndex = i;
                    usedIndexes.add(i);
                    if (i == line.length() - 1) {
                        break;
                    }
                }
                partNumbers.add(Integer.parseInt(line.substring(numberStartIndex, numberEndIndex + 1)));
            }
        }
        return partNumbers;
    }

    private List<Integer> possibleIndexesOfPartnumber(String lineAbove, String line, String lineBelow) {
        List<Integer> possibleIndexesOfPartnumber = new ArrayList<>();
        possibleIndexesOfPartnumber.addAll(indexesOfSymbols(lineAbove));
        possibleIndexesOfPartnumber.addAll(addToEach(indexesOfSymbols(lineAbove), 1));
        possibleIndexesOfPartnumber.addAll(addToEach(indexesOfSymbols(lineAbove), -1));

        possibleIndexesOfPartnumber.addAll(addToEach(indexesOfSymbols(line), 1));
        possibleIndexesOfPartnumber.addAll(addToEach(indexesOfSymbols(line), -1));

        possibleIndexesOfPartnumber.addAll(indexesOfSymbols(lineBelow));
        possibleIndexesOfPartnumber.addAll(addToEach(indexesOfSymbols(lineBelow), 1));
        possibleIndexesOfPartnumber.addAll(addToEach(indexesOfSymbols(lineBelow), -1));
        possibleIndexesOfPartnumber = possibleIndexesOfPartnumber.stream().distinct().toList();

        return possibleIndexesOfPartnumber;
    }

    private List<Integer> indexesOfSymbols(String line) {
        List<Integer> indexesOfSymbols = new ArrayList<>();
        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            if (!Character.isDigit(c) && c != '.') {
                indexesOfSymbols.add(i);
            }
        }
        return indexesOfSymbols;
    }

    private List<Integer> addToEach(List<Integer> integerList, int n) {
        return integerList.stream().map(i -> i + n).toList();
    }
}
