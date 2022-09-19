package domain.depth;

import domain.general.Node;

public class DepthSearchTableItem {
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
}
