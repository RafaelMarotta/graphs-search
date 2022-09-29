package domain.general;

import java.util.List;

public interface Table {
    List<String> getLabels();
    List<String> getDescriptions();
    List<TableItem> getItems();
}
