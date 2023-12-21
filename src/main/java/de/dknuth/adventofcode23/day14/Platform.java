package de.dknuth.adventofcode23.day14;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

public class Platform {

    private List<String> content;

    public List<String> getContent() {
        return content;
    }

    public Platform(List<String> content) {
        this.content = content;
    }

    private String getColumn(int i) {
        StringBuilder builder = new StringBuilder();
        for (int j = 0; j < content.size(); j++) {
            builder.append(content.get(j).charAt(i));
        }
        return builder.toString();
    }

    private String getRow(int i) {
        return content.get(i);
    }

    long calcWeightOfRow(int i) {
        return i * getContent().get(getContent().size() - i).chars().filter(c -> (char) c == 'O').count();
    }

    List<String> performCycle(long times) {
        List<List<String>> oldContents = new ArrayList<>();
        oldContents.add(content);
        for (long i = 0; i < times; i++) {
            moveAllRocksNorth();
            moveAllRocksWest();
            moveAllRocksSouth();
            moveAllRocksEast();
            if (oldContents.contains(content)) {
                long indexOfRepetitionStart = oldContents.indexOf(content);
                long repetitionSize = i + 1 - indexOfRepetitionStart;
                long stepsToGoAfterRepetitionStart = (times - indexOfRepetitionStart) % repetitionSize;
                content = oldContents.get((int) (indexOfRepetitionStart + stepsToGoAfterRepetitionStart));
                return oldContents.get((int) (indexOfRepetitionStart + stepsToGoAfterRepetitionStart));
            }
            oldContents.add(content);
        }
        return content;
    }

    List<String> moveAllRocksNorth() {
        return moveAllRocksNorthOrSouth("north");
    }

    List<String> moveAllRocksSouth() {
        return moveAllRocksNorthOrSouth("south");
    }

    private List<String> moveAllRocksNorthOrSouth(String northOrSouth) {
        UnaryOperator<String> move;
        if (northOrSouth.equals("north")) {
            move = this::moveRocksNorthInSection;
        } else {
            move = this::moveRocksSouthInSection;
        }

        List<String> columns = new ArrayList<>();
        for (int i = 0; i < content.get(0).length(); i++) {
            String column = getColumn(i);
            List<String> sections = divide(column);
            for (int j = 0; j < sections.size(); j++) {
                sections.set(j, move.apply(sections.get(j)));
            }
            columns.add(combine(sections));
        }
        List<String> newContent = new ArrayList<>();
        for (int j = 0; j < columns.get(0).length(); j++) {
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < columns.size(); i++) {
                builder.append(columns.get(i).charAt(j));
            }
            newContent.add(builder.toString());
        }
        this.content = newContent;
        return columns;
    }

    List<String> moveAllRocksEast() {
        return moveAllRocksEastOrWest("east");
    }

    List<String> moveAllRocksWest() {
        return moveAllRocksEastOrWest("west");
    }

    private List<String> moveAllRocksEastOrWest(String eastOrWest) {
        UnaryOperator<String> move;
        if (eastOrWest.equals("east")) {
            move = this::moveRocksSouthInSection;
        } else {
            move = this::moveRocksNorthInSection;
        }

        List<String> newContent = new ArrayList<>();
        for (String line : content) {
            List<String> sections = divide(line);
            for (int j = 0; j < sections.size(); j++) {
                sections.set(j, move.apply(sections.get(j)));
            }
            newContent.add(combine(sections));
        }
        this.content = newContent;
        return newContent;
    }

    private List<String> divide(String input) {
        return Arrays.asList(input
                .replace("#", "-#-")
                .split("#")).stream()
                .map(s -> s.replace("-", ""))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    private String combine(List<String> sections) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < sections.size() - 1; i++) {
            builder.append(sections.get(i)).append("#");
        }
        builder.append(sections.get(sections.size() - 1));
        return builder.toString();
    }

    private String moveRocksNorthInSection(String input) {
        int rockCount = (int) input.chars().filter(c -> (char) c == 'O').count();
        StringBuilder builder = new StringBuilder("O".repeat(rockCount)).append(".".repeat(input.length() - rockCount));
        return builder.toString();
    }

    private String moveRocksSouthInSection(String input) {
        int rockCount = (int) input.chars().filter(c -> (char) c == 'O').count();
        StringBuilder builder = new StringBuilder(".".repeat(input.length() - rockCount)).append("O".repeat(rockCount));
        return builder.toString();
    }

}
