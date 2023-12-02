package de.dknuth.adventofcode23.day02;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.dknuth.adventofcode23.day.Day;

public class Day02 implements Day {

    @Override
    public String solutionToPart1(List<String> input) {
        return input.stream()
                .filter(g -> isGamePossible(g, 12, 13, 14))
                .map(g -> gameNumber(g))
                .reduce(0, Integer::sum)
                .toString();
    }

    @Override
    public String solutionToPart2(List<String> input) {
        return input.stream()
                .map(g -> mapListOf(g))
                .map(g -> powerOfFewestNumberForPossibleGame(g))
                .reduce(0, Integer::sum)
                .toString();
    }

    private Integer gameNumber(String input) {
        String number = input.substring(5, input.indexOf(":"));
        return Integer.parseInt(number);
    }

    private boolean isGamePossible(String game, int maxRed, int maxGreen, int maxBlue) {
        List<Map<String, Integer>> cubeSets = mapListOf(game);
        long impossibleCubeSetCount = cubeSets.stream()
                .filter(cs -> isImpossibleCubeSet(cs, maxRed, maxGreen, maxBlue)).count();
        return impossibleCubeSetCount == 0;
    }

    private boolean isImpossibleCubeSet(Map<String, Integer> cubeSet, int maxRed, int maxGreen, int maxBlue) {
        return (cubeSet.get("red") != null && cubeSet.get("red") > maxRed)
                || (cubeSet.get("green") != null && cubeSet.get("green") > maxGreen)
                || (cubeSet.get("blue") != null && cubeSet.get("blue") > maxBlue);
    }

    private List<Map<String, Integer>> mapListOf(String game) {
        String[] colors = { "red", "green", "blue" };
        List<Map<String, Integer>> mapListOfGame = new ArrayList<>();
        String[] cubeSets = game.substring(game.indexOf(":") + 1).split(";");
        for (String cubeSet : cubeSets) {
            String[] numbersWithColors = cubeSet.split(",");
            Map<String, Integer> cubeSetMap = new HashMap<>();
            for (String numberWithColor : numbersWithColors) {
                for (String color : colors) {
                    if (numberWithColor.contains(color)) {
                        cubeSetMap.put(color, Integer.parseInt(numberWithColor.replace(color, "").trim()));
                    }
                }
            }
            mapListOfGame.add(cubeSetMap);
        }
        return mapListOfGame;
    }

    private Integer powerOfFewestNumberForPossibleGame(List<Map<String, Integer>> game) {
        int maxRed = maxOfColor(game, "red");
        int maxGreen = maxOfColor(game, "green");
        int maxBlue = maxOfColor(game, "blue");

        return maxRed * maxGreen * maxBlue;
    }

    private int maxOfColor(List<Map<String, Integer>> game, String color) {
        int maxColor = 0;
        for (Map<String, Integer> cubeSet : game) {
            if (cubeSet.get(color) != null && cubeSet.get(color) > maxColor) {
                maxColor = cubeSet.get(color);
            }
        }
        return maxColor;
    }

}
