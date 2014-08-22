package net.github.rtc.app.export.table;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

/**
 * Created by Ivan Yatcuba on 8/22/14.
 */
public class XLSNXTable implements ReportTable {

    private Sheet sheet;
    private CellStyle cellStyle;
    private CreationHelper createHelper;

    public XLSNXTable(Workbook workbook, String sheetName) {
        this.sheet = workbook.createSheet(sheetName);
        this.cellStyle = workbook.createCellStyle();
        this.createHelper = workbook.getCreationHelper();
        this.cellStyle.setDataFormat(
                createHelper.createDataFormat().getFormat("dd/mm/yy"));
    }

    @Override
    public void createRow(int rowIndex) {
        sheet.createRow(rowIndex);
    }

    @Override
    public void createCell(int rowIndex, int cellIndex, Object value) {
        if(value instanceof Date){
            sheet.getRow(rowIndex).createCell(cellIndex).setCellValue((Date) value);
            sheet.getRow(rowIndex).getCell(cellIndex).setCellStyle(cellStyle);
        }else {
            sheet.getRow(rowIndex).createCell(cellIndex).
                    setCellValue(createHelper.createRichTextString(value.toString()));
        }
    }

    @Override
    public void writeToFile(String fileName) throws IOException {
        File file = new File(fileName);
        if (!file.exists()) {
            file.createNewFile();
        }
        FileOutputStream fileOut = new FileOutputStream(file);
        sheet.getWorkbook().write(fileOut);
        fileOut.close();
    }


}
