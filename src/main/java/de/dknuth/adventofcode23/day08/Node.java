package de.dknuth.adventofcode23.day08;

public class Node {
    private String name;
    private String leftName;
    private String rightName;
    private Node left;
    private Node right;

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public Node(String name, String left, String right) {
        this.name = name;
        this.leftName = left;
        this.rightName = right;
    }

    public String getName() {
        return name;
    }

    public String getLeftName() {
        return leftName;
    }

    public String getRightName() {
        return rightName;
    }

    public Node walk(char direction) {
        return direction == 'L' ? getLeft() : getRight();
    }
}
