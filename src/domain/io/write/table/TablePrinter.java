package domain.io.write.table;

import domain.model.Table;
import domain.model.TableItem;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public abstract class TablePrinter {
    protected final BufferedWriter writer;
    private final Table table;

    protected TablePrinter(BufferedWriter writer, Table table) {
        this.writer = writer;
        this.table = table;
    }

    protected String getDiv(int length, int maxLength) {
        return ";";
    }

    protected synchronized void printTable() throws IOException {
        printLabels(table.getLabels());
        List<String> descriptions = table.getDescriptions();
        for (int i = 0; i < descriptions.size(); i++) {
            printValue(descriptions.get(i), getDescriptionMaxLength());
            printCol(i);
        }
        writer.flush();
    }

    protected void printCol(int col) throws IOException {
        List<String> values = getCol(col);
        for (int i = 0; i < values.size(); i ++) {
            printValue(values.get(i), Math.max(getRowMaxLength(i), getLabelMaxLength()));
        }
        writer.append("\n");
    }

    protected void printLabels(List<String> labels) throws IOException {
        for (int i = 0; i < labels.size(); i++) {
            printValue(labels.get(i), Math.max(getRowMaxLength(i), getLabelMaxLength()));
        }
        writer.append("\n");
    }

    protected void printValue(String value, int maxSize) throws IOException {
        String val = String.valueOf(value);
        writer.append(val).append(getDiv(value.length(), maxSize));
    }

    protected int getDescriptionMaxLength() {
        return table.getDescriptions().stream().mapToInt(String::length).max().orElseThrow(RuntimeException::new);
    }

    protected int getLabelMaxLength() {
        return table.getLabels().stream().mapToInt(String::length).max().orElseThrow(RuntimeException::new);
    }

    protected int getRowMaxLength(int row) {
        return getRow(row).stream().mapToInt(String::length).max().orElseThrow(RuntimeException::new);
    }

    protected int getColMaxLength(int col) {
        return getCol(col).stream().mapToInt(String::length).max().orElseThrow(RuntimeException::new);
    }

    protected List<String> getRow(int row) {
        return table.getItems().get(row).getValues().stream().map(String::valueOf).collect(Collectors.toList());
    }

    protected List<String> getCol(int col) {
        return table.getItems().stream().map(TableItem::getValues).map(e -> String.valueOf(e.get(col))).collect(Collectors.toList());
    }
}
