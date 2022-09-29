package domain.general;

import java.util.LinkedList;
import java.util.Queue;

public class Node {
    private final String value;
    private final Queue<Node> nodes;

    public Node(String value) {
        this.value = value;
        this.nodes = new LinkedList<>();
    }

    public void addNeighbor(Node node) {
        if (!nodes.contains(node)) {
            nodes.add(node);
            node.addNeighbor(this);
        }
    }

    public void addSuccessor(Node node) {
        if (!nodes.contains(node)) {
            nodes.add(node);
        }
    }

    public Queue<Node> getBorderedQueue() {
        return nodes;
    }

    public String getValue() {
        return value;
    }
}
