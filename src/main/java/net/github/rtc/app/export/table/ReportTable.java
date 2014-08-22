package net.github.rtc.app.export.table;

import java.io.IOException;

/**
 * Created by Ivan Yatcuba on 8/22/14.
 */
public interface ReportTable {
    public void createRow(int rowIndex);
    public void createCell(int rowIndex, int cellIndex, Object value);
    public void writeToFile(String fileName) throws IOException;
}
