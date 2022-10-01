package domain.model;

public class NodeWithDegree {
    private final Node node;
    private Integer degree;

    public NodeWithDegree(Node node, Integer degree) {
        this.node = node;
        this.degree = degree;
    }

    public void setDegree(Integer degree) {
        this.degree = degree;
    }

    public void decreaseDegree() {
        degree--;
    }

    public Node getNode() {
        return node;
    }

    public Integer getDegree() {
        return degree;
    }
}
