package domain.fleury;

import domain.model.EulerianType;
import domain.model.FleuryResult;
import domain.model.Node;
import domain.model.NodeWithDegree;
import domain.search.depth.DepthSearch;

import java.util.*;
import java.util.stream.Collectors;

import static domain.model.EulerianType.*;

public class FleuryMethod {
    private final Map<String, NodeWithDegree> degrees = new HashMap<>();
    private final LinkedList<Node> eulerianPath = new LinkedList<>();

    public static FleuryResult find(Node node) {
        return new FleuryMethod().findRoot(node.clone());
    }

    public FleuryResult findRoot(Node node) {
        buildDegrees(node);
        if (isNotEulerian()) {
            return new FleuryResult(NOT_EULERIAN);
        }
        Node selectedNode = selectRootNode();
        eulerianPath.push(selectedNode);
        return findRecursively(selectedNode);
    }

    private FleuryResult findRecursively(Node selectedNode) {
        if (isNotEulerian()) {
            return new FleuryResult(NOT_EULERIAN);
        }
        Node node2 = selectedNode.getNeighborQueue().stream()
                .filter(child -> !isBridge(selectedNode, child))
                .findAny()
                .orElse(null);
        if(node2 == null) {
            if (selectedNode.getNeighborQueue().isEmpty()) {
                return new FleuryResult(getEulerianType(), eulerianPath);
            } else {
                node2 = selectedNode.getNeighborQueue().getFirst();
            }
        }
        node2.removeNeighbor(selectedNode);
        eulerianPath.push(node2);
        return findRecursively(node2);
    }

    private EulerianType getEulerianType() {
        return eulerianPath.getLast().equals(eulerianPath.getFirst()) ? EULERIAN : SEMI_EULERIAN;
    }

    private boolean isBridge(Node node1, Node node2) {
        try {
            Node clone1 = node1.clone();
            Node clone1Child = clone1.getNeighborQueue()
                    .stream()
                    .filter(e -> e.getValue().equals(node2.getValue()))
                    .findFirst()
                    .orElseThrow(RuntimeException::new);
            clone1.removeNeighbor(clone1Child);
            Node clone2 = clone1Child.clone();

            int count1 = DepthSearch.search(clone1).getResearchNodeOrder().size();
            int count2 = DepthSearch.search(clone2).getResearchNodeOrder().size();
            return count1 > count2;
        } catch (Exception exception) {
            exception.getStackTrace();
            throw new RuntimeException();
        }
    }

    private Node selectRootNode() {
        return getOddNodes().stream().findAny().orElse(
                degrees.values().stream().findAny().orElseThrow(RuntimeException::new)
        ).getNode();
    }

    private boolean isNotEulerian() {
        return getOddNodes().size() > 2;
    }

    private List<NodeWithDegree> getOddNodes() {
        return degrees.values().stream().filter(e -> e.getDegree() % 2 != 0).collect(Collectors.toList());
    }

    private void buildDegrees(Node node) {
        if (!degrees.containsKey(node.getValue())) {
            degrees.put(node.getValue(), new NodeWithDegree(node, node.getNeighborQueue().size()));
            for (Node child : node.getNeighborQueue()) {
                buildDegrees(child);
            }
        }
    }
}
