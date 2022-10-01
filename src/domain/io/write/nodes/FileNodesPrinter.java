package domain.io.write.nodes;

import domain.model.Node;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

public class FileNodesPrinter extends NodesPrinter {
    private static final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

    protected FileNodesPrinter(String file, List<Node> nodes) throws IOException {
        super(new BufferedWriter(new FileWriter(file)), nodes);
    }

    public static void print(List<Node> nodes, String file) throws IOException {
        new FileNodesPrinter(file, nodes).printNodes();
    }

    @Override
    protected String getDiv() {
        return ";";
    }
}
