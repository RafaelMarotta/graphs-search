package domain.search.breadth;

import domain.general.Node;
import domain.general.SearchResult;

import java.util.*;
import java.util.stream.Collectors;

public class BreadthSearch {
    private final Queue<Node> researchedNodes;
    private final Queue<Node> successors = new LinkedList<>();

    private final Map<String, Node> treeNodeMap = new HashMap<>();
    private final BreadthSearchTable searchTable;

    private final Node nodeTree;

    public BreadthSearch(Node searchTree) {
        this.nodeTree = searchTree;
        researchedNodes = new LinkedList<>();
        searchTable = new BreadthSearchTable();
    }

    public static SearchResult search(Node node) {
        Node searchTree = new Node(node.getValue());
        BreadthSearch search = new BreadthSearch(searchTree);
        search.searchRootNode(node);
        return search.getResult();
    }

    public SearchResult getResult() {
        List<String> researchNodeOrder = researchedNodes.stream().map(Node::getValue).collect(Collectors.toList());
        return new SearchResult(searchTable, researchNodeOrder, nodeTree);
    }

    public void searchRootNode(Node node) {
        treeNodeMap.put(node.getValue(), nodeTree);
        researchedNodes.add(node);
        searchTable.addNode(node, null);
        searchNode(node);
    }
    private boolean searchNode(Node node) {
        node.getBorderedQueue().forEach(child -> addNode(node, successors, child));
        if (successors.isEmpty()) {
            return true;
        }
        return searchNode(successors.poll());
    }

    private void addNode(Node parent, Queue<Node> successors, Node child) {
        if (!researchedNodes.contains(child)) {
            researchedNodes.add(child);
            Node treeChild = new Node(child.getValue());
            treeNodeMap.put(child.getValue(), treeChild);
            treeNodeMap.get(parent.getValue()).addSuccessor(treeChild);
            successors.add(child);
            searchTable.addNode(child, parent);
        }
    }
}
