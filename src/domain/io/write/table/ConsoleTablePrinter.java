package domain.io.write.table;

import domain.general.Table;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

public class ConsoleTablePrinter extends TablePrinter {
    private static final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    private ConsoleTablePrinter(Table table) {
        super(writer, table);
    }

    public static void print(Table table) throws IOException {
        new ConsoleTablePrinter(table).printTable();
    }

    @Override
    protected synchronized void printTable() throws IOException {
        super.printTable();
        writer.write("\n");
    }

    @Override
    protected String getDiv(int length, int maxLength) {
        StringBuilder sb = new StringBuilder();
        int blank = (maxLength - length);
        for(int i = 0; i <= blank; i++) {
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
