package de.dknuth.adventofcode23.day08;

import java.util.List;

import de.dknuth.adventofcode23.day.Day;

public class Day08 implements Day {

    @Override
    public String solutionToPart1(List<String> inputs) {
        List<Node> nodes = generateNodes(inputs);
        fillNodeList(nodes);
        char[] directions = getDirections(inputs);
        long i = 0;
        boolean found = false;
        Node currentNode = nodes.stream().filter(n -> n.getName().equals("AAA")).findFirst().orElseThrow();
        while (!found) {
            char direction = directions[(int) (i % directions.length)];
            currentNode = currentNode.walk(direction);
            i++;
            if (currentNode.getName().equals("ZZZ")) {
                found = true;
            }
        }
        return String.valueOf(i);
    }

    // @Override
    // public String solutionToPart2(List<String> inputs) {
    // List<Node> nodes = generateNodes(inputs);
    // char[] directions = getDirections(inputs);
    // fillNodeList(nodes, directions);
    // long i = 0;
    // boolean found = false;
    // List<Node> currentNodes = nodes.stream().filter(n ->
    // n.getName().substring(2).equals("A")).toList();

    // while (!found) {
    // char direction = directions[(int) (i % directions.length)];
    // currentNodes = currentNodes.stream().map(n -> n.walk(direction)).toList();
    // i++;
    // if (currentNodes.stream().filter(n ->
    // n.getName().substring(2).equals("Z")).count() == currentNodes
    // .size()) {
    // found = true;
    // }
    // }
    // return String.valueOf(i);
    // }

    private List<Node> generateNodes(List<String> inputs) {
        return inputs.stream().skip(2).map(this::generateNode).toList();
    }

    private Node generateNode(String input) {
        return new Node(getName(input), getLeftName(input), getRightName(input));
    }

    private String getName(String input) {
        return input.substring(0, 4).trim();
    }

    private String getLeftName(String input) {
        return input.substring(7, 10).trim();
    }

    private String getRightName(String input) {
        return input.substring(12, 15).trim();
    }

    private char[] getDirections(List<String> inputs) {
        return inputs.get(0).trim().toCharArray();
    }

    private List<Node> fillNodeList(List<Node> nodeList) {
        for (int i = 0; i < nodeList.size(); i++) {
            if (nodeList.get(i).getLeft() == null) {
                String nextNodeName = nodeList.get(i).getLeftName();
                Node nextNode = nodeList.stream().parallel().filter(n -> n.getName().equals(nextNodeName)).findFirst()
                        .orElseThrow();
                nodeList.get(i).setLeft(nextNode);
            }
            if (nodeList.get(i).getRight() == null) {
                String nextNodeName = nodeList.get(i).getRightName();
                Node nextNode = nodeList.stream().parallel().filter(n -> n.getName().equals(nextNodeName)).findFirst()
                        .orElseThrow();
                nodeList.get(i).setRight(nextNode);
            }
        }
        return nodeList;
    }

    private List<Node> fillNodeList(List<Node> nodeList, char[] directions) {
        this.fillNodeList(nodeList);
        List<Node> aNodes = nodeList.stream().filter(n -> n.getName().substring(2).equals("A")).toList();
        List<Node> zNodes = nodeList.stream().filter(n -> n.getName().substring(2).equals("Z")).toList();
        for (int i = 0; i < aNodes.size(); i++) {
            long j = 0;
            boolean found = false;
            Node currentNode = aNodes.get(i);
            while (!found) {
                char direction = directions[(int) (j % directions.length)];
                currentNode = currentNode.walk(direction);
                j++;
                if (currentNode.getName().substring(2).equals("Z")) {
                    found = true;
                }
            }
            aNodes.get(i).setStepsToNextZ(j);
            aNodes.get(i).setNextZ(currentNode);
        }
        for (int i = 0; i < aNodes.size(); i++) {
            long j = 0;
            boolean found = false;
            Node currentNode = zNodes.get(i);
            while (!found) {
                char direction = directions[(int) (j % directions.length)];
                currentNode = currentNode.walk(direction);
                j++;
                if (currentNode.getName().substring(2).equals("Z")) {
                    found = true;
                }
            }
            zNodes.get(i).setStepsToNextZ(j);
            zNodes.get(i).setNextZ(currentNode);
        }
        return nodeList;
    }
}
