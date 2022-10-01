package domain.search.depth;

import domain.model.Table;
import domain.model.TableItem;
import domain.model.Node;

import java.util.*;
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
        return Arrays.asList("TD", "TT", "PAI");
    }

    @Override
    public List<TableItem> getItems() {
        return new ArrayList<>(nodes.values());
    }
}
