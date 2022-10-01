import domain.fleury.FleuryMethod;
import domain.io.read.GraphReader;
import domain.io.write.nodes.ConsoleNodesPrinter;
import domain.io.write.nodes.FileNodesPrinter;
import domain.io.write.table.ConsoleTablePrinter;
import domain.io.write.table.FileTablePrinter;
import domain.io.write.tree.ConsoleTreePrinter;
import domain.io.write.tree.FileTreePrinter;
import domain.model.EulerianType;
import domain.model.FleuryResult;
import domain.model.Node;
import domain.model.SearchResult;
import domain.search.breadth.BreadthSearch;
import domain.search.depth.DepthSearch;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Objects;

public class Main {

    private static final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws Exception {
        if (Objects.equals(args[0], "fleury")) {
            Node rootNode = GraphReader.readGraph("input.txt").values().stream().findAny().orElseThrow(RuntimeException::new);
            fleuryMethod(rootNode);
            return;
        }

        Node rootNode = GraphReader.readGraph("input.txt").get(args[1]);
        if (Objects.equals(args[0], "profundidade")) {
            depthSearch(rootNode);
            return;
        }

        if (Objects.equals(args[0], "largura")) {
            breadthSearch(rootNode);
        }
    }

    private static void fleuryMethod(Node rootNode) throws IOException {
        System.out.println("####### FLEURY #######\n");
        FleuryResult fleuryResult = FleuryMethod.find(rootNode);
        EulerianType result = fleuryResult.getResult();
        String output = String.format("O grafo informado é %s", result.getValue());
        System.out.println(output);
        if (result != EulerianType.NOT_EULERIAN) {
            System.out.println("\n####### CAMINHO EULERIANO #######");
            ConsoleNodesPrinter.print(writer, fleuryResult.getEulerianPath());
            FileNodesPrinter.print(fleuryResult.getEulerianPath(), "fleury-visited-nodes.csv");
        }
    }

    private static void depthSearch(Node rootNode) throws IOException {
        System.out.println("####### Busca Em Profundidade #######\n");
        SearchResult depthResult = DepthSearch.search(rootNode);
        ConsoleTablePrinter.print(writer, depthResult.getTable());
        System.out.println("\n####### Arvore de busca #######\n");
        ConsoleTreePrinter.print(writer, depthResult.getTreeNode());
        System.out.println("\n####### Vértices pesquisados #######\n");
        ConsoleNodesPrinter.print(writer, depthResult.getResearchNodeOrder());
        FileTablePrinter.print(depthResult.getTable(), "depth-search-table.csv");
        FileTreePrinter.print(depthResult.getTreeNode(), "depth-search-tree.txt");
        FileNodesPrinter.print(depthResult.getResearchNodeOrder(), "depth-search-visited-nodes.csv");
    }

    private static void breadthSearch(Node rootNode) throws IOException {
        System.out.println("####### Busca Em Largura #######\n");
        SearchResult breadthResult = BreadthSearch.search(rootNode);
        ConsoleTablePrinter.print(writer, breadthResult.getTable());
        System.out.println("\n####### Arvore de busca #######\n");
        ConsoleTreePrinter.print(writer, breadthResult.getTreeNode());
        System.out.println("\n####### Vértices pesquisados #######\n");
        ConsoleNodesPrinter.print(writer, breadthResult.getResearchNodeOrder());
        FileTablePrinter.print(breadthResult.getTable(), "breadth-search-table.csv");
        FileTreePrinter.print(breadthResult.getTreeNode(), "breadth-search-tree.txt");
        FileNodesPrinter.print(breadthResult.getResearchNodeOrder(), "breadth-search-visited-nodes.csv");
    }
}
