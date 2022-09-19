package domain.breadth;

import domain.general.Node;

import java.util.*;

public class BreadthSearch {
    private final Queue<Node> researchedNodes;
    private final Queue<Node> successors = new LinkedList<>();
    private final BreadthSearchTable searchTable;

    public BreadthSearch() {
        researchedNodes = new LinkedList<>();
        searchTable = new BreadthSearchTable();
    }

    public static BreadthSearchResult search(Node node) {
        BreadthSearch search = new BreadthSearch();
        search.searchRootNode(node);
        return search.getResult();
    }

    public BreadthSearchResult getResult() {
        return new BreadthSearchResult(researchedNodes, searchTable);
    }

    public void searchRootNode(Node node) {
        System.out.println(node.getValue());
        researchedNodes.add(node);
        searchTable.addNode(node, null);
        searchNode(node);
    }
    private boolean searchNode(Node node) {
        node.getSuccessorsQueue().forEach(child -> addNode(node, successors, child));
        if (successors.isEmpty()) {
            return true;
        }
        return searchNode(successors.poll());
    }

    private void addNode(Node parent, Queue<Node> successors, Node child) {
        if (!researchedNodes.contains(child)) {
            researchedNodes.add(child);
            successors.add(child);
            searchTable.addNode(child, parent);
        }
    }
}
