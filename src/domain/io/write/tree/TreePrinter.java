package domain.io.write.tree;

import domain.model.Node;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.LinkedList;

public abstract class TreePrinter {
    protected final BufferedWriter writer;
    private final static String HAS_CHILD_CHAR = "├──";
    private final static String LEAF_CHAR = "└──";

    protected TreePrinter(BufferedWriter writer) {
        this.writer = writer;
    }

    public void printTree(Node node) throws IOException {
        printTreeRecursively(node, "", "");
        writer.flush();
    }

    private void printTreeRecursively(Node node, String prefix, String childPrefix) throws IOException {
        writer.append(prefix)
                .append(node.getValue())
                .append("\n");

        LinkedList<Node> neighborQueue = node.getNeighborQueue();

        if (hasChild(node)) {
            for (int i = 0; i < neighborQueue.size() - 1; i++) {
                Node child = neighborQueue.get(i);
                printTreeRecursively(child, childPrefix + HAS_CHILD_CHAR, childPrefix + "│   ");
            }
            printTreeRecursively(neighborQueue.getLast(), childPrefix + LEAF_CHAR, childPrefix + "   ");
        }
    }

    private static boolean hasChild(Node node) {
        return !node.getNeighborQueue().isEmpty();
    }
}
