package de.dknuth.adventofcode23.day08;

public class Node {
    private String name;
    private String left;
    private String right;

    public Node(String name, String left, String right) {
        this.name = name;
        this.left = left;
        this.right = right;
    }

    public String getName() {
        return name;
    }

    public String getLeft() {
        return left;
    }

    public String getRight() {
        return right;
    }

}
