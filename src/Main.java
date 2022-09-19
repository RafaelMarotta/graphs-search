import domain.depth.DepthSearchResult;
import domain.general.Node;
import domain.depth.DepthSearch;

public class Main {
    public static void main(String[] args) {
        Node a = new Node("a");
        Node b = new Node("b");
        Node c = new Node("c");
        Node d = new Node("d");
        Node e = new Node("e");
        Node f = new Node("f");

        addSuccessors(a, b, c, d, e, f);

        DepthSearchResult search = DepthSearch.search(e);
        search.printSearchTable();
        System.out.println("");
        search.printResearchedNodeOrder();
    }

    private static void addSuccessors(Node a, Node b, Node c, Node d, Node e, Node f) {
        a.addSuccessor(c);
        a.addSuccessor(e);
        a.addSuccessor(b);

        b.addSuccessor(d);
        b.addSuccessor(a);
        b.addSuccessor(e);
        b.addSuccessor(f);

        c.addSuccessor(a);
        c.addSuccessor(e);

        d.addSuccessor(b);
        d.addSuccessor(f);

        e.addSuccessor(f);
        e.addSuccessor(c);
        e.addSuccessor(a);
        e.addSuccessor(b);

        f.addSuccessor(e);
        f.addSuccessor(b);
        f.addSuccessor(d);
    }
}
