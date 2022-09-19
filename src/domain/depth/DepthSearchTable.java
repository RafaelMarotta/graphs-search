package domain.depth;

import domain.general.Node;

import java.util.HashMap;
import java.util.Map;

public class DepthSearchTable {
    private int counter;
    private final Map<Node, DepthSearchTableItem> nodes = new HashMap<>();

    public void addDT(Node node, Node parent) {
        counter++;
        nodes.put(node, new DepthSearchTableItem(parent, counter));
    }

    public void addET(Node node) {
        counter++;
        nodes.get(node).setEndTime(counter);
    }

    public void printTable() {
        System.out.print("XXX |");
        nodes.keySet().forEach(this::printColumn);
        System.out.println();

        StringBuilder ed = new StringBuilder();
        StringBuilder et = new StringBuilder();
        StringBuilder parent = new StringBuilder();

        ed.append("TD  | ");
        et.append("TT  | ");
        parent.append("PAI | ");

        nodes.values().forEach(e -> {
            ed.append((e.getDiscoveryTime() < 10 ? "0" : "") +e.getDiscoveryTime() + " | ");
            et.append((e.getEndTime() < 10 ? "0" : "") + e.getEndTime() + " | ");
            String parentName = e.getParent() != null ? e.getParent().getValue() : "Ø";
            parent.append(parentName + "  | ");
        });

        System.out.println(ed);
        System.out.println(et);
        System.out.println(parent);
    }


    private void printColumn(Node col) {
        System.out.print(" " + col.getValue() + "  |");
    }
}
