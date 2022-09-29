package domain.io.write.table;

import domain.general.Table;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class FileTablePrinter extends TablePrinter {
    private FileTablePrinter(String file, Table table) throws IOException {
        super(new BufferedWriter(new FileWriter(file)), table);
    }

    public static void print(Table table, String file) throws IOException {
        new FileTablePrinter(file, table).printTable();
    }

    @Override
    protected synchronized void printTable() throws IOException {
        super.printTable();
        writer.close();
    }

    @Override
    protected void printLabels(List<String> labels) throws IOException {
        writer.append(getDiv(0, getDescriptionMaxLength()));
        super.printLabels(labels);
    }
}
