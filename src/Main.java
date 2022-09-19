import domain.breadth.BreadthSearch;
import domain.breadth.BreadthSearchResult;
import domain.depth.DepthSearchResult;
import domain.general.Node;
import domain.depth.DepthSearch;

public class Main {
    public static void main(String[] args) {
        depthSearchExample();
        breadthSearchExample();
    }

    private static void breadthSearchExample() {
        Node a = new Node("a");
        Node b = new Node("b");
        Node c = new Node("c");
        Node d = new Node("d");
        Node e = new Node("e");

        addSuccessorsBreadthSearch(a, b, c, d, e);
        BreadthSearchResult result = BreadthSearch.search(a);
        result.printSearchTable();
        System.out.println("");
        result.printResearchedNodeOrder();
    }

    private static void depthSearchExample() {
        Node a = new Node("a");
        Node b = new Node("b");
        Node c = new Node("c");
        Node d = new Node("d");
        Node e = new Node("e");
        Node f = new Node("f");

        addSuccessorsDepth(a, b, c, d, e, f);
        DepthSearchResult search = DepthSearch.search(e);
        search.printSearchTable();
        System.out.println("");
        search.printResearchedNodeOrder();
    }

    private static void addSuccessorsBreadthSearch(Node a, Node b, Node c, Node d, Node e) {
        a.addSuccessor(b);
        a.addSuccessor(c);

        b.addSuccessor(d);
        b.addSuccessor(c);
        b.addSuccessor(a);

        c.addSuccessor(a);
        c.addSuccessor(d);
        c.addSuccessor(e);
        c.addSuccessor(b);

        d.addSuccessor(e);
        d.addSuccessor(b);
        d.addSuccessor(c);

        e.addSuccessor(c);
        e.addSuccessor(d);
    }

    private static void addSuccessorsDepth(Node a, Node b, Node c, Node d, Node e, Node f) {
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
