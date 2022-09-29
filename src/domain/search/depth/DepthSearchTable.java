package domain.search.depth;

import domain.general.Table;
import domain.general.TableItem;
import domain.general.Node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DepthSearchTable implements Table {
    private int counter;
    private final Map<Node, DepthSearchTableItem> nodes = new HashMap<>();

    public void newNode(Node node, Node parent) {
        counter++;
        nodes.put(node, new DepthSearchTableItem(parent, counter));
    }

    public void setToExplored(Node node) {
        counter++;
        nodes.get(node).setEndTime(counter);
    }

    @Override
    public List<String> getLabels() {
        return nodes.keySet().stream().map(Node::getValue).collect(Collectors.toList());
    }

    @Override
    public List<String> getDescriptions() {
        return List.of("TD", "TT", "PAI");
    }

    @Override
    public List<TableItem> getItems() {
        return new ArrayList<>(nodes.values());
    }
}
