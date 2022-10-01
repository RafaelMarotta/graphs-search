package domain.io.write.tree;

import domain.model.Node;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileTreePrinter extends TreePrinter {
    protected FileTreePrinter(String file) throws IOException {
        super(new BufferedWriter(new FileWriter(file)));
    }

    public static void print(Node node, String file) throws IOException {
        new FileTreePrinter(file).printTree(node);
    }
}
