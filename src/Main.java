import domain.io.read.GraphReader;
import domain.io.write.table.ConsoleTablePrinter;
import domain.io.write.table.FileTablePrinter;
import domain.general.Node;
import domain.general.SearchResult;
import domain.io.write.tree.ConsoleTreePrinter;
import domain.io.write.tree.FileTreePrinter;
import domain.search.breadth.BreadthSearch;
import domain.search.depth.DepthSearch;

public class Main {
    public static void main(String[] args) throws Exception {
        Node rootNode = GraphReader.readGraph("teste.txt").get("A");
        SearchResult breadthResult = BreadthSearch.search(rootNode);
        SearchResult depthResult = DepthSearch.search(rootNode);

        FileTablePrinter.print(breadthResult.getTable(),"breadth-search-table.csv");
        FileTablePrinter.print(depthResult.getTable(),"depth-search-table.csv" );

        ConsoleTablePrinter.print(breadthResult.getTable());
        ConsoleTablePrinter.print(depthResult.getTable());

        ConsoleTreePrinter.print(breadthResult.getTreeNode());
        ConsoleTreePrinter.print(depthResult.getTreeNode());

        FileTreePrinter.print(breadthResult.getTreeNode(), "breadth-search-tree.txt");
        FileTreePrinter.print(depthResult.getTreeNode(), "depth-search-tree.txt");
    }
}
