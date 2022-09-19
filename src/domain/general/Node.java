package domain.general;

import java.util.*;

public class Node {
    private final String value;
    private final Queue<Node> nodes;

    public Node(String value) {
        this.value = value;
        this.nodes = new LinkedList<>();
    }

    public void addSuccessor(Node node) {
        nodes.add(node);
    }

    public Queue<Node> getSuccessorsQueue() {
        return nodes;
    }

    public String getValue() {
        return value;
    }
}
