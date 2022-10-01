package domain.search.depth;

import domain.model.TableItem;
import domain.model.Node;

import java.util.Arrays;
import java.util.List;

public class DepthSearchTableItem implements TableItem {
    private final int discoveryTime;
    private int endTime;
    private final Node parent;

    public DepthSearchTableItem(Node parent, int discoveryTime) {
        this.parent = parent;
        this.discoveryTime = discoveryTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    public int getDiscoveryTime() {
        return discoveryTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public Node getParent() {
        return parent;
    }

    @Override
    public List<Object> getValues() {
        return Arrays.asList(getDiscoveryTime(), getEndTime(), getParent() == null ? "{}" : getParent().getValue());
    }
}
