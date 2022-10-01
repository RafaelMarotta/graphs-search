package domain.model;

import java.util.List;

public class FleuryResult {
    private EulerianType result;

    private List<Node> eulerianPath;

    public FleuryResult(EulerianType result) {
        this.result = result;
    }

    public FleuryResult(EulerianType result, List<Node> eulerianPath) {
        this.result = result;
        this.eulerianPath = eulerianPath;
    }

    public EulerianType getResult() {
        return result;
    }

    public List<Node> getEulerianPath() {
        return eulerianPath;
    }
}
