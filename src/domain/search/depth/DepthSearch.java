package domain.search.depth;

import domain.general.Node;
import domain.general.SearchResult;

import java.util.*;
import java.util.stream.Collectors;

public class DepthSearch {
    private final Queue<Node> researchedNodes; //A queue with the node search order
    private final DepthSearchTable searchTable; //Used to build the SearchTable
    private final Map<Node, Node> parentNodes = new HashMap<>(); //Map to help get the parent of a node from his name
    private final Map<String, Node> treeNodeMap = new HashMap<>(); //Map to help get a node from his name
    private final Node nodeTree; //The root node of search tree

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
        searchTable.newNode(node, null);
        researchedNodes.add(node);
        parentNodes.put(node, null);
        searchNode(node);
    }

    private boolean searchNode(Node node) {
        Node child = node.getNeighborQueue().poll();
        Node parent = parentNodes.get(node);

        if (isNodeExplored(child)) {
            searchTable.setToExplored(node); //Set node as explored (Used to define end time)
            if (hasParentNode(parent)) { //If it has parent node then explored parent else finish search
                return searchNode(parent);
            }
            return true;
        }

        if (isNewNode(child)) { //If it is a node not discovered yet
            addInSearchTree(node, child);
            researchedNodes.add(child);
            parentNodes.put(child, node);
            searchTable.newNode(child, node);
            return searchNode(child);
        }
        return searchNode(node);
    }

    private void addInSearchTree(Node node, Node child) {
        Node childTree = new Node(child.getValue());
        treeNodeMap.put(child.getValue(), childTree);
        treeNodeMap.get(node.getValue()).addSuccessor(childTree);
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
