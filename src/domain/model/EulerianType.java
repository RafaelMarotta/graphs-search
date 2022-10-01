package domain.model;

public enum EulerianType {
    EULERIAN("Euleriano"),
    SEMI_EULERIAN("Semi-euleriano"),
    NOT_EULERIAN("Não é euleriano");

    private final String value;

    EulerianType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
