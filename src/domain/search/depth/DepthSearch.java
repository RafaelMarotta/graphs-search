package domain.search.depth;

import domain.general.Node;
import domain.general.SearchResult;

import java.util.*;
import java.util.stream.Collectors;

public class DepthSearch {
    private final Queue<Node> researchedNodes;
    private final DepthSearchTable searchTable;
    private final Map<Node, Node> parentNodes = new HashMap<>();
    private final Map<String, Node> treeNodeMap = new HashMap<>();
    private final Node nodeTree;

    private DepthSearch(Node nodeTree) {
        this.nodeTree = nodeTree;
        this.researchedNodes = new LinkedList<>();
        this.searchTable = new DepthSearchTable();
    }

    public static SearchResult search(Node node) {
        Node searchTree = new Node(node.getValue());
        DepthSearch dphSearch = new DepthSearch(searchTree);
        dphSearch.searchRootNode(node);
        return dphSearch.getResult();
    }

    public SearchResult getResult() {
        List<String> researchNodeOrder = researchedNodes.stream().map(Node::getValue).collect(Collectors.toList());
        return new SearchResult(searchTable, researchNodeOrder, nodeTree);
    }

    private void searchRootNode(Node node) {
        treeNodeMap.put(node.getValue(), nodeTree);
        searchTable.addDT(node, null);
        researchedNodes.add(node);
        parentNodes.put(node, null);
        searchNode(node);
    }

    private boolean searchNode(Node node) {
        Node child = node.getBorderedQueue().poll();
        Node parent = parentNodes.get(node);

        if (isNodeExplored(child)) {
            searchTable.addET(node);
            if (hasParentNode(parent)) {
                return searchNode(parent);
            }
            return true;
        }

        if (isNewNode(child)) {
            Node childTree = new Node(child.getValue());
            treeNodeMap.put(child.getValue(), childTree);
            treeNodeMap.get(node.getValue()).addSuccessor(childTree);
            researchedNodes.add(child);
            parentNodes.put(child, node);
            searchTable.addDT(child, node);
            return searchNode(child);
        }
        return searchNode(node);
    }

    private boolean isNewNode(Node child) {
        return !parentNodes.containsKey(child);
    }

    private static boolean hasParentNode(Node parent) {
        return parent != null;
    }

    private static boolean isNodeExplored(Node child) {
        return child == null;
    }
}
