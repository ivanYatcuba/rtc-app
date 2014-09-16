package net.github.rtc.app.export.table;

import java.io.IOException;

/**
 * Created by Ivan Yatcuba on 8/22/14.
 */
public interface ReportTable {
    void createRow(int rowIndex);

    void createCell(int rowIndex, int cellIndex, Object value);

    void writeToFile(String fileName) throws IOException;
}
