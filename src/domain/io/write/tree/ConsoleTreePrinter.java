package domain.io.write.tree;

import domain.general.Node;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class ConsoleTreePrinter extends TreePrinter {
    private static final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

    public ConsoleTreePrinter() {
        super(writer);
        System.out.println();
    }

    public static void print(Node node) throws IOException {
        new ConsoleTreePrinter().printTree(node);
    }
}
