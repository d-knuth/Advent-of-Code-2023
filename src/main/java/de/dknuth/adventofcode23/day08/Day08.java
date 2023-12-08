package de.dknuth.adventofcode23.day08;

import java.util.List;

import de.dknuth.adventofcode23.day.Day;

public class Day08 implements Day {

    @Override
    public String solutionToPart1(List<String> inputs) {
        List<Node> nodes = generateNodes(inputs);
        char[] directions = getDirections(inputs);
        int i = 0;
        boolean found = false;
        Node currentNode = nodes.get(0);
        while (!found) {
            char direction = directions[i % directions.length];
            currentNode = walk(currentNode, nodes, direction);
            i++;
            if (currentNode.getName().equals("ZZZ")) {
                found = true;
            }
        }

        return String.valueOf(i);
    }

    @Override
    public String solutionToPart2(List<String> inputs) {
        return "";
    }

    private List<Node> generateNodes(List<String> inputs) {
        return inputs.stream().skip(2).map(this::generateNode).toList();
    }

    private Node generateNode(String input) {
        return new Node(getName(input), getLeft(input), getRight(input));
    }

    private String getName(String input) {
        return input.substring(0, 4).trim();
    }

    private String getLeft(String input) {
        return input.substring(7, 10).trim();
    }

    private String getRight(String input) {
        return input.substring(12, 15).trim();
    }

    private char[] getDirections(List<String> inputs) {
        return inputs.get(0).trim().toCharArray();
    }

    private Node walk(Node origin, List<Node> nodes, char direction) {
        String nextNodeName = direction == 'L' ? origin.getLeft() : origin.getRight();
        return nodes.stream().parallel().filter(n -> n.getName().equals(nextNodeName)).findFirst().orElseThrow();
    }

}
