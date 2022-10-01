package domain.io.write.tree;

import domain.model.Node;

import java.io.BufferedWriter;
import java.io.IOException;

public class ConsoleTreePrinter extends TreePrinter {
    public ConsoleTreePrinter(BufferedWriter writer) {
        super(writer);
    }

    public static void print(BufferedWriter writer, Node node) throws IOException {
        new ConsoleTreePrinter(writer).printTree(node);
    }
}
