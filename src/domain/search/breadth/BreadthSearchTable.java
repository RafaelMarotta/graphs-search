package domain.search.breadth;

import domain.general.Node;
import domain.general.Table;
import domain.general.TableItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
        return List.of("L", "LEVEL", "PAI");
    }

    @Override
    public List<TableItem> getItems() {
        return new ArrayList<>(nodes.values());
    }
}
