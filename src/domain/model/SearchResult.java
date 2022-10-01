package domain.model;

import java.util.List;

public class SearchResult {
    private final Table table;
    private final List<Node> researchNodeOrder;

    private final Node treeNode;

    public SearchResult(Table table, List<Node> researchNodeOrder, Node treeNode) {
        this.table = table;
        this.researchNodeOrder = researchNodeOrder;
        this.treeNode = treeNode;
    }

    public Table getTable() {
        return table;
    }

    public List<Node> getResearchNodeOrder() {
        return researchNodeOrder;
    }

    public Node getTreeNode() {
        return treeNode;
    }
}
