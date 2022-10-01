package domain.io.write.nodes;

import domain.model.Node;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;

public class ConsoleNodesPrinter extends NodesPrinter {
    private ConsoleNodesPrinter(BufferedWriter writer, List<Node> nodes) {
        super(writer, nodes);
    }

    public static void print(BufferedWriter writer, List<Node> nodes) throws IOException {
        new ConsoleNodesPrinter(writer, nodes).printNodes();
    }
}
