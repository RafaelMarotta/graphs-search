package domain.io.write.table;

import domain.model.Table;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;

public class ConsoleTablePrinter extends TablePrinter {
    private ConsoleTablePrinter(BufferedWriter writer, Table table) {
        super(writer, table);
    }

    public static void print(BufferedWriter writer, Table table) throws IOException {
        new ConsoleTablePrinter(writer, table).printTable();
    }

    @Override
    protected synchronized void printTable() throws IOException {
        super.printTable();
        writer.write("\n");
    }

    // Get the division columns balancing the spaces
    @Override
    protected String getDiv(int length, int maxLength) {
        StringBuilder sb = new StringBuilder();
        int blank = (maxLength - length);
        for (int i = 0; i <= blank; i++) {
            sb.append(" ");
        }
        sb.append("| ");
        return sb.toString();
    }

    @Override
    protected void printLabels(List<String> labels) throws IOException {
        writer.append("#").append(getDiv(1, getDescriptionMaxLength()));
        super.printLabels(labels);
    }
}
