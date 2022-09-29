package domain.general;

import java.util.List;

public class SearchResult {
    private final Table table;
    private final List<String> researchNodeOrder;

    private final Node treeNode;

    public SearchResult(Table table, List<String> researchNodeOrder, Node treeNode) {
        this.table = table;
        this.researchNodeOrder = researchNodeOrder;
        this.treeNode = treeNode;
    }

    public Table getTable() {
        return table;
    }

    public List<String> getResearchNodeOrder() {
        return researchNodeOrder;
    }

    public Node getTreeNode() {
        return treeNode;
    }
}
