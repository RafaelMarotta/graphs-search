package domain.search.breadth;

import domain.model.Node;
import domain.model.Table;
import domain.model.TableItem;

import java.util.*;
import java.util.stream.Collectors;

public class BreadthSearchTable implements Table {
    private int l;
    private final Map<Node, BreadthSearchTableItem> nodes = new HashMap<>();

    public void addNode(Node node, Node parent) {
        l++;
        BreadthSearchTableItem item = new BreadthSearchTableItem(l, parent);
        BreadthSearchTableItem parentNode = nodes.get(parent);
        if (parentNode == null) {
            item.setLevel(0);
        } else {
            item.setLevel(parentNode.getLevel() + 1);
        }
        nodes.put(node, item);
    }

    @Override
    public List<String> getLabels() {
        return nodes.keySet().stream().map(Node::getValue).collect(Collectors.toList());
    }

    @Override
    public List<String> getDescriptions() {
        return Arrays.asList("L", "LEVEL", "PAI");
    }

    @Override
    public List<TableItem> getItems() {
        return new ArrayList<>(nodes.values());
    }
}
