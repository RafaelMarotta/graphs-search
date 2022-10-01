package domain.io.write.nodes;

import domain.model.Node;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;

public abstract class NodesPrinter {
    protected final BufferedWriter writer;
    protected final List<Node> nodes;

    protected NodesPrinter(BufferedWriter writer, List<Node> nodes) {
        this.writer = writer;
        this.nodes = nodes;
    }

    protected String getDiv() {
        return ",";
    }

    protected synchronized void printNodes() throws IOException {
        StringBuilder sb = new StringBuilder();
        nodes.forEach(e -> sb.append(e.getValue()).append(getDiv()));
        writer.append(sb.substring(0, sb.length()-1));
        writer.flush();
    }
}
