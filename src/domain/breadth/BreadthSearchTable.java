package domain.breadth;

import domain.general.Node;

import java.util.HashMap;
import java.util.Map;

public class BreadthSearchTable {
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
    public void printTable() {
        System.out.print("XXX   |");
        nodes.keySet().forEach(this::printColumn);
        System.out.println();

        StringBuilder l = new StringBuilder();
        StringBuilder level = new StringBuilder();
        StringBuilder parent = new StringBuilder();

        l.append("L     | ");
        level.append("LEVEL | ");
        parent.append("PAI   | ");

        nodes.values().forEach(e -> {
            l.append(e.getL() + " | ");
            level.append(e.getLevel() + " | ");
            String parentName = e.getParent() != null ? e.getParent().getValue() : "Ø";
            parent.append(parentName + " | ");
        });

        System.out.println(l);
        System.out.println(level);
        System.out.println(parent);
    }


    private void printColumn(Node col) {
        System.out.print(" "+col.getValue() + " |");
    }
}
