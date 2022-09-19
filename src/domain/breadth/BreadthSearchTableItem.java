package domain.breadth;

import domain.general.Node;

public class BreadthSearchTableItem {
    private int l;
    private int level;
    private Node parent;

    public BreadthSearchTableItem(int l, Node parent) {
        this.l = l;
        this.level = level;
        this.parent = parent;
    }

    public int getL() {
        return l;
    }

    public int getLevel() {
        return level;
    }

    public Node getParent() {
        return parent;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
