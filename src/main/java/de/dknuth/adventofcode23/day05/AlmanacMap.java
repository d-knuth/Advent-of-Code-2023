package de.dknuth.adventofcode23.day05;

import java.util.Arrays;
import java.util.List;

public class AlmanacMap {

    private List<List<Long>> destinationSourceRangeList;

    public void init(List<String> inputs) {
        destinationSourceRangeList = inputs.stream().map(this::destinationSourceRange).toList();
    }

    private List<Long> destinationSourceRange(String input) {
        return Arrays.asList(input.replace("  ", " ")
                .split(" ")).stream()
                .map(Long::parseLong).toList();
    }

    public long get(long source) {
        for (List<Long> destinationSourceRange : destinationSourceRangeList) {
            if (source >= destinationSourceRange.get(1)
                    && source < destinationSourceRange.get(1) + destinationSourceRange.get(2)) {
                return destinationSourceRange.get(0) + (source - destinationSourceRange.get(1));
            }
        }
        return source;
    }
}
