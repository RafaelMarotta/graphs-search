package domain.depth;

import domain.general.Node;

import java.util.Queue;

public class DepthSearchResult {
    private final Queue<Node> researchedNodes;
    private final DepthSearchTable depthSearchTable;

    public DepthSearchResult(Queue<Node> researchedNodes, DepthSearchTable depthSearchTable) {
        this.researchedNodes = researchedNodes;
        this.depthSearchTable = depthSearchTable;
    }

    public void printSearchTable() {
        System.out.println("############################# Tabela #############################");
        depthSearchTable.printTable();
    }

    public void printResearchedNodeOrder() {
        System.out.println("############################# Busca #############################");
        researchedNodes.forEach(node -> System.out.println(node.getValue()));
    }
}
