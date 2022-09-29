package domain.general;

import java.util.LinkedList;
import java.util.Queue;

public class Node {
    private final String value;
    private final LinkedList<Node> nodes;

    public Node(String value) {
        this.value = value;
        this.nodes = new LinkedList<>();
    }

    // Undirected edges
    public void addNeighbor(Node node) {
        if (edgeNotExists(node)) {
            nodes.add(node);
            node.addNeighbor(this);
        }
    }

    // Directed edges
    public void addSuccessor(Node node) {
        if (edgeNotExists(node)) {
            nodes.add(node);
        }
    }

    private boolean edgeNotExists(Node node) {
        return !nodes.contains(node);
    }

    public LinkedList<Node> getNeighborQueue() {
        return nodes;
    }

    public String getValue() {
        return value;
    }
}
