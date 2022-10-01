package domain.io.read;

import domain.model.Node;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;

public class GraphReader {
    private final Map<String, Node> nodesMap;
    private static final String DIV_CHAR = ",";

    public GraphReader() {
        this.nodesMap = new HashMap<>();
    }

    public static Map<String, Node> readGraph(String filePath) {
        try {
            GraphReader reader = new GraphReader();
            return reader.readGraph(new File(filePath));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public Map<String, Node> readGraph(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String edge = scanner.nextLine();
            String[] nodes = edge.split(DIV_CHAR);
            Node node1 = getNodeByName(nodes[0]);
            Node node2 = getNodeByName(nodes[1]);
            node1.addNeighbor(node2);
        }
        return nodesMap;
    }

    private Node getNodeByName(String name) {
        return Optional.ofNullable(nodesMap.get(name)).orElseGet(() -> {
            Node node = new Node(name);
            nodesMap.put(name, node);
            return node;
        });
    }
}
