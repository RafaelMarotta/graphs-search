package domain.search.breadth;

import domain.model.Node;
import domain.model.SearchResult;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class BreadthSearch {
    private final LinkedList<Node> researchedNodes; //A queue with the node search order
    private final Queue<Node> successors = new LinkedList<>(); //Queue to control next node to be explored

    private final Map<String, Node> treeNodeMap = new HashMap<>(); //Map to help get a node from his name
    private final BreadthSearchTable searchTable; //Used to build the SearchTable

    private final Node nodeTree; //The root node of search tree

    public BreadthSearch(Node searchTree) {
        this.nodeTree = searchTree;
        researchedNodes = new LinkedList<>();
        searchTable = new BreadthSearchTable();
    }

    public static SearchResult search(Node node) {
        Node searchTree = new Node(node.getValue()); //Initialize the searchTree root node
        BreadthSearch search = new BreadthSearch(searchTree);
        search.searchRootNode(node.clone());
        return search.getResult();
    }

    public SearchResult getResult() {
        return new SearchResult(searchTable, researchedNodes, nodeTree);
    }

    public void searchRootNode(Node node) {
        treeNodeMap.put(node.getValue(), nodeTree);
        researchedNodes.add(node);
        searchTable.addNode(node, null);
        searchNode(node);
    }

    private boolean searchNode(Node node) {
        mapChildren(node);
        if (allNodesHasBeenExplored()) {
            return true;
        }
        return searchNode(successors.poll());
    }

    private boolean allNodesHasBeenExplored() {
        return successors.isEmpty();
    }

    //Handle the search of all children from a node
    private void mapChildren(Node node) {
        node.getNeighborQueue().forEach(child -> addNode(node, successors, child));
    }

    private void addNode(Node parent, Queue<Node> successors, Node child) {
        if (isNotDiscovered(child)) {
            researchedNodes.add(child);
            addInSearchTree(parent, child);
            searchTable.addNode(child, parent);
            successors.add(child);
        }
    }

    private boolean isNotDiscovered(Node child) {
        return !researchedNodes.contains(child);
    }

    private void addInSearchTree(Node parent, Node child) {
        Node treeChild = new Node(child.getValue());
        treeNodeMap.put(child.getValue(), treeChild);
        treeNodeMap.get(parent.getValue()).addSuccessor(treeChild);
    }
}
