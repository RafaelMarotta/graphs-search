package domain.depth;

import domain.general.Node;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class DepthSearch {
    private final Queue<Node> researchedNodes;
    private final DepthSearchTable searchTable;
    private final Map<Node, Node> parentNodes = new HashMap<>();

    private DepthSearch() {
        this.researchedNodes = new LinkedList<>();
        this.searchTable = new DepthSearchTable();
    }

    public static DepthSearchResult search(Node node) {
        DepthSearch dphSearch = new DepthSearch();
        dphSearch.searchRootNode(node);
        return dphSearch.getResult();
    }

    public DepthSearchResult getResult() {
        return new DepthSearchResult(researchedNodes, searchTable);
    }

    private void searchRootNode(Node node) {
        searchTable.addDT(node, null);
        researchedNodes.add(node);
        parentNodes.put(node, null);
        searchNode(node);
    }
    private boolean searchNode(Node node) {
        Node child = node.getSuccessorsQueue().poll();
        Node parent = parentNodes.get(node);
        if (child == null && parent == null){
            return true;
        }

        if (child != null && !parentNodes.containsKey(child)) {
            researchedNodes.add(child);
            parentNodes.put(child, node);
            searchTable.addDT(child, node);
            return searchNode(child);
        }
        if (child != null) {
            return searchNode(node);
        }
        searchTable.addET(node);
        return searchNode(parent);
    }
}
