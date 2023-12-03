package de.dknuth.adventofcode23.day02;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Game {

    private int id;

    private List<Map<String, Integer>> cubeSets;

    Game(String input) {
        this.id = gameNumber(input);
        this.cubeSets = mapListOf(input);
    }

    int getId() {
        return id;
    }

    private Integer gameNumber(String input) {
        return numberOutOf(input.substring(5, input.indexOf(":")));
    }

    private List<Map<String, Integer>> mapListOf(String input) {
        String[] colors = { "red", "green", "blue" };
        List<Map<String, Integer>> mapListOfCubeSets = new ArrayList<>();
        String[] stringCubeSets = input.substring(input.indexOf(":") + 1).split(";");
        for (String cubeSet : stringCubeSets) {
            String[] numbersWithColors = cubeSet.split(",");
            Map<String, Integer> cubeSetMap = new HashMap<>();
            for (String numberWithColor : numbersWithColors) {
                for (String color : colors) {
                    if (numberWithColor.contains(color)) {
                        cubeSetMap.put(color, numberOutOf(numberWithColor));
                    }
                }
            }
            mapListOfCubeSets.add(cubeSetMap);
        }
        return mapListOfCubeSets;
    }

    private Integer numberOutOf(String input) {
        return Integer.parseInt(input.replaceAll("\\D", ""));
    }

    boolean isGamePossible(int maxRed, int maxGreen, int maxBlue) {
        long impossibleCubeSetCount = this.cubeSets.stream()
                .filter(cs -> isImpossibleCubeSet(cs, maxRed, maxGreen, maxBlue)).count();
        return impossibleCubeSetCount == 0;
    }

    private boolean isImpossibleCubeSet(Map<String, Integer> cubeSet, int maxRed, int maxGreen, int maxBlue) {
        return (cubeSet.get("red") != null && cubeSet.get("red") > maxRed)
                || (cubeSet.get("green") != null && cubeSet.get("green") > maxGreen)
                || (cubeSet.get("blue") != null && cubeSet.get("blue") > maxBlue);
    }

    Integer powerOfFewestNumberForPossibleGame() {
        return maxOfColor("red") * maxOfColor("green") * maxOfColor("blue");
    }

    private int maxOfColor(String color) {
        int maxColor = 0;
        for (Map<String, Integer> cubeSet : this.cubeSets) {
            if (cubeSet.get(color) != null && cubeSet.get(color) > maxColor) {
                maxColor = cubeSet.get(color);
            }
        }
        return maxColor;
    }

}
