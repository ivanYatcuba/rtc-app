package net.github.rtc.app.export.table;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ivan Yatcuba on 8/22/14.
 */
public class CSVTable implements ReportTable {

    List<List<String>> table;

    public CSVTable() {
        table = new ArrayList<>();
    }

    @Override
    public void createRow(int rowIndex) {
        table.add(rowIndex, new ArrayList<String>());
    }

    @Override
    public void createCell(int rowIndex, int cellIndex, Object value) {
        table.get(rowIndex).add(cellIndex, value.toString());
    }

    @Override
    public void writeToFile(String fileName) throws IOException {
        FileWriter writer = new FileWriter(fileName);
        for (List<String> row : table) {
            for (String cell : row) {
                writer.append(cell).append(',');
            }
            writer.append('\n');
        }
        writer.flush();
        writer.close();
    }
}
