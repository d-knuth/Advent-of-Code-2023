package de.dknuth.adventofcode23.day14;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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

    List<String> moveAllRocksNorth() {
        List<String> columns = new ArrayList<>();
        for (int i = 0; i < content.get(0).length(); i++) {
            String column = getColumn(i);
            List<String> sections = divide(column);
            for (int j = 0; j < sections.size(); j++) {
                sections.set(j, moveRocksNorthInSection(sections.get(j)));
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
}
