package de.dknuth.adventofcode23.day03;

import java.util.ArrayList;
import java.util.List;

public class EngineSchematic {

    private List<String> schematic;

    EngineSchematic(List<String> schematic) {

        this.schematic = schematic;

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
