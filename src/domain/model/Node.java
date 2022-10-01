package domain.model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Node implements Cloneable {
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

    public void removeNeighbor(Node node) {
        if (nodes.contains(node)) {
            nodes.remove(node);
            node.removeNeighbor(this);
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

    @Override
    public Node clone() {
        Map<String, Node> clonedNodes = new HashMap<>();
        clonedNodes.put(value, new Node(this.getValue()));
        return clone(clonedNodes);
    }

    public Node clone(Map<String, Node> clonedNodes) {
        Node node = new Node(value);
        clonedNodes.put(value, node);
        for (Node child: nodes) {
            if (clonedNodes.get(child.getValue()) == null) {
                node.addNeighbor(child.clone(clonedNodes));
            } else {
                node.addNeighbor(clonedNodes.get(child.getValue()));
            }
        }
        return node;
    }
}
