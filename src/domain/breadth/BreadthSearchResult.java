package domain.breadth;

import domain.general.Node;

import java.util.Queue;

public class BreadthSearchResult {
    private final Queue<Node> researchedNodes;
    private final BreadthSearchTable depthSearchTable;

    public BreadthSearchResult(Queue<Node> researchedNodes, BreadthSearchTable depthSearchTable) {
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
