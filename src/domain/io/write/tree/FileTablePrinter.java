package domain.io.write.tree;

import domain.general.Node;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileTablePrinter extends TreePrinter {
    protected FileTablePrinter(String file) throws IOException {
        super(new BufferedWriter(new FileWriter(file)));
    }

    public static void print(Node node, String file) throws IOException {
        new FileTablePrinter(file).printTree(node);
    }

    @Override
    public void printTree(Node node) throws IOException {
        super.printTree(node);
        writer.close();
    }
}
