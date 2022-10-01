package domain.search.breadth;

import domain.model.Node;
import domain.model.TableItem;

import java.util.Arrays;
import java.util.List;

public class BreadthSearchTableItem implements TableItem {
    private final int l;
    private int level;
    private final Node parent;

    public BreadthSearchTableItem(int l, Node parent) {
        this.l = l;
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

    @Override
    public List<Object> getValues() {
        return Arrays.asList(getL(), getLevel(), getParent() == null ? "{}" : getParent().getValue());
    }
}
