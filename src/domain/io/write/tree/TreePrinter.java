package domain.io.write.tree;

import domain.general.Node;

import java.io.BufferedWriter;
import java.io.IOException;

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
        if (hasChild(node)) {
            while (untilLastChild(node)) {
                var child = getChild(node);
                printTreeRecursively(child, childPrefix + HAS_CHILD_CHAR, childPrefix + "│   ");
            }
            printTreeRecursively(getChild(node), childPrefix + LEAF_CHAR, childPrefix + "   ");
        }
    }

    private static boolean untilLastChild(Node node) {
        return node.getNeighborQueue().size() > 1;
    }

    private static boolean hasChild(Node node) {
        return !node.getNeighborQueue().isEmpty();
    }

    private static Node getChild(Node node) {
        return node.getNeighborQueue().poll();
    }
}
